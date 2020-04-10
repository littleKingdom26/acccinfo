package info.team23h.acc.dao;

import info.team23h.acc.vo.RecordVO;
import info.team23h.acc.vo.SearchVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecordDAO {

	@Autowired
	private SqlSession sql;

	private final String PREFIX = "recordSql";

	public RecordVO getRecordData(RecordVO recordVO) {
		return sql.selectOne(PREFIX+".getRecordData", recordVO);
	}

	public int createRecordData(RecordVO recordVO) {
		return sql.insert(PREFIX +".createRecordData", recordVO);
	}

	public int updateRecordData(RecordVO recordVO) {
		return sql.update(PREFIX +".updateRecordData", recordVO);
	}

	public List<RecordVO> getRecordDataListForWeek(SearchVO searchVO) {
		return sql.selectList(PREFIX +".getRecordDataListForWeek", searchVO);
	}

	public List<RecordVO> getRecordDataListForTrackSeq(SearchVO searchVO) {
		return sql.selectList(PREFIX +".getRecordDataListForTrackSeq", searchVO);
	}

	public List<RecordVO> recordPlayerDetail(SearchVO searchVO) {
		return sql.selectList(PREFIX +".recordPlayerDetail", searchVO);
	}

	public List<RecordVO> loadAllRecodeTrackData() {
		return sql.selectList(PREFIX + ".loadAllRecodeTrackData");
	}
}
