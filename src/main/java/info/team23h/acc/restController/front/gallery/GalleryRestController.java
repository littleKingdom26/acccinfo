package info.team23h.acc.restController.front.gallery;


import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.gallery.GalleryService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.vo.gallery.GalleryResultVO;
import info.team23h.acc.vo.gallery.GallerySaveVO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = "Gallery API", description = "겔러리 api 리스트")
@RequestMapping("/api/gallery")
public class GalleryRestController {

	final private ResponseService responseService;

	final private GalleryService galleryService;


	@PostMapping(value="/save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<GalleryResultVO> gallerySave(@ModelAttribute GallerySaveVO gallerySaveVO) throws IOException {
		log.debug("fileList : {}", gallerySaveVO.toString());
		return responseService.getSingleResult(galleryService.save(gallerySaveVO));
	}

}
