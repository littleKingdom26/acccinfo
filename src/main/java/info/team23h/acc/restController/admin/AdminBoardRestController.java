package info.team23h.acc.restController.admin;

import info.team23h.acc.service.bbs.BbsService;
import info.team23h.acc.vo.bbs.BbsVO;
import info.team23h.acc.vo.comment.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminBoardRestController {

	@Autowired
	BbsService bbsService;

	@PostMapping("/board/delComment")
	public HashMap<String,Object> saveBbs(@RequestBody CommentVO commentVO) {
		HashMap<String,Object> result = bbsService.commentDel(commentVO);
		return result;
	}

	@PostMapping("/board/delBoard")
	public HashMap<String, Object> saveBbs(@RequestBody BbsVO bbsVO) {
		CommentVO commentVO = new CommentVO();
		commentVO.setBbsSeq(bbsVO.getSeq());
		bbsService.commentDel(commentVO);
		HashMap<String, Object> result = bbsService.bbsDel(bbsVO);
		return result;
	}
}
