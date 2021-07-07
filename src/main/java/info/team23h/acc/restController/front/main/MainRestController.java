package info.team23h.acc.restController.front.main;

import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.event.EventService;
import info.team23h.acc.service.gallery.GalleryService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.vo.front.gallery.GalleryResultVO;
import info.team23h.acc.vo.front.main.BeforeLeagueRankerGroupResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = "Main API",description = "메인 화면 api 리스트")
@RequestMapping("/api/main")
public class MainRestController {

	final private ResponseService responseService;

	final private EventService eventService;

	final private GalleryService galleryService;

	@ApiOperation(value = "전 시즌 랭커 목록 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/beforeLeagueRanker" , produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<BeforeLeagueRankerGroupResultVO>> beforeLeagueRanker(){
		return responseService.getSingleResult(eventService.getBeforeLeagueRanker());
	}


	@ApiOperation(value="메인 겔러리 목록 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value="/gallery",produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<GalleryResultVO>> findByMainGallery(){
		return responseService.getSingleResult(galleryService.findByMainGallery());
	}
}
