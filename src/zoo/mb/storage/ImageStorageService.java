package zoo.mb.storage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ImageStorageService {

	private static final long MAX_IMAGE_PIXELS = 40_000_000;
	private static final Map<String, ImageFormat> SUPPORTED_FORMATS;

	static {
		Map<String, ImageFormat> formats = new HashMap<String, ImageFormat>();
		formats.put("jpeg", new ImageFormat("jpg", "image/jpeg"));
		formats.put("png", new ImageFormat("png", "image/png"));
		formats.put("gif", new ImageFormat("gif", "image/gif"));
		formats.put("bmp", new ImageFormat("bmp", "image/bmp"));
		formats.put("wbmp", new ImageFormat("wbmp", "image/vnd.wap.wbmp"));
		SUPPORTED_FORMATS = Collections.unmodifiableMap(formats);
	}

	private final Path storageDirectory;

	public ImageStorageService(@Value("${app.image-storage.location:uploads/images}") String storageLocation) {
		this.storageDirectory = Paths.get(storageLocation).toAbsolutePath().normalize();
	}

	public StoredImage store(byte[] imageBytes) throws IOException {
		ImageFormat format = detectFormat(imageBytes);
		String filename = UUID.randomUUID() + "." + format.getExtension();
		Files.createDirectories(storageDirectory);
		Files.write(storageDirectory.resolve(filename), imageBytes);
		return new StoredImage(filename, format.getContentType(), imageBytes.length);
	}

	private ImageFormat detectFormat(byte[] imageBytes) throws IOException {
		try (ImageInputStream input = ImageIO.createImageInputStream(new ByteArrayInputStream(imageBytes))) {
			if (input == null) {
				throw invalidImage();
			}

			Iterator<ImageReader> readers = ImageIO.getImageReaders(input);
			if (!readers.hasNext()) {
				throw invalidImage();
			}

			ImageReader reader = readers.next();
			try {
				reader.setInput(input, true, true);
				ImageFormat format = SUPPORTED_FORMATS.get(reader.getFormatName().toLowerCase(Locale.ROOT));
				if (format == null) {
					throw invalidImage();
				}

				int width = reader.getWidth(0);
				int height = reader.getHeight(0);
				if (width <= 0 || height <= 0 || (long) width * height > MAX_IMAGE_PIXELS) {
					throw invalidImage();
				}
				return format;
			} finally {
				reader.dispose();
			}
		}
	}

	private ResponseStatusException invalidImage() {
		return new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"Upload must contain a valid JPEG, PNG, GIF, BMP, or WBMP image.");
	}

	public static class StoredImage {

		private final String filename;
		private final String contentType;
		private final long sizeBytes;

		public StoredImage(String filename, String contentType, long sizeBytes) {
			this.filename = filename;
			this.contentType = contentType;
			this.sizeBytes = sizeBytes;
		}

		public String getFilename() {
			return filename;
		}

		public String getContentType() {
			return contentType;
		}

		public long getSizeBytes() {
			return sizeBytes;
		}
	}

	private static class ImageFormat {

		private final String extension;
		private final String contentType;

		private ImageFormat(String extension, String contentType) {
			this.extension = extension;
			this.contentType = contentType;
		}

		private String getExtension() {
			return extension;
		}

		private String getContentType() {
			return contentType;
		}
	}
}
