package info.team23h.acc.vo.event;

import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventResultVO extends CommonVO {

	/** 이벤스 시퀀스 */
	private int eventSeq;
	/**
	 * 이벤트 정보 시퀀스
	 */
	private int eventInfoSeq;
	/**
	 * 라운드
	 */
	private int round;
	/**
	 * 플레이어 아이디
	 */
	private String playerId;
	/**
	 * 차 아이디
	 */
	private String carId;
	/**
	 * 레이스 타임
	 */
	private int raceTime;
	/**
	 * 총 타임
	 */
	private int totalTime;
	/**
	 * 총 렙
	 */
	private int totalLap;
	/**
	 * 베스트랩
	 */
	private int bestLap;
	/**
	 * 섹터1
	 */
	private int sector1;
	/**
	 * 섹터2
	 */
	private int sector2;
	/**
	 * 섹터3
	 */
	private int sector3;
	/**
	 * 순위
	 */
	private int rank;
	/**
	 * 스코어
	 */
	private int score;
	/**
	 * 핸디캡
	 */
	private int handicap;
	/**
	 * 패널티
	 */
	private int penalty;

	/**
	 * 차량모델
	 */
	private int carModel;

	/**
	 * 의무 피트 스톱 남은 수
	 */
	private int missMandatoryPitStop;
}
