package info.team23h.acc.repository.recode;

import info.team23h.acc.entity.recode.RecordGt4;
import info.team23h.acc.entity.week.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordGt4Repository extends JpaRepository<RecordGt4,Long> {

	List<RecordGt4> findAllByWeek(Week week);

	List<RecordGt4> findAllByTrack_SeqIn(Iterable<Long> trackList);

	List<RecordGt4> findAllByTrack_Seq(Long trackSeq);
}
