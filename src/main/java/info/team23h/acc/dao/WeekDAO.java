package info.team23h.acc.dao;

import info.team23h.acc.vo.WeekVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WeekDAO {

	@Autowired
	private SqlSession sqlSession;


	public WeekVO getRecently() {
		return sqlSession.selectOne("weekSql.getRecently");
	}

	public int setLastSessionEnd(int sessionID) {
		return sqlSession.update("weekSql.setLastSessionEnd", sessionID);
	}

	public int setNewSession(WeekVO weekVO) {
		return sqlSession.insert("weekSql.setNewSession", weekVO);
	}

	public List<WeekVO> getWeekList() {
		return sqlSession.selectList("weekSql.getWeekList");
	}
}
