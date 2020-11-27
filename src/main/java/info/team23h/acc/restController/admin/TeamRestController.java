package info.team23h.acc.restController.admin;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.config.variable.CommonResponse;
import info.team23h.acc.entity.team.TeamInfo;
import info.team23h.acc.model.response.CommonResult;
import info.team23h.acc.service.event.EventService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.service.team.TeamService;
import info.team23h.acc.service.teamInfo.TeamInfoService;
import info.team23h.acc.service.teamScore.TeamScoreService;
import info.team23h.acc.vo.team.TeamInfoSaveVO;
import info.team23h.acc.vo.team.TeamResultVO;
import info.team23h.acc.vo.team.TeamSaveVO;
import info.team23h.acc.vo.team.TeamScoreSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/team")
public class TeamRestController {

	final ResponseService responseService;

	final TeamInfoService teamInfoService;

	final TeamService teamService;

	final EventService eventService;

	final TeamScoreService teamScoreService;

	@DeleteMapping("/teamInfo/delete/{teamInfoSeq}")
	public CommonResult teamInfoDel(@PathVariable("teamInfoSeq") Long teamInfoSeq) {
		try{
			teamInfoService.delete(teamInfoSeq);
			return responseService.getSuccessResult();
		}catch(Exception e){
			log.debug("e.getMessage() > {}", e.getMessage());
			return responseService.getFailResult();
		}
	}

	@PostMapping(value = "/teamInfo/save")
	public CommonResult teamInfoSave(@ModelAttribute TeamInfoSaveVO teamInfoSaveVO) {
		if("".equals(teamInfoSaveVO.getTeamName())){
			return responseService.getFailResult(CommonResponse.FAIL.getCode(),"팀 이름을 입력하세요");
		}else{
			TeamInfo save = teamInfoService.save(teamInfoSaveVO);
			return responseService.getSuccessResult();
		}
	}

	@DeleteMapping("/team/delete/{teamSeq}")
	public CommonResult teamDelete(@PathVariable("teamSeq") Long teamSeq) {
		try{
			teamService.delete(teamSeq);
			return responseService.getSuccessResult();
		}catch(Exception e){
			log.debug("e.getMessage() > {}", e.getMessage());
			return responseService.getFailResult();
		}
	}

	@PostMapping(value = "/team/save")
	public CommonResult teamSave(@ModelAttribute TeamSaveVO teamSaveVO) {
		try{
			if(teamSaveVO.getTeamInfoSeq() == null || teamSaveVO.getTeamInfoSeq() == 0){
				return responseService.getFailResult(CommonResponse.FAIL.getCode(), "팀선택을 먼저 하시기 바랍니다.");
			}
			if(teamSaveVO.getPlayerId() == null || "".equals(teamSaveVO.getPlayerId())){
				return responseService.getFailResult(CommonResponse.FAIL.getCode(), "드라이버를 선택하시기 바랍니다.");
			}
			TeamResultVO save = teamService.save(teamSaveVO);
			return responseService.getSingleResult(save);
		}catch(Team23hException e){
			return responseService.getFailResult(CommonResponse.FAIL.getCode(), e.getMessage());
		}catch(Exception e){
			return responseService.getFailResult(CommonResponse.FAIL.getCode(), "저장 실패하였습니다. 관리자에게 문의하세요.");
		}
	}

	@PostMapping(value="/teamScore/save")
	public CommonResult teamScoreSave(@ModelAttribute TeamScoreSaveVO teamScoreSaveVO){
		log.debug("teamScoreSaveVO.toString() > {}", teamScoreSaveVO.toString());
		try{
			teamScoreService.save(teamScoreSaveVO);
			return responseService.getSuccessResult();
		}catch( Exception e){
			return responseService.getFailResult();
		}

	}


}
