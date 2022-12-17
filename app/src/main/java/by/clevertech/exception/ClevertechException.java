package by.clevertech.exception;

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
