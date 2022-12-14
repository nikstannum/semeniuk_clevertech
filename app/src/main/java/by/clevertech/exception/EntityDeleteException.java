package by.clevertech.exception;

public class EntityDeleteException extends MyAppException {
	public EntityDeleteException() {
	}

	public EntityDeleteException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityDeleteException(String message) {
		super(message);
	}

	public EntityDeleteException(Throwable cause) {
		super(cause);
	}
}
