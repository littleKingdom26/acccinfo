package info.team23h.acc.service.timeTrial;

import info.team23h.acc.vo.front.timeTrial.TimeTrialResultVO;

import java.util.List;

public interface TimeTrialService {
	/**
	 * Find gt 3 week time trial.
	 *
	 * @param sessionId the session id
	 */
	List<TimeTrialResultVO> findGt3WeekTimeTrial(Long sessionId);
}
