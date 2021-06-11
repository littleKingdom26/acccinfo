package info.team23h.acc.repository.eventSub;


import info.team23h.acc.entity.event.EventSub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventSubRepository extends JpaRepository<EventSub,Long> {

	List<EventSub> findAllByEventInfoSeqAndCarIdAndRoundOrderByLapAsc(Long eventInfoSeq,String carId,Long round);
}
