package info.team23h.acc.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventSubVO extends CommonVO{

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
	private int lapTime;
	/**
	 * 섹터 1
	 */
	private int sector1;
	/**
	 * 섹터 2
	 */
	private int sector2;
	/**
	 * 섹터 3
	 */
	private int sector3;

	/**
	 * 라운드
	 */
	private int round;
}
