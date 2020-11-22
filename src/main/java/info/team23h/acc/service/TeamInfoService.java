package info.team23h.acc.service;


import info.team23h.acc.entity.team.TeamInfo;
import info.team23h.acc.vo.team.TeamInfoSaveVO;



public interface TeamInfoService {

	TeamInfo save(TeamInfoSaveVO teamInfoSaveVO);
}
