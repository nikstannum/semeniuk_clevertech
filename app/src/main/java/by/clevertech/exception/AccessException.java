package by.clevertech.exception;

public class AccessException extends MyAppException {
	public AccessException() {
	}

	public AccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessException(String message) {
		super(message);
	}

	public AccessException(Throwable cause) {
		super(cause);
	}
}
