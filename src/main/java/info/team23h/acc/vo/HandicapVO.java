package info.team23h.acc.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HandicapVO extends CommonVO{

	/**
	 * 핸디캡 정보 시퀀스
	 */
	private int handicapInfoSeq;
	/**
	 * 순위
	 */
	private int rank;
	/**
	 * 핸디캡
	 */
	private int handicap;

	/**
	 * 랭킹 배열
	 */
	private List<String> ranks;

	/**
	 * 핸디캡 배열
	 */
	private List<String> handicaps;

}
