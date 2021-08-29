package info.team23h.acc.service.week;

import info.team23h.acc.dao.WeekDAO;
import info.team23h.acc.entity.week.Week;
import info.team23h.acc.repository.week.WeekRepository;
import info.team23h.acc.vo.common.SearchVO;
import info.team23h.acc.vo.front.timeTrial.WeekResultVO;
import info.team23h.acc.vo.track.TrackVO;
import info.team23h.acc.vo.week.WeekVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeekServiceImpl implements WeekService {
	@Autowired
	private WeekDAO weekDAO;

	@Autowired
	private WeekRepository weekRepository;

	@Override
	public WeekVO getRecently() {
		return weekDAO.getRecently();
	}


	@Override
	public void setSessionId(TrackVO trackVO,
							 int sessionID) {
		// 과거 세션 end DT 넣어주기
		weekDAO.setLastSessionEnd(sessionID);
		// 새로운 세션 start DT 넣어주기
		WeekVO weekVO = new WeekVO();
		weekVO.setTrackSeq(trackVO.getSeq());
		weekVO.setSessionId(sessionID);
		weekDAO.setNewSession(weekVO);
	}

	@Override
	public List<WeekVO> getWeekList(SearchVO searchVO) {
		List<WeekVO> weekList = weekDAO.getWeekList();
		for(int i = 0; i < weekList.size(); i++){
			WeekVO temp = weekList.get(i);
			if(searchVO.getSessionId().equals(String.valueOf(temp.getSessionId()))){
				temp.setSelected(true);
			}
		}
		return weekList;
	}

	@Override
	public List<WeekResultVO> findWeekList() {
		final List<Week> all = weekRepository.findAll();
		final List<WeekResultVO> result = all.stream().sorted(Comparator.comparingLong(Week::getSessionId).reversed()).map(WeekResultVO::new).collect(Collectors.toList());

		return result;
	}
}
