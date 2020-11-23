package info.team23h.acc.service.view;

import info.team23h.acc.dao.ViewDAO;
import info.team23h.acc.vo.common.ViewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewServiceImpl implements ViewService {
	@Autowired
	ViewDAO viewDAO;

	@Override
	public int updateViewCount() {

		// 오늘날짜 있는지 체크
		int cnt = viewDAO.loadTodayViewCount();

		if(cnt > 0){
			// 있으면 업데이트
			viewDAO.updateViewCount();
		}else{
			// 없으면 인서트
			cnt = viewDAO.insertViewCount();
		}
		return cnt;
	}

	@Override
	public ViewVo getViewCount() {
		return viewDAO.getViewCount();
	}
}
