package info.team23h.acc.repository.leagueDiv;

import info.team23h.acc.entity.leagueDiv.LeagueDiv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeagueDivRepository extends JpaRepository<LeagueDiv, Long> {
	List<LeagueDiv> findByLeagueDivEquals(String leagueDiv);


}


