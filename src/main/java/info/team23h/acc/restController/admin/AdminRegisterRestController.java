package info.team23h.acc.restController.admin;


import info.team23h.acc.model.response.CommonResult;
import info.team23h.acc.service.leagueDiv.LeagueDivService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.vo.leagueDiv.LeagueDivSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/register")
public class AdminRegisterRestController {

	final private LeagueDivService leagueDivService;

	final private ResponseService responseService;


	@PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public CommonResult save(@RequestBody LeagueDivSaveVO leagueDivSaveVO){
		log.info("AdminRegisterRestController.save");
		leagueDivService.save(leagueDivSaveVO);
		return responseService.getSuccessResult();
	}

}
