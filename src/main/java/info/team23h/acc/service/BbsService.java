package info.team23h.acc.service;


import info.team23h.acc.vo.BbsNameVO;
import info.team23h.acc.vo.BbsSearch;
import info.team23h.acc.vo.BbsVO;
import info.team23h.acc.vo.CommentVO;

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
}
