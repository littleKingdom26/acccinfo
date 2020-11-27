package info.team23h.acc.repository.eventMata;

import info.team23h.acc.entity.event.EventMeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventMetaRepository extends JpaRepository<EventMeta, Long> {


	Optional<EventMeta> findByEventInfoSeqAndRound(Long id,Long round);
}
