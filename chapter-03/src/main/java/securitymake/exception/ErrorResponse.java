package securitymake.exception;

import java.time.Instant;
import java.time.ZoneId;

public class ErrorResponse {

    private final Instant timestamp;
    private final String message;

    public ErrorResponse(String message) {
        this.timestamp = Instant.now().atZone(ZoneId.of("Asia/Seoul")).toInstant();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
