package by.clevertech.exception;

public class EntityUpdateException extends MyAppException {
	public EntityUpdateException() {
	}

	public EntityUpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityUpdateException(String message) {
		super(message);
	}

	public EntityUpdateException(Throwable cause) {
		super(cause);
	}
}
