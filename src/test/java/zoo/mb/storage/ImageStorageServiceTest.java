package zoo.mb.storage;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ImageStorageServiceTest {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void storesValidImageWithGeneratedFilename() throws Exception {
		byte[] png = createPng();
		Path directory = temporaryFolder.newFolder().toPath();
		ImageStorageService service = new ImageStorageService(directory.toString());

		ImageStorageService.StoredImage storedImage = service.store(png);

		assertTrue(storedImage.getFilename().matches("[0-9a-f-]{36}\\.png"));
		assertEquals("image/png", storedImage.getContentType());
		assertEquals(png.length, storedImage.getSizeBytes());
		assertArrayEquals(png, Files.readAllBytes(directory.resolve(storedImage.getFilename())));
	}

	@Test
	public void rejectsNonImageContent() throws Exception {
		ImageStorageService service = new ImageStorageService(temporaryFolder.newFolder().getAbsolutePath());

		try {
			service.store("not an image".getBytes(java.nio.charset.StandardCharsets.UTF_8));
			fail("Expected invalid image to be rejected.");
		} catch (ResponseStatusException exception) {
			assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
		}
	}

	private byte[] createPng() throws Exception {
		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
			ImageIO.write(image, "png", output);
			return output.toByteArray();
		}
	}
}
