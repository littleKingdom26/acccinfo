package info.team23h.acc.vo.event;

import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventSubVO extends CommonVO {

	/**
	 * 차 아이디
	 */
	private String carId;
	/**
	 * 이벤트 시퀀스
	 */
	private int eventInfoSeq;
	/**
	 * 렙
	 */
	private int lap;
	/**
	 * 렙 타임
	 */
	private String lapTime;
	/**
	 * 섹터 1
	 */
	private String sector1;
	/**
	 * 섹터 2
	 */
	private String sector2;
	/**
	 * 섹터 3
	 */
	private String sector3;

	/**
	 * 라운드
	 */
	private int round;

	/**
	 * 드라이버 명
	 */
	private String playerName;
}
