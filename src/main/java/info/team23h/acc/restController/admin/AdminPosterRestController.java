package info.team23h.acc.restController.admin;

import info.team23h.acc.model.response.CommonResult;
import info.team23h.acc.service.poster.PosterService;
import info.team23h.acc.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/poster")
public class AdminPosterRestController {

	final private PosterService posterService;

	final private ResponseService responseService;

	@DeleteMapping("/{posterKey}")
	public CommonResult delete(@PathVariable final Long posterKey){
		log.debug("posterKey : {}", posterKey);
		posterService.delete(posterKey);
		return responseService.getSuccessResult();
	}
}
