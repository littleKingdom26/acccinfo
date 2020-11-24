package info.team23h.acc.repository.event;

import info.team23h.acc.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> ,EventRepositoryCustom{

}
