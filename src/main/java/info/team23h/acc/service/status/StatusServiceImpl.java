package info.team23h.acc.service.status;

import info.team23h.acc.dao.StatusDAO;
import info.team23h.acc.vo.status.StatusSearch;
import info.team23h.acc.vo.status.StatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService{

	@Autowired
	StatusDAO statusDAO;

	@Override
	public List<StatusVO> getCarStatus(StatusSearch statusSearch) {
		List <StatusVO> result = statusDAO.getCarStatus(statusSearch);
		return result;
	}
}
