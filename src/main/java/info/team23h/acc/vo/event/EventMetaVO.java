package info.team23h.acc.vo.event;

import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventMetaVO extends CommonVO {

	/**
	 * 이벤트 정보 시퀀스
	 */
	private int eventInfoSeq;
	/**
	 * 라운드
	 */
	private int round;

	/**
	 * 트랙명
	 */
	private String trackName;

	/**
	 * 트랙 뷰 네임
	 */
	private String trackViewName;


}
