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

	public RecordVO getRecordData(RecordVO recordVO) {
		return sql.selectOne("recordSql.getRecordData", recordVO);

	}

	public int createRecordData(RecordVO recordVO) {
		return sql.insert("recordSql.createRecordData", recordVO);
	}

	public int updateRecordData(RecordVO recordVO) {
		return sql.update("recordSql.updateRecordData", recordVO);
	}

	public List<RecordVO> getRecordDataListForWeek(SearchVO searchVO) {
		return sql.selectList("recordSql.getRecordDataListForWeek", searchVO);
	}

	public List<RecordVO> getRecordDataListForTrackSeq(SearchVO searchVO) {
		return sql.selectList("recordSql.getRecordDataListForTrackSeq", searchVO);
	}
}
