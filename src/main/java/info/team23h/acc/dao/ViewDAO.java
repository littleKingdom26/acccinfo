package info.team23h.acc.dao;

import info.team23h.acc.vo.ViewVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ViewDAO {

	@Autowired
	private SqlSession sqlSession;

	private final String PREFIX = "viewSql";

	public int updateViewCount() {
		return sqlSession.update(PREFIX+".updateViewCount");
	}

	public ViewVo getViewCount() {
		return sqlSession.selectOne(PREFIX+".getViewCount");
	}

	public int loadTodayViewCount() {
		return sqlSession.selectOne(PREFIX+".loadTodayViewCount");
	}

	public int insertViewCount() {
		return sqlSession.insert(PREFIX+".insertViewCount");
	}
}
