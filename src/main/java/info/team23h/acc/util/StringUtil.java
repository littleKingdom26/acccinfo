package info.team23h.acc.util;

public class StringUtil {


	/**
	 * String > String 공백제거
	 *
	 * @param value
	 * @return
	 */
	public static String nvl(String value) {
		return (value != null && value.length() > 0) ? value.trim() : "";
	}

	/**
	 * Long > String 형변환
	 *
	 * @param value
	 * @return
	 */
	public static String nvl(long value) {
		return ObjectUtil.isNull(value) ? "" : Long.toString(value);
	}

	/**
	 * Integer > String 형변환
	 *
	 * @param value
	 * @return
	 */
	public static String nvl(int value) {
		return ObjectUtil.isNull(value) ? "" : Integer.toString(value);
	}

	/**
	 * Object > String 형변환
	 *
	 * @param value
	 * @return
	 */
	public static String nvl(Object value) {
		return ObjectUtil.isNull(value) ? "" : String.valueOf(value);
	}

	/**
	 * String > String 데이터
	 *
	 * @param value
	 * @param repairValue
	 * @return
	 * @description 대체 value가 공백일 경우 repairValue로 대체
	 */
	public static String nvl(String value,
							 String repairValue) {
		return ("".equals(nvl(value))) ? nvl(repairValue) : value;
	}

	/**
	 * String > Integer 데이터
	 *
	 * @param value
	 * @param repairValue
	 * @return
	 * @description 대체 value가 공백일 경우 repairValue로 대체
	 */
	public static String nvl(String value,
							 int repairValue) {
		return ("".equals(nvl(value))) ? nvl(repairValue) : value;
	}

	/**
	 * String > long 데이터
	 *
	 * @param value
	 * @param repairValue
	 * @return
	 * @description 대체 value가 공백일 경우 repairValue로 대체
	 */
	public static String nvl(String value,
							 long repairValue) {
		return ("".equals(nvl(value))) ? nvl(repairValue) : value;
	}

}
