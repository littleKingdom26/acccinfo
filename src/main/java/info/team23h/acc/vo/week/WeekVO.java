package info.team23h.acc.vo.week;

import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WeekVO extends CommonVO {

	/**
	 * 주차 시퀀스
	 */
	private long seq;

	/**
	 * 세션 아이디
	 */
	private int sessionId;
	/**
	 * 트랙 시퀀스
	 */
	private long trackSeq;
	/**
	 * 시작 날짜
	 */
	private String startDt;
	/**
	 * 끝 날짜
	 */
	private String endDt;

	/**
	 * 옵션 체크 유무
	 */
	private boolean selected;


}
