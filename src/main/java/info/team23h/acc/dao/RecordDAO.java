package info.team23h.acc.dao;

import info.team23h.acc.vo.common.SearchVO;
import info.team23h.acc.vo.recode.RecordVO;
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

	public RecordVO getRecordData_GT4(RecordVO recordVO) {
		return sql.selectOne(PREFIX + ".getRecordData_GT4",recordVO);
	}

	public int createRecordData(RecordVO recordVO) {
		return sql.insert(PREFIX +".createRecordData", recordVO);
	}

	public int createRecordData_GT4(RecordVO recordVO) {
		return sql.insert(PREFIX + ".createRecordData_GT4",recordVO);
	}

	public int updateRecordData(RecordVO recordVO) {
		return sql.update(PREFIX +".updateRecordData", recordVO);
	}

	public int updateRecordData_GT4(RecordVO recordVO) {
		return sql.update(PREFIX + ".updateRecordData_GT4",recordVO);
	}

	public List<RecordVO> getRecordDataListForWeek(SearchVO searchVO) {
		return sql.selectList(PREFIX +".getRecordDataListForWeek", searchVO);
	}

	public List<RecordVO> getRecordDataListForWeek_GT4(SearchVO searchVO) {
		return sql.selectList(PREFIX + ".getRecordDataListForWeek_GT4",searchVO);
	}

	public List<RecordVO> getRecordDataListForTrackSeq(SearchVO searchVO) {
		return sql.selectList(PREFIX +".getRecordDataListForTrackSeq", searchVO);
	}

	public List<RecordVO> getRecordDataListForTrackSeq_GT4(SearchVO searchVO) {

		return sql.selectList(PREFIX + ".getRecordDataListForTrackSeq_GT4",searchVO);
	}

	public List<RecordVO> recordPlayerDetail(SearchVO searchVO) {
		return sql.selectList(PREFIX +".recordPlayerDetail", searchVO);
	}

	public List<RecordVO> recordPlayerDetail_GT4(SearchVO searchVO) {
		return sql.selectList(PREFIX + ".recordPlayerDetail_GT4",searchVO);
	}

	public List<RecordVO> loadAllRecodeTrackData() {
		return sql.selectList(PREFIX + ".loadAllRecodeTrackData");
	}

	public List<RecordVO> loadAllRecodeTrackData_GT4() {
		return sql.selectList(PREFIX + ".loadAllRecodeTrackData_GT4");
	}


	public List<RecordVO> recordCarDetail(SearchVO searchVO) {
		return sql.selectList(PREFIX + ".recordCarDetail", searchVO);
	}

	public List<RecordVO> recordCarDetail_GT4(SearchVO searchVO) {
		return sql.selectList(PREFIX + ".recordCarDetail_GT4",searchVO);
	}
}
