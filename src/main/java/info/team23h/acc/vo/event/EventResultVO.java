package info.team23h.acc.vo.event;

import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventResultVO extends CommonVO {

	/** 이벤스 시퀀스 */
	private Long eventSeq;
	/**
	 * 이벤트 정보 시퀀스
	 */
	private Long eventInfoSeq;
	/**
	 * 라운드
	 */
	private Long round;
	/**
	 * 플레이어 아이디
	 */
	private String playerId;

	private String lastName;

	private String firstName;

	private String steamAvatar;

	private String carName;
	/**
	 * 차 아이디
	 */
	private String carId;
	/**
	 * 레이스 타임
	 */
	private Long raceTime;
	/**
	 * 총 타임
	 */
	private Long totalTime;
	/**
	 * 총 렙
	 */
	private Long totalLap;
	/**
	 * 베스트랩
	 */
	private Long bestLap;
	/**
	 * 섹터1
	 */
	private Long sector1;
	/**
	 * 섹터2
	 */
	private Long sector2;
	/**
	 * 섹터3
	 */
	private Long sector3;
	/**
	 * 순위
	 */
	private Long rank;
	/**
	 * 스코어
	 */
	private Long score;
	/**
	 * 핸디캡
	 */
	private Long handicap;
	/**
	 * 패널티
	 */
	private Long penalty;

	/**
	 * 차량모델
	 */
	private Long carModel;

	/**
	 * 의무 피트 스톱 남은 수
	 */
	private Long missMandatoryPitStop;

	private String participationYn;

}
