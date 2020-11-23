package info.team23h.acc.vo.team;


import info.team23h.acc.entity.player.Player;
import info.team23h.acc.entity.team.Team;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamResultVO {
	private Long teamSeq;

	private Long teamInfoSeq;

	private Player player;

	public TeamResultVO(Team team) {
		this.teamSeq = team.getTeamSeq();
		this.teamInfoSeq = team.getTeamInfo().getTeamInfoSeq();
		this.player = team.getPlayer();
	}
}
