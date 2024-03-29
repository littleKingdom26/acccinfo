package info.team23h.acc.service.bbs;


import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.vo.bbs.*;
import info.team23h.acc.vo.comment.CommentResultVO;
import info.team23h.acc.vo.comment.CommentVO;
import info.team23h.acc.vo.front.Bbs.BbsSearchVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;

/**
 * The interface Bbs service.
 */
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

	/**
	 * 노티스 정보 조회
	 *
	 * @param pageRequest the page request
	 * @param bbsSearch   the bbs search
	 * @return the page
	 */
	Page<Bbs> findPages(PageRequest pageRequest, BbsSearch bbsSearch);

	/**
	 * 게시물 목록
	 *
	 * @param bbsSearch the bbs search
	 * @return the page
	 */
	Page<Bbs> findByAllPages(BbsSearchVO bbsSearch);


	/**
	 * 게시물 목록 (검색어  추가)
	 *
	 * @param bbsSearch the bbs search
	 * @param keyword   the keyword
	 * @return the page
	 */
	Page<Bbs> findByAllPages(BbsSearchVO bbsSearch,String keyword);
	/**
	 * 게시물 상세
	 *
	 * @param bbsSeq the bbs seq
	 * @return the bbs result dto
	 */
	BbsResultVO findBySeq(Long bbsSeq);

	/**
	 * 파일 삭제
	 *
	 * @param fileSeq the file seq
	 */
	void deleteFile(Long fileSeq);

	/**
	 * 댓글 저장
	 *
	 * @param commentVO the comment vo
	 */
	CommentResultVO saveComment(CommentVO commentVO);

	/**
	 * 관리자 게시판 목록
	 *
	 * @param nameSeq the name seq
	 * @param search  the search
	 */
	Page<AdminBbsPageResultVO> findByAllPages(Long nameSeq, AdminBbsSearchVO search);

	void deleteBbs(Long seq);
}
