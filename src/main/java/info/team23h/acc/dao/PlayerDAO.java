package info.team23h.acc.dao;

import info.team23h.acc.vo.PlayerSearch;
import info.team23h.acc.vo.PlayerVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerDAO {

	@Autowired
	private SqlSession sql;

	private final String  PREFIX = "playerSql";

	public List<PlayerVO> getPlayerList(PlayerSearch playerSearch) {
		return sql.selectList(PREFIX+".getPlayerList", playerSearch);
	}

	public int createDriver(PlayerVO playerVO) {
		return sql.insert(PREFIX +".createDriver", playerVO);
	}

	public int getPlayerDetail(PlayerVO playerVO) {
		return sql.selectOne(PREFIX +".getPlayerDetail",playerVO);
	}

	public int updateDriver(PlayerVO playerVO) {
		return  sql.update(PREFIX +".updateDriver", playerVO);
	}
}
