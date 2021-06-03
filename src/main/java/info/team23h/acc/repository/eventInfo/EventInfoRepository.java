package info.team23h.acc.repository.eventInfo;

import info.team23h.acc.entity.event.EventInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventInfoRepository extends JpaRepository<EventInfo,Long>, EventInfoRepositoryCustom {


}
