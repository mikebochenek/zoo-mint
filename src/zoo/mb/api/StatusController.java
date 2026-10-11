package zoo.mb.api;

import java.time.Instant;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

	private volatile Instant serverStartTime;

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		serverStartTime = Instant.now();
	}

	@GetMapping("/api/status")
	public StatusResponse getStatus() {
		Runtime runtime = Runtime.getRuntime();
		return new StatusResponse(
				serverStartTime,
				System.getProperty("java.version"),
				runtime.freeMemory(),
				runtime.totalMemory(),
				runtime.maxMemory());
	}

	public record StatusResponse(
			Instant serverStartTime,
			String javaVersion,
			long freeMemoryBytes,
			long totalMemoryBytes,
			long maxMemoryBytes) {
	}
}
