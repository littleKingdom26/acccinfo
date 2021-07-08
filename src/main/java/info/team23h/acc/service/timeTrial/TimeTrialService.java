package info.team23h.acc.service.timeTrial;

import info.team23h.acc.vo.front.timeTrial.TimeTrialPlayerDetailVO;
import info.team23h.acc.vo.front.timeTrial.TimeTrialResultVO;

import java.util.List;

public interface TimeTrialService {
	/**
	 * Find gt3 week time trial.
	 *
	 * @param sessionId the session id
	 */
	List<TimeTrialResultVO> findGt3WeekTimeTrial(Long sessionId);

	/**
	 * Find gt3 player id detail.
	 *
	 * @param playerId the player id
	 */
	List<TimeTrialPlayerDetailVO> findGt3PlayerIdDetail(String playerId);


	/**
	 * Find gt4 week time trial list.
	 *
	 * @param sessionId the session id
	 * @return the list
	 */
	List<TimeTrialResultVO> findGt4WeekTimeTrial(Long sessionId);

	/**
	 * Find gt4 player detail object.
	 *
	 * @param playerId the player id
	 * @return the object
	 */
	List<TimeTrialPlayerDetailVO> findGt4PlayerIdDetail(String playerId);

	/**
	 * GT3 트랙별 기록
	 *
	 * @param trackSeq the track seq
	 */
	List<TimeTrialResultVO> findGt3Track(Long trackSeq);

	/**
	 * GT4 트랙별 기록
	 *
	 * @param trackSeq the track seq
	 * @return the list
	 */
	List<TimeTrialResultVO> findGt4Track(Long trackSeq);
}
