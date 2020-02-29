package info.team23h.acc.restController;

import info.team23h.acc.service.BbsService;
import info.team23h.acc.vo.BbsVO;
import info.team23h.acc.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class BbsRestController {

	@Autowired
	BbsService bbsService;

	@PostMapping("/bbs/saveBbs")
	public HashMap<String,Object> saveBbs(@RequestBody BbsVO bbsVO) {
		HashMap<String,Object> result = bbsService.save(bbsVO);
		return result;
	}

	@PostMapping("/bbs/updateBbs")
	public HashMap<String, Object> updateBbs(@RequestBody BbsVO bbsVO) {
		HashMap<String, Object> result = bbsService.update(bbsVO);
		return result;
	}

	@PostMapping("/bbs/saveComment")
	public HashMap<String,Object> saveComment(@RequestBody CommentVO commentVO){
		HashMap<String, Object> result = bbsService.commentSave(commentVO);
		return result;
	}
}
