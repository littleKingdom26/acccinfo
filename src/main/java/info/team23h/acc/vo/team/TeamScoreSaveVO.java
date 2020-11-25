package info.team23h.acc.vo.team;

import info.team23h.acc.entity.team.Team;
import info.team23h.acc.entity.team.TeamInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeamScoreSaveVO  {

	private Long round;

	private Long eventInfoSeq;

	private Long score;

	private Team team;

	private TeamInfo teamInfo;


}
