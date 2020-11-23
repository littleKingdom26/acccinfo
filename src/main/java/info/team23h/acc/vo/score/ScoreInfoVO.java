package info.team23h.acc.vo.score;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScoreInfoVO  extends ScoreVO{

	/**
	 * 스코어 정보 시퀀스
	 */
	private int scoreInfoSeq;
	/**
	 * 제목
	 */
	private String title;
	/**
	 * 기본 점수
	 */
	private String participationYn;

	/**
	 * 기본점수 프론트 뷰
	 */
	private String defaultPointView;
}
