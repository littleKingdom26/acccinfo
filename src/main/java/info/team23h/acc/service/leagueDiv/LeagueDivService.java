package info.team23h.acc.service.leagueDiv;

import info.team23h.acc.vo.leagueDiv.LeagueDivResultVO;
import info.team23h.acc.vo.leagueDiv.LeagueDivSaveVO;

import java.util.List;

public interface LeagueDivService {

	/**
	 * 리그 구분 선수 등록
	 *
	 * @param leagueDivSaveVO the league div save vo
	 */
	void save(LeagueDivSaveVO leagueDivSaveVO);

	List<LeagueDivResultVO> findByLeagueDiv(String name);
}
