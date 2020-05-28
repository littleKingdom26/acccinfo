package info.team23h.acc.util;

public class MathUtil {

	final static int MIN = 60;
	final static int HOUR = MIN*60;

	/**
	 * 초를 분초형태로
	 *
	 * @param rowData 시간
	 * @param secZero  true : 초에 0 붙임 false :  초에 0 안붙임
	 * @return string
	 */
	public static String secToMin(int rowData,boolean secZero){
		String result = "";
		if(rowData <1000){
			result ="0."+String.format("%03d",rowData);
		}else {
			int rowSec = rowData / 1000;
			String rowSecString = String.valueOf(rowData);
			int h,m,s;
			s= rowSec%MIN;
			m = rowSec > HOUR ? rowSec/MIN/HOUR : rowSec/MIN;
			h = rowSec / HOUR;

			String millisec = rowSecString.substring(rowSecString.length() - 3);
			if(m > 0){
				result = m + ":";
			}
			if(secZero){
				result += String.format("%02d", s) + "." + millisec;
			}else{
				result += s + "." + millisec;
			}

		}
		return result;
	}

	public static String secToMin(String StringRowData,
								  boolean secZero) {
		return secToMin(StringUtil.nvlToInt(StringRowData, 0), secZero);
	}

	public static String secToMin(Object race_time) {
		return secToMin(StringUtil.nvlToInt(race_time, 0),true);
	}
}
