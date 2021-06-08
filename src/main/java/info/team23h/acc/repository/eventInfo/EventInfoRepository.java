package info.team23h.acc.repository.eventInfo;

import info.team23h.acc.entity.event.EventInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventInfoRepository extends JpaRepository<EventInfo,Long>, EventInfoRepositoryCustom {


	List<EventInfo> findAllByAndDivisionAndRegDtBetweenOrderByRegDtDesc(String division , LocalDateTime startDt, LocalDateTime endDt);
}
