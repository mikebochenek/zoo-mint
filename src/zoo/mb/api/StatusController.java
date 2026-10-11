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

	public static class StatusResponse {

		private final Instant serverStartTime;
		private final String javaVersion;
		private final long freeMemoryBytes;
		private final long totalMemoryBytes;
		private final long maxMemoryBytes;

		public StatusResponse(Instant serverStartTime, String javaVersion, long freeMemoryBytes,
				long totalMemoryBytes, long maxMemoryBytes) {
			this.serverStartTime = serverStartTime;
			this.javaVersion = javaVersion;
			this.freeMemoryBytes = freeMemoryBytes;
			this.totalMemoryBytes = totalMemoryBytes;
			this.maxMemoryBytes = maxMemoryBytes;
		}

		public Instant getServerStartTime() {
			return serverStartTime;
		}

		public String getJavaVersion() {
			return javaVersion;
		}

		public long getFreeMemoryBytes() {
			return freeMemoryBytes;
		}

		public long getTotalMemoryBytes() {
			return totalMemoryBytes;
		}

		public long getMaxMemoryBytes() {
			return maxMemoryBytes;
		}
	}
}
