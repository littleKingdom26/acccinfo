package info.team23h.acc.service;


import info.team23h.acc.entity.team.TeamInfo;
import info.team23h.acc.vo.team.TeamInfoResultVO;
import info.team23h.acc.vo.team.TeamInfoSaveVO;

import java.util.List;


public interface TeamInfoService {

	TeamInfo save(TeamInfoSaveVO teamInfoSaveVO);

	List<TeamInfoResultVO> findAll();

	void delete(Long teamInfoSeq) throws Exception;
}
