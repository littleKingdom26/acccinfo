package info.team23h.acc.util;

public class ObjectUtil {

	/**
	 * Object NULL 체크
	 *
	 * @param value
	 * @return
	 */
	public static boolean isNull(Object value) {
		return value == null;
	}

	/**
	 * Object[] NULL 체크
	 *
	 * @param value
	 * @return
	 */
	public static boolean isNull(Object[] value) {
		return value == null || value.length <= 0;
	}

	/**
	 * Object > Object 형변환
	 *
	 * @param value
	 * @return
	 */
	public static Object nvl(Object value) {
		return (isNull(value)) ? "" : (isNull(StringUtil.nvl(String.valueOf(value)))) ? "" : StringUtil.nvl(String.valueOf(value));
	}

}
