package info.team23h.acc.config;

public class Team23hException extends RuntimeException{


	public Team23hException(String message) {
		super(message);
	}

	public Team23hException(String message,
							Throwable cause) {
		super(message, cause);
	}
}
