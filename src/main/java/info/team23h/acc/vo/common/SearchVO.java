package info.team23h.acc.vo.common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchVO {

	/**
	 * 주차
	 */
	private String sessionId;

	/**
	 * 트랙 번호
	 */
	private String trackSeq;

	private String playerId;


	private String team23h;

	private String firstName;

	private String lastName;

	private String carModel;

	private String carName;

	private String steamAvatar;

}
