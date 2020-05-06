package info.team23h.acc.service;

import info.team23h.acc.vo.StatusSearch;
import info.team23h.acc.vo.StatusVO;

import java.util.List;

public interface StatusService {
	List<StatusVO> getCarStatus(StatusSearch statusSearch);
}
