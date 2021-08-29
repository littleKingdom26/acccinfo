package info.team23h.acc.repository.leagueDiv;

import info.team23h.acc.entity.leagueDiv.LeagueDiv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeagueDivRepository extends JpaRepository<LeagueDiv, Long> {
	List<LeagueDiv> findByLeagueDivEquals(String leagueDiv);

	Optional<LeagueDiv> findByPlayerIdEquals(String playerId);

}


