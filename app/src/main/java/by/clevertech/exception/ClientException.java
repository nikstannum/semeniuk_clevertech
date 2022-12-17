package by.clevertech.exception;

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
