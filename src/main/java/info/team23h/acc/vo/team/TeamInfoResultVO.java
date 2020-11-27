package info.team23h.acc.vo.team;

import info.team23h.acc.entity.team.Team;
import info.team23h.acc.entity.team.TeamInfo;
import info.team23h.acc.vo.common.CommonVO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamInfoResultVO  extends CommonVO {

	private Long teamInfoSeq;

	private String teamName;

	private List<Team> team;

	public TeamInfoResultVO(TeamInfo teamInfo) {
		this.teamInfoSeq = teamInfo.getTeamInfoSeq();
		this.teamName = teamInfo.getTeamName();
		this.team = teamInfo.getTeam();
	}

	public int getTeamCount(){

		int i = 0;
		for(Team temp : team){
			if("N".equals(temp.getDelYn())){
				i++;
			}
		}
		return i;
	}

}
