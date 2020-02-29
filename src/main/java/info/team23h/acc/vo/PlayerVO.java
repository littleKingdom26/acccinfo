package info.team23h.acc.vo;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerVO extends CommonVO {


	/** 플레이어 아이디 */
	public String playerId;
	/** 이름 */
	public String firstName;
	/** 성 */
	public String lastName;

}
