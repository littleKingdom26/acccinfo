package info.team23h.acc.vo.penalty;

import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PenaltyVO extends CommonVO {
	/**
	 * 패널티 시퀀스
	 */
	private int penaltySeq;
	/**
	 * 이벤트 정보 시퀀스
	 */
	private int eventInfoSeq;

	/**
	 * 라운드
	 */
	private int round;
	/**
	 *
	 */
	private String playerId;
	/**
	 *
	 */
	private int addTime;
	/**
	 *
	 */
	private String carId;
	/**
	 *
	 */
	private String reason;
	/**
	 *
	 */
	private String penalty;

	private String firstName;

	private String lastName;

	private String steamAvatar;
}
