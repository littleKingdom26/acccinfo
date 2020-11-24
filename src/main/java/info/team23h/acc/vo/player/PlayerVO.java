package info.team23h.acc.vo.player;


import info.team23h.acc.entity.player.Player;
import info.team23h.acc.vo.common.CommonVO;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerVO extends CommonVO implements Comparable<PlayerVO> {


	/** 플레이어 아이디 */
	private String playerId;
	/** 이름 */
	private String firstName;
	/** 성 */
	private String lastName;
	/** 스팀 아바타 **/
	private String steamAvatar;
	/**
	 * tt 스코어
	 */
	private int ttScore;

	/**
	 * 순서
	 */
	private int no;

	public PlayerVO(Player player) {
		this.playerId = player.getPlayerId();
		this.firstName = player.getFirstName();
		this.lastName = player.getLastName();
		this.steamAvatar = player.getSteamAvatar();
		this.regDt = player.getRegDt().format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

	@Override
	public int compareTo(PlayerVO o) {
		double  target = o.getTtScore();
		if(ttScore == target){
			return 0;
		}else if(ttScore > target){
			return 1;
		}else {
			return  -1;
		}
	}


}
