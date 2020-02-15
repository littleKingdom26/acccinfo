package info.team23h.acc.util;

public class MathUtil {

	final static int MIN = 60;
	final static int HOUR = MIN*60;
	/**
	 * 초를 분초형태로
	 * @param rowData
	 * @return
	 */
	public static String secToMin(int rowData){
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
			result += String.format("%02d", s) + "." + millisec;
		}
		return result;
	}
}
