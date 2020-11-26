package info.team23h.acc.repository.teamScore;

import info.team23h.acc.entity.event.EventInfo;
import info.team23h.acc.entity.team.Team;
import info.team23h.acc.entity.team.TeamScore;
import info.team23h.acc.vo.team.TeamScoreSearchVO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamScoreRepository extends JpaRepository<TeamScore, Long> , TeamScoreRepositoryCustom {

	Optional<TeamScore> findByEventInfoAndRoundAndTeam(EventInfo eventInfo, Long round, Team team );


	List<TeamScore> findDistinctByEventDt(String eventDt);


}
