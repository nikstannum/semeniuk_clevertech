package by.clevertech.service.exception;

/**
 * Extends {@link ClevertechException}
 * <p>
 * A class that provides the user with information about exceptions that have
 * arisen as a result of incorrect data handling.
 * 
 * @author Nikita Semeniuk
 *
 */
public class ClientException extends ClevertechException {

    public ClientException() {
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

}
