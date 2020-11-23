package info.team23h.acc.restController.admin;

import info.team23h.acc.config.variable.CommonResponse;
import info.team23h.acc.entity.team.TeamInfo;
import info.team23h.acc.model.response.CommonResult;
import info.team23h.acc.service.ResponseService;
import info.team23h.acc.service.TeamInfoService;
import info.team23h.acc.vo.team.TeamInfoSaveVO;
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

	@DeleteMapping("/teamInfo/delete/{teamInfoSeq}")
	public CommonResult scoreInfoDel(@PathVariable("teamInfoSeq") Long teamInfoSeq) throws Exception {
		teamInfoService.delete(teamInfoSeq);
		return responseService.getSuccessResult();
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

}
