package info.team23h.acc.repository.event;

import info.team23h.acc.entity.event.Event;
import info.team23h.acc.entity.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> ,EventRepositoryCustom{


	List<Event> findAllByEventInfoSeqAndRoundOrderByRankAsc(Long eventInfoSeq,Long round);


	List<Event> findAllByEventInfoSeqOrderByRoundAsc(Long eventInfoSeq);


	List<Event> findAllByEventInfoSeqIn(Iterable<Long> eventInfoSeq);

	Optional<Event> findByEventInfoSeqAndRoundAndPlayer(Long eventInfoSeq, Long round, Player player);

	List<Event> findAllByEventInfoSeqAndRound(Long eventInfoSeq, Long round);
}
