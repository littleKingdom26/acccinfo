package info.team23h.acc.dao;

import info.team23h.acc.vo.ViewVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ViewDAO {

	@Autowired
	private SqlSession sqlSession;


	public int updateViewCount() {
		return sqlSession.update("viewSql.updateViewCount");
	}

	public ViewVo getViewCount() {
		return sqlSession.selectOne("viewSql.getViewCount");
	}

	public int loadTodayViewCount() {
		return sqlSession.selectOne("viewSql.loadTodayViewCount");
	}

	public int insertViewCount() {
		return sqlSession.insert("viewSql.insertViewCount");
	}
}
