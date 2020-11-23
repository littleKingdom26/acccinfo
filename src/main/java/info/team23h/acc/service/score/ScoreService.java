package info.team23h.acc.service.score;

import info.team23h.acc.vo.score.ScoreInfoVO;
import info.team23h.acc.vo.score.ScoreVO;

import java.util.HashMap;
import java.util.List;

public interface ScoreService {
	/**
	 * 스코어 정보 리스트 조회
	 * @return
	 */
	List<ScoreInfoVO> getScoreInfoList();

	/**
	 * 스코어 정보 입력
	 * @param scoreInfoVO
	 * @return
	 */
	int insertScoreInfo(ScoreInfoVO scoreInfoVO);

	/**
	 * 스코어 조회
	 * @param scoreInfoVO
	 * @return
	 */
	List<ScoreVO> getScore(ScoreInfoVO scoreInfoVO);

	/**
	 * 스코어 정보 조회
	 * @param scoreInfoVO
	 * @return
	 */
	ScoreInfoVO getScoreInfo(ScoreInfoVO scoreInfoVO);

	/**
	 * 스코어 정보 삭제
	 * @param scoreInfoVO
	 * @return
	 */
	HashMap<String, Object> delScoreInfo(ScoreInfoVO scoreInfoVO) throws Exception;
}
