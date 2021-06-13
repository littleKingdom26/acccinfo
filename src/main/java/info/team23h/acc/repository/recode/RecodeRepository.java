package info.team23h.acc.repository.recode;

import info.team23h.acc.entity.recode.Record;
import info.team23h.acc.entity.week.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecodeRepository extends JpaRepository<Record,Long> {

	List<Record> findAllByWeek(Week week);
}
