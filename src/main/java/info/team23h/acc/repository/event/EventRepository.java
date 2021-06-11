package info.team23h.acc.repository.event;

import info.team23h.acc.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> ,EventRepositoryCustom{


	List<Event> findAllByEventInfoSeqAndRoundOrderByRankAsc(Long eventInfoSeq,Long round);


	List<Event> findAllByEventInfoSeqOrderByRoundAsc(Long eventInfoSeq);
}
