package by.clevertech.exception;

/**
 * Extends {@link ClientException}
 * <p>
 * A class that provides the user with information about exceptions that result
 * from a request to non-existent resources.
 * 
 * @author Nikita Semeniuk
 *
 */
public class EntityNotFoundException extends ClientException {

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

}
