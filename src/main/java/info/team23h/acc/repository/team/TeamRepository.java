package info.team23h.acc.repository.team;

import info.team23h.acc.entity.player.Player;
import info.team23h.acc.entity.team.Team;
import info.team23h.acc.entity.team.TeamInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

	List<Team> findByTeamInfoAndDelYn(TeamInfo teamInfo,String delYn);

	Optional<Team> findByPlayerAndDelYn(Player player,String delYn);


}
