package info.team23h.acc.service.score;

import info.team23h.acc.dao.ScoreDAO;
import info.team23h.acc.vo.score.ScoreInfoVO;
import info.team23h.acc.vo.score.ScoreVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	ScoreDAO scoreDAO;

	@Override
	public List<ScoreInfoVO> getScoreInfoList() {
		return scoreDAO.getScoreInfoList();
	}

	@Override
	@Transactional
	public int insertScoreInfo(ScoreInfoVO scoreInfoVO) {
		if(scoreInfoVO.getParticipationYn()== null){
			scoreInfoVO.setParticipationYn("N");
		}
		int cnt = scoreDAO.insertScoreInfo(scoreInfoVO);
		for(int i = 0; i < scoreInfoVO.getRanks().size(); i++){
			ScoreVO scoreVO = new ScoreVO();
			if(!scoreInfoVO.getRanks().get(i).isEmpty() && !scoreInfoVO.getScores().get(i).isEmpty()){
				scoreVO.setRank(Integer.parseInt(scoreInfoVO.getRanks().get(i)));
				scoreVO.setScore(Integer.parseInt(scoreInfoVO.getScores().get(i)));
				scoreVO.setScoreInfoSeq(scoreInfoVO.getScoreInfoSeq());
				int scoreCnt = scoreDAO.insertScore(scoreVO);
			}
		}
		return cnt;
	}

	@Override
	public List<ScoreVO> getScore(ScoreInfoVO scoreInfoVO) {
		return scoreDAO.getScore(scoreInfoVO);
	}

	@Override
	public ScoreInfoVO getScoreInfo(ScoreInfoVO scoreInfoVO) {
		ScoreInfoVO result = scoreDAO.getScoreInfo(scoreInfoVO);
		if("Y".equals(result.getParticipationYn())){
			result.setDefaultPointView("Y");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> delScoreInfo(ScoreInfoVO scoreInfoVO) throws Exception {

		int cnt = scoreDAO.delScoreInfo(scoreInfoVO);

		if(cnt > 0){
			cnt = scoreDAO.delScore(scoreInfoVO);
		}

		HashMap<String, Object> result = new HashMap<>();
		if(cnt == 0){
			throw new Exception();
		}else{
			result.put("code", "0000");
		}
		return result;
	}
}
