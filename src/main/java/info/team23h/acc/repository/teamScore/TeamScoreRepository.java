package info.team23h.acc.repository.teamScore;

import info.team23h.acc.entity.event.EventMeta;
import info.team23h.acc.entity.team.Team;
import info.team23h.acc.entity.team.TeamScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamScoreRepository extends JpaRepository<TeamScore, Long> , TeamScoreRepositoryCustom {

	Optional<TeamScore> findByEventMetaAndTeam(EventMeta eventMeta, Team team);


}
