package info.team23h.acc.repository.recode;

import info.team23h.acc.entity.recode.Record;
import info.team23h.acc.entity.week.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Long>, RecordRepositoryCustom {

	List<Record> findAllByWeek(Week week);

	List<Record> findAllByTrack_SeqIn(Iterable<Long> trackList);


	List<Record> findAllByTrack_Seq(Long trackSeq);
}
