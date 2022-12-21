package by.clevertech.service.exception;

/**
 * Extends {@link RuntimeException}
 * <p>
 * The main class that provides the user with information about the occurrence
 * of an exception.
 * 
 * @author Nikita Semeniuk
 *
 */
@SuppressWarnings("serial")
public class ClevertechException extends RuntimeException {

    public ClevertechException() {
    }

    public ClevertechException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClevertechException(String message) {
        super(message);
    }

    public ClevertechException(Throwable cause) {
        super(cause);
    }
}
