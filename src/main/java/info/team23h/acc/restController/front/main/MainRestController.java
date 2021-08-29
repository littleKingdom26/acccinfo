package info.team23h.acc.restController.front.main;

import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.event.EventService;
import info.team23h.acc.service.gallery.GalleryService;
import info.team23h.acc.service.poster.PosterService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.vo.front.gallery.GalleryResultVO;
import info.team23h.acc.vo.front.main.BeforeLeagueRankerGroupResultVO;
import info.team23h.acc.vo.front.main.PosterMainResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
@Api(tags = "Main API",description = "메인 화면 api 리스트")
public class MainRestController {

	final private ResponseService responseService;

	final private EventService eventService;

	final private GalleryService galleryService;

	final private PosterService posterService;

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


	@ApiOperation(value="포스터 항목 조회",notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value="/poster/{type}",produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<PosterMainResultVO>> findByMainPosterFilterType(@PathVariable(value = "type") @ApiParam(name = "type",value = "common - 포스터 타입 코드 조회 항목 입력") final String type){
		return responseService.getSingleResult(posterService.findByMainPoster(type));
	}

	@ApiOperation(value = "포스터 전체 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/poster", produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<PosterMainResultVO>> findByMainPoster() {
		return responseService.getSingleResult(posterService.findByMainPoster());
	}
}
