package info.team23h.acc.vo.team;

import info.team23h.acc.entity.event.EventMeta;
import info.team23h.acc.entity.team.Team;
import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamScoreSaveVO extends CommonVO {

	private Long round;

	private Long eventInfoSeq;

	private Long score;

	private Team team;

	//private EventInfo eventInfo;

	private EventMeta eventMeta;

}
