package info.team23h.acc.restController.admin;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.config.variable.CommonResponse;
import info.team23h.acc.entity.team.Team;
import info.team23h.acc.entity.team.TeamInfo;
import info.team23h.acc.model.response.CommonResult;
import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.event.EventService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.service.team.TeamService;
import info.team23h.acc.service.teamInfo.TeamInfoService;
import info.team23h.acc.vo.event.EventResultVO;
import info.team23h.acc.vo.team.TeamInfoSaveVO;
import info.team23h.acc.vo.team.TeamResultVO;
import info.team23h.acc.vo.team.TeamSaveVO;
import info.team23h.acc.vo.team.TeamScoreSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/team")
public class TeamRestController {

	final ResponseService responseService;

	final TeamInfoService teamInfoService;

	final TeamService teamService;

	final EventService eventService;

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

		// 1. 이벤트 조회
		List<EventResultVO> eventResultList =  eventService.findByEventList(teamScoreSaveVO);

		// 2. 팀원 조회
		// 3. 1 / 2 번조회한곳에서 플레이어 아이디 같은사람 찾기
		// 4. 팀스코어 테이블에서 3번에서 조회한 아이디가 있는지 체크 후 있다면 삭제
		// 5. 팀 스코어에 값 입력

		return responseService.getSuccessResult();
	}

}
