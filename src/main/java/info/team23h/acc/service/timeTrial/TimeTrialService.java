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
}
