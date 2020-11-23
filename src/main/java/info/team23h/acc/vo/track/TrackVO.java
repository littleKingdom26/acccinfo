package info.team23h.acc.vo.track;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TrackVO {
	/** 시퀀스 */
	private long seq;

	/**
	 * 트랙명
	 */
	private String trackName;

	/**
	 * 트랙 뷰 네임
	 */
	private String trackViewName;


	/**
	 * 선택요소
	 */
	private boolean selected;


}
