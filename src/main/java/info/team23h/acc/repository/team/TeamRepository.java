package info.team23h.acc.repository.team;

import info.team23h.acc.entity.team.Team;
import info.team23h.acc.entity.team.TeamInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

	List<Team> findByTeamInfo(TeamInfo teamInfo);
}
