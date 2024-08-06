package liquibase.exception;

import lombok.Getter;

import java.util.logging.Level;

/**
 * Base class for all Liquibase exceptions.
 */
@Getter
public class LiquibaseException extends Exception {

    private static final long serialVersionUID = 1L;
    private String timestamp;
    private String details;
    private Level logLevel = null;

    public LiquibaseException() {
    }

    public LiquibaseException(String message) {
        super(message);
    }

    public LiquibaseException(String message, Level logLevel) {
        super(message);
        this.logLevel = logLevel;
    }

    public LiquibaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LiquibaseException(Throwable cause) {
        super(cause);
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
