package info.team23h.acc.dao;

import info.team23h.acc.vo.ScoreInfoVO;
import info.team23h.acc.vo.ScoreVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScoreDAO {
	@Autowired
	private SqlSession sql;

	private final String PREFIX = "scoreSql";

	public List<ScoreInfoVO> getScoreInfoList() {
		return sql.selectList(PREFIX+".getScoreInfoList");
	}

	public int insertScoreInfo(ScoreInfoVO scoreInfoVO) {
		return sql.insert(PREFIX+".insertScoreInfo", scoreInfoVO);
	}

	public int insertScore(ScoreVO scoreVO) {
		return sql.insert(PREFIX + ".insertScore", scoreVO);
	}

	public List<ScoreVO> getScore(ScoreInfoVO scoreInfoVO) {
		return sql.selectList(PREFIX+".getScore", scoreInfoVO);
	}

	public ScoreInfoVO getScoreInfo(ScoreInfoVO scoreInfoVO) {
		return sql.selectOne(PREFIX + ".getScoreInfo", scoreInfoVO);
	}

	public int delScoreInfo(ScoreInfoVO scoreInfoVO) {
		return sql.delete(PREFIX+".delScoreInfo", scoreInfoVO);
	}

	public int delScore(ScoreInfoVO scoreInfoVO) {
		return sql.delete(PREFIX + ".delScore", scoreInfoVO);
	}
}
