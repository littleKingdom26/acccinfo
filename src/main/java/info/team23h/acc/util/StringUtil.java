package info.team23h.acc.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;

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

	/**
	 * Object > int
	 * @param value
	 * @param repairValue
	 * @return
	 */
	public static int nvlToInt(Object value,int repairValue) {
		return Integer.parseInt(nvl(String.valueOf(value), repairValue));
	}

	public static int nvlToInt(Object value) {
		return Integer.parseInt(nvl(String.valueOf(value), 0));

	}

	/**
	 * 마크업 작성 form
	 *
	 * @param parameterClass
	 * @param parameterObject
	 * @return
	 */
	public static String markupForm(Class<?> parameterClass, Object parameterObject) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		StringBuffer stringBuffer = new StringBuffer();

		Class<?> variableClass = parameterClass;
		while(variableClass != null && variableClass != Object.class){

			for(Field field : variableClass.getDeclaredFields()){
				if(!field.isSynthetic()){
					char[] arrayChar = field.getName().substring(0, 1).toCharArray();
					String fieldName = "get" + Character.toUpperCase(arrayChar[0]) + field.getName().substring(1);
					Object fieldValue;

					try{
						fieldValue = variableClass.getDeclaredMethod(fieldName).invoke(parameterObject);
						hashMap.put(field.getName(), StringUtil.nvl(fieldValue));

					}catch(Exception e){
						e.printStackTrace();
					}

				}
			}
			variableClass = variableClass.getSuperclass();
		}

		Iterator<String> iterator = hashMap.keySet().iterator();

		while(iterator.hasNext()){
			String key = String.valueOf(iterator.next());
			String value = String.valueOf(hashMap.get(key));
			stringBuffer.append("<input type=\"hidden\" name=\"" + key + "\" id=\"" + key + "\" value=\"" + value + "\" />\n");
		}

		return stringBuffer.toString();
	}
}
