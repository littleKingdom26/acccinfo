package info.team23h.acc.vo.player;


import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
