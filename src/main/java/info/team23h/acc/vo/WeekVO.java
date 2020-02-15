package info.team23h.acc.vo;

public class WeekVO extends CommonVO {

	/**
	 * 주차 시퀀스
	 */
	private long seq;

	/**
	 * 세션 아이디
	 */
	private int sessionId;
	/**
	 * 트랙 시퀀스
	 */
	private long trackSeq;
	/**
	 * 시작 날짜
	 */
	private String startDt;
	/**
	 * 끝 날짜
	 */
	private String endDt;

	/**
	 * 옵션 체크 유무
	 */
	private boolean selected;

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public long getTrackSeq() {
		return trackSeq;
	}

	public void setTrackSeq(long trackSeq) {
		this.trackSeq = trackSeq;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
