package info.team23h.acc.vo;

public class SearchVO {

	/**
	 * 주차
	 */
	private String sessionId;

	/**
	 * 트랙 번호
	 */
	private String trackSeq;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTrackSeq() {
		return trackSeq;
	}

	public void setTrackSeq(String trackSeq) {
		this.trackSeq = trackSeq;
	}

	@Override
	public String toString() {
		return "SearchVO{" + "sessionId='" + sessionId + '\'' + ", trackSeq='" + trackSeq + '\'' + '}';
	}
}
