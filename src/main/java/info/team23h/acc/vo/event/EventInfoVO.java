package info.team23h.acc.vo.event;

import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventInfoVO extends CommonVO {

	/**
	 * 이벤트 정보 시퀀스
	 */
	private int eventInfoSeq;
	/**
	 * 타이틀
	 */
	private String title;
	/**
	 * 라운드
	 */
	private int round;
	/**
	 * 스코어 시퀀스
	 */
	private int scoreInfoSeq;
	/**
	 * 핸디캡 시퀀스
	 */
	private int handicapInfoSeq;

	/**
	 * 스코어 타이틀
	 */
	private String scoreInfoTitle;

	/**
	 * 핸디캡 타이틀
	 */
	private String handicapInfoTitle;

	/**
	 * 파서 데이터
	 */
	private String parserString;

	/**
	 * 빅 그리드 여부
	 */
	private String bigGridYn;

	private List<Integer> rounds;

}
