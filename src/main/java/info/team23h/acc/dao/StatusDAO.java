package info.team23h.acc.dao;

import info.team23h.acc.vo.StatusSearch;
import info.team23h.acc.vo.StatusVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusDAO {

	@Autowired
	private SqlSession sql;

	private final String PREFIX = "statusSql";

	public List<StatusVO> getCarStatus(StatusSearch statusSearch) {
		return sql.selectList(PREFIX+".getCarStatus",statusSearch);
	}
}
