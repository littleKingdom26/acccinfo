package info.team23h.acc.dao;

import info.team23h.acc.vo.handicap.HandicapInfoVO;
import info.team23h.acc.vo.handicap.HandicapVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HandicapDAO {
	@Autowired
	private SqlSession sql;

	private final String PREFIX = "handicapSql";

	public List<HandicapInfoVO> getHandicapInfoList() {
		return sql.selectList(PREFIX+".getHandicapInfoList");
	}

	public int insertHandicapInfo(HandicapInfoVO handicapInfoVO) {
		return sql.insert(PREFIX+".insertHandicapInfo", handicapInfoVO);
	}

	public int insertHandicap(HandicapVO handicapVO) {
		return sql.insert(PREFIX + ".insertHandicap", handicapVO);
	}

	public List<HandicapVO> getHandicap(HandicapInfoVO handicapInfoVO) {
		return sql.selectList(PREFIX+".getHandicap", handicapInfoVO);
	}

	public HandicapInfoVO getHandicapInfo(HandicapInfoVO handicapInfoVO) {
		return sql.selectOne(PREFIX + ".getHandicapInfo", handicapInfoVO);
	}

	public int delHandicapInfo(HandicapInfoVO handicapInfoVO) {
		return sql.delete(PREFIX+".delHandicapInfo", handicapInfoVO);
	}

	public int delHandicap(HandicapInfoVO handicapInfoVO) {
		return sql.delete(PREFIX + ".delHandicap", handicapInfoVO);
	}
}
