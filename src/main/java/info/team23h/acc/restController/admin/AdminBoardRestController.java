package info.team23h.acc.restController.admin;

import info.team23h.acc.model.response.CommonResult;
import info.team23h.acc.service.bbs.BbsService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.vo.bbs.BbsVO;
import info.team23h.acc.vo.comment.CommentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminBoardRestController {


	final private BbsService bbsService;

	final private ResponseService responseService;


	@PostMapping("/board/delComment")
	public HashMap<String,Object> saveBbs(@RequestBody CommentVO commentVO) {
		HashMap<String,Object> result = bbsService.commentDel(commentVO);
		return result;
	}

	@PostMapping("/board/delBoard")
	public CommonResult saveBbs(@RequestBody BbsVO bbsVO) {
		bbsService.deleteBbs(bbsVO.getSeq());
		return responseService.getSuccessResult();
	}
}
