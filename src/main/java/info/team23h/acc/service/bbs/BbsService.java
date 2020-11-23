package info.team23h.acc.service.bbs;


import info.team23h.acc.vo.bbs.BbsNameVO;
import info.team23h.acc.vo.bbs.BbsSearch;
import info.team23h.acc.vo.bbs.BbsVO;
import info.team23h.acc.vo.comment.CommentVO;

import java.util.HashMap;
import java.util.List;

public interface BbsService {

	/**
	 * 게시판 목록 조회
	 * @return
	 */
	List<BbsNameVO> loadBbsName();

	/**
	 * 게시판 리스트 조회
	 * @param bbsSearch
	 * @return
	 */
	HashMap<String,Object> loadBbsList(BbsSearch bbsSearch);

	/**
	 * 게시물 조회
	 * @param bbsVO
	 * @return
	 */
	HashMap<String, Object> save(BbsVO bbsVO);

	/**
	 * 게시판 상세보기
	 * @param bbsSearch
	 * @return
	 */
	HashMap<String, Object> loadBbsView(BbsSearch bbsSearch);

	/**
	 * 댓글 저장
	 * @param commentVO
	 * @return
	 */
	HashMap<String, Object> commentSave(CommentVO commentVO);

	/**
	 * 게시물 수정
	 * @param bbsVO
	 * @return
	 */
	HashMap<String, Object> update(BbsVO bbsVO);

	/**
	 * 코멘트 삭제
	 * @param commentVO
	 * @return
	 */
	HashMap<String, Object> commentDel(CommentVO commentVO);

	/**
	 * 게시물 삭제
	 * @param bbsVO
	 * @return
	 */
	HashMap<String, Object> bbsDel(BbsVO bbsVO);

	/**
	 * 게시물 비밀번호 체크
	 * @param bbsVO
	 * @return
	 */
	HashMap<String, Object> modifyCheck(BbsVO bbsVO);
}
