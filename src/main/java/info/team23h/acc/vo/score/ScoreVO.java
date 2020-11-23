package info.team23h.acc.vo.score;

import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ScoreVO extends CommonVO {

	/**
	 *  스코어 정보 시퀀스
	 */
	private int scoreInfoSeq;
	/**
	 * 순위
	 */
	private int rank;
	/**
	 * 점수
	 */
	private int score;

	/**
	 * 랭킹 배열
	 */
	private List<String> ranks;

	/**
	 * 스코어 배열
	 */
	private List<String> scores;
}
