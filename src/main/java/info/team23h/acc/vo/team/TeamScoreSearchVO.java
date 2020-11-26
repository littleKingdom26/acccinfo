package info.team23h.acc.vo.team;

import info.team23h.acc.entity.team.TeamScore;
import info.team23h.acc.vo.common.CommonVO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamScoreSearchVO extends CommonVO {

	private String eventDt;

	public TeamScoreSearchVO(TeamScore teamScore) {
		this.eventDt = teamScore.getEventDt();
	}
}
