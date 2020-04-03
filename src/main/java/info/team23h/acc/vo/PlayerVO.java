package info.team23h.acc.vo;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerVO extends CommonVO implements Comparable<PlayerVO> {


	/** 플레이어 아이디 */
	public String playerId;
	/** 이름 */
	public String firstName;
	/** 성 */
	public String lastName;

	/**
	 * tt 스코어
	 */
	public int ttScore;

	/**
	 * 순서
	 */
	public int no;

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
