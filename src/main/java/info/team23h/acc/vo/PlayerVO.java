package info.team23h.acc.vo;


public class PlayerVO extends CommonVO {


	/** 플레이어 아이디 */
	public String playerId;
	/** 이름 */
	public String firstName;
	/** 성 */
	public String lastName;

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
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
}
