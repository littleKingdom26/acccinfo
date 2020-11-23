package info.team23h.acc.dao;

import info.team23h.acc.vo.week.WeekVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WeekDAO {

	@Autowired
	private SqlSession sqlSession;

	private final String PREFIX = "weekSql";

	public WeekVO getRecently() {
		return sqlSession.selectOne(PREFIX+".getRecently");
	}

	public int setLastSessionEnd(int sessionID) {
		return sqlSession.update(PREFIX+".setLastSessionEnd", sessionID);
	}

	public int setNewSession(WeekVO weekVO) {
		return sqlSession.insert(PREFIX +".setNewSession", weekVO);
	}

	public List<WeekVO> getWeekList() {
		return sqlSession.selectList(PREFIX +".getWeekList");
	}
}
