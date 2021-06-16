package info.team23h.acc.repository.player;

import info.team23h.acc.entity.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player,String> {

	List<Player> findAllByFirstNameContainsOrLastNameContains(String firstName,String lastName);


}
