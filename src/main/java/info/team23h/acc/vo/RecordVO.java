package info.team23h.acc.vo;

public class RecordVO extends CommonVO {

	/** 세션 아이디 */
	private String sessionId;
	/**
	 * 플레이어 아이디
	 */
	private String playerId;
	/**
	 * 베스트 랩
	 */
	private int bestLap;
	/**
	 * 섹터 1
	 */
	private int sector1;
	/**
	 * 섹터 2
	 */
	private int sector2;
	/**
	 * 섹터 3
	 */
	private int sector3;

	private int potential;

	/**
	 * 트랙 번호
	 */
	private long trackSeq;
	/**
	 * 차량 모델
	 */
	private String carModel;

	/**
	 * 차량 명
	 */
	private String carName;

	/** 추가 변수 */
	/*
	 * 세션 초기화 정보
	 */
	private String SessionFlag;

	/**
	 * 트랙 명
	 */
	private String trackName;

	/**
	 * 랭킹
	 */
	private int rank;

	/**
	 * 이름
	 */
	private String firstName;

	/**
	 * 성
	 */
	private String lastName;

	/**
	 * 베스트 타임 겝
	 */
	private String gap;

	private String bestLapView;

	private String sector1View;

	private String sector2View;

	private String sector3View;

	private String potentialView;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public int getSector1() {
		return sector1;
	}

	public void setSector1(int sector1) {
		this.sector1 = sector1;
	}

	public int getSector2() {
		return sector2;
	}

	public void setSector2(int sector2) {
		this.sector2 = sector2;
	}

	public int getSector3() {
		return sector3;
	}

	public void setSector3(int sector3) {
		this.sector3 = sector3;
	}

	public long getTrackSeq() {
		return trackSeq;
	}

	public void setTrackSeq(long trackSeq) {
		this.trackSeq = trackSeq;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getSessionFlag() {
		return SessionFlag;
	}

	public void setSessionFlag(String sessionFlag) {
		SessionFlag = sessionFlag;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getBestLap() {
		return bestLap;
	}

	public void setBestLap(int bestLap) {
		this.bestLap = bestLap;
	}

	public String getBestLapView() {
		return bestLapView;
	}

	public void setBestLapView(String bestLapView) {
		this.bestLapView = bestLapView;
	}

	public String getSector1View() {
		return sector1View;
	}

	public void setSector1View(String sector1View) {
		this.sector1View = sector1View;
	}

	public String getSector2View() {
		return sector2View;
	}

	public void setSector2View(String sector2View) {
		this.sector2View = sector2View;
	}

	public String getSector3View() {
		return sector3View;
	}

	public void setSector3View(String sector3View) {
		this.sector3View = sector3View;
	}

	public String getGap() {
		return gap;
	}

	public void setGap(String gap) {
		this.gap = gap;
	}

	public int getPotential() {
		return potential;
	}

	public void setPotential(int potential) {
		this.potential = potential;
	}

	public String getPotentialView() {
		return potentialView;
	}

	public void setPotentialView(String potentialView) {
		this.potentialView = potentialView;
	}

	@Override
	public String toString() {
		return "RecordVO{" + "sessionId='" + sessionId + '\'' + ", playerId='" + playerId + '\'' + ", bestLap=" + bestLap + ", sector1=" + sector1 + ", sector2=" + sector2 + ", sector3=" + sector3 + ", trackSeq=" + trackSeq + ", carModel='" + carModel + '\'' + ", carName='" + carName + '\'' + ", SessionFlag='" + SessionFlag + '\'' + ", trackName='" + trackName + '\'' + ", rank=" + rank + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", gap='" + gap + '\'' + ", bestLapView='" + bestLapView + '\'' + ", sector1View='" + sector1View + '\'' + ", sector2View='" + sector2View + '\'' + ", sector3View='" + sector3View + '\'' + '}';
	}
}
