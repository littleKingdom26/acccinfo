package info.team23h.acc.config;

public class Team23hRestException extends RuntimeException{


	public Team23hRestException(String message) {
		super(message);
	}

	public Team23hRestException(String message,
								Throwable cause) {
		super(message, cause);
	}
}
