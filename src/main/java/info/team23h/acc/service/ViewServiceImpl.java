package info.team23h.acc.service;

import info.team23h.acc.dao.ViewDAO;
import info.team23h.acc.vo.ViewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewServiceImpl implements ViewService {
	@Autowired
	ViewDAO viewDAO;

	@Override
	public int updateViewCount() {
		return viewDAO.updateViewCount();
	}

	@Override
	public ViewVo getViewCount() {
		return viewDAO.getViewCount();
	}
}
