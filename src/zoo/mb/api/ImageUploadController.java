package zoo.mb.api;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import zoo.mb.storage.ImageStorageService;

@RestController
public class ImageUploadController {

	private final ImageStorageService imageStorageService;

	public ImageUploadController(ImageStorageService imageStorageService) {
		this.imageStorageService = imageStorageService;
	}

	@PostMapping(value = "/api/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ImageUploadResponse uploadImage(@RequestPart("image") MultipartFile image) throws IOException {
		if (image.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image must not be empty.");
		}

		ImageStorageService.StoredImage storedImage = imageStorageService.store(image.getBytes());
		return new ImageUploadResponse(storedImage.getFilename(), storedImage.getContentType(),
				storedImage.getSizeBytes());
	}

	public static class ImageUploadResponse {

		private final String filename;
		private final String contentType;
		private final long sizeBytes;

		public ImageUploadResponse(String filename, String contentType, long sizeBytes) {
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
}
