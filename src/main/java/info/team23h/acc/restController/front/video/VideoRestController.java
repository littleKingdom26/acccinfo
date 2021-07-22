package info.team23h.acc.restController.front.video;

import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.bbs.BbsService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.service.video.VideoService;
import info.team23h.acc.vo.front.Bbs.BbsSearchVO;
import info.team23h.acc.vo.front.video.VideoResultVO;
import info.team23h.acc.vo.front.video.VideoSearchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/video")
@Api(tags = "video API", description = "비디오 api 리스트")
public class VideoRestController {

	final private ResponseService responseService;

	final private VideoService videoService;

	final private BbsService bbsService;

	final private BCryptPasswordEncoder bCryptPasswordEncoder;


	@ApiOperation(value = "비디오 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<Page<VideoResultVO>> findGalleryList(final VideoSearchVO commonVO) {
		log.info("GalleryRestController.findGalleryList");
		BbsSearchVO bbsSearchVO = new BbsSearchVO();
		bbsSearchVO.setPage(commonVO.getPage());
		bbsSearchVO.setSize(commonVO.getSize());
		if(ObjectUtils.isEmpty(bbsSearchVO.getNameSeq())) {
			bbsSearchVO.setNameSeq(5L); // 공지시항
		}
		final Page<Bbs> byAllPages = bbsService.findByAllPages(bbsSearchVO, commonVO.getKeyword());
		final Page<VideoResultVO> map = byAllPages.map(VideoResultVO::new);
		return responseService.getSingleResult(map);
	}



}
