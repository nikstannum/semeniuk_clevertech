package by.clevertech.exception;

public class EntityCreateException extends MyAppException {
	public EntityCreateException() {
	}

	public EntityCreateException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityCreateException(String message) {
		super(message);
	}

	public EntityCreateException(Throwable cause) {
		super(cause);
	}
}
