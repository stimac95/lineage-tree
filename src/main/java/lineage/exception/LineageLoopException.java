package lineage.exception;

/**
 * Thrown when the loop in the tree lineage is detected.
 */
public class LineageLoopException extends Exception {
    public LineageLoopException() {
    }

    public LineageLoopException(String message) {
        super(message);
    }

    public LineageLoopException(String message, Throwable cause) {
        super(message, cause);
    }

    public LineageLoopException(Throwable cause) {
        super(cause);
    }

    public LineageLoopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
