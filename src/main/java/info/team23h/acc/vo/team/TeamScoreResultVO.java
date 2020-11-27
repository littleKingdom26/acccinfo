package info.team23h.acc.vo.team;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamScoreResultVO {

	private TeamInfoResultVO TeamInfoResultVO;

	List<TeamScoreResultDetailVO> teamScoreResultDetailList;
}
