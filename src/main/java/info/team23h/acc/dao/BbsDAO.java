package info.team23h.acc.dao;

import info.team23h.acc.vo.bbs.BbsNameVO;
import info.team23h.acc.vo.bbs.BbsSearch;
import info.team23h.acc.vo.bbs.BbsVO;
import info.team23h.acc.vo.comment.CommentVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BbsDAO {
	@Autowired
	private SqlSession sql;

	private final String PREFIX = "bbsSql";

	public List<BbsNameVO> loadBbsName() {
		return sql.selectList(PREFIX + ".loadBbsName");
	}

	public List<BbsVO> loadBbsList(BbsSearch bbsSearch) {
		return sql.selectList(PREFIX + ".loadBbsList",bbsSearch);
	}

	public int loadBbsListCount(BbsSearch bbsSearch) {
		return sql.selectOne(PREFIX + ".loadBbsListCount", bbsSearch);
	}

	public int save(BbsVO bbsVO) {
		return sql.insert(PREFIX + ".save", bbsVO);
	}

	public BbsVO loadBbsView(BbsSearch bbsSearch) {
		return sql.selectOne(PREFIX + ".loadBbsView",bbsSearch);
	}

	public int commentSave(CommentVO commentVO) {
		return sql.insert(PREFIX + ".commentSave", commentVO);
	}

	public int update(BbsVO bbsVO) {
		return sql.update(PREFIX + ".update", bbsVO);
	}

	public int commentDel(CommentVO commentVO) {
		return sql.delete(PREFIX+".commentDel",commentVO);
	}

	public int bbsDel(BbsVO bbsVO) {
		return sql.delete(PREFIX + ".bbsDel", bbsVO);
	}
}
