package info.team23h.acc.service.status;

import info.team23h.acc.vo.status.StatusSearch;
import info.team23h.acc.vo.status.StatusVO;

import java.util.List;

public interface StatusService {
	List<StatusVO> getCarStatus(StatusSearch statusSearch);
}
