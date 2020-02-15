package info.team23h.acc.vo;

public class TrackVO {
	/** 시퀀스 */
	private long seq;

	/**
	 * 트랙명
	 */
	private String trackName;


	/**
	 * 선택요소
	 */
	private boolean selected;

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
