package info.team23h.acc.config.variable;

public enum CommonResponse {

	SUCCESS("SUC", "성공"),
	FAIL("ERR", "에러발생");

	String code;

	String msg;

	CommonResponse(final String code, final String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
