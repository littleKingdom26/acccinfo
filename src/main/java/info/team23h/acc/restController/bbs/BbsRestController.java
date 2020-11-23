package info.team23h.acc.restController.bbs;

import info.team23h.acc.service.bbs.BbsService;
import info.team23h.acc.vo.bbs.BbsVO;
import info.team23h.acc.vo.comment.CommentVO;
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

	@PostMapping("/bbs/delete")
	public HashMap<String, Object> delete(@RequestBody BbsVO bbsVO) {
		HashMap<String, Object> result = bbsService.bbsDel(bbsVO);
		return result;
	}



	@PostMapping("/bbs/saveComment")
	public HashMap<String,Object> saveComment(@RequestBody CommentVO commentVO){
		HashMap<String, Object> result = bbsService.commentSave(commentVO);
		return result;
	}

	@PostMapping("/bbs/modifyCheck")
	public HashMap<String, Object> modifyCheck(@RequestBody BbsVO bbsVO) {
		HashMap<String, Object> result = bbsService.modifyCheck(bbsVO);
		return result;
	}


}
