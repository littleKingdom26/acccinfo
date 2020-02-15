package info.team23h.acc.dao;

import info.team23h.acc.vo.PlayerVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerDAO {

	@Autowired
	private SqlSession sql;


	public List<PlayerVO> getPlayerList() {
		return sql.selectList("playerSql.getPlayerList");
	}

	public int createDriver(PlayerVO playerVO) {
		return sql.insert("playerSql.createDriver", playerVO);
	}

	public int getPlayerDetail(PlayerVO playerVO) {
		return sql.selectOne("playerSql.getPlayerDetail",playerVO);
	}

	public int updateDriver(PlayerVO playerVO) {
		return  sql.update("playerSql.updateDriver", playerVO);
	}
}
