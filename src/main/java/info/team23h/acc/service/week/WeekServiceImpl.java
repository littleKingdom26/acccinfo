package info.team23h.acc.service.week;

import info.team23h.acc.dao.WeekDAO;
import info.team23h.acc.vo.common.SearchVO;
import info.team23h.acc.vo.track.TrackVO;
import info.team23h.acc.vo.week.WeekVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeekServiceImpl implements WeekService {
	@Autowired
	WeekDAO weekDAO;

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
}
