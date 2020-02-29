package info.team23h.acc.service;

import info.team23h.acc.dao.BbsDAO;
import info.team23h.acc.vo.BbsNameVO;
import info.team23h.acc.vo.BbsSearch;
import info.team23h.acc.vo.BbsVO;
import info.team23h.acc.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	BbsDAO bbsDAO;

	@Value("${team23h.bbs.pageCount}")
	private int pageCount;

	@Override
	public List<BbsNameVO> loadBbsName() {
		return bbsDAO.loadBbsName();
	}

	@Override
	public HashMap<String,Object> loadBbsList(BbsSearch bbsSearch) {

		int totalCount = bbsDAO.loadBbsListCount(bbsSearch);
		bbsSearch.setTotalCount(totalCount); // 총 데이터 수
		bbsSearch.setPageCount(pageCount); // 1페이지에 뿌려질 데이터의 수

		List<BbsVO> bbsList =  bbsDAO.loadBbsList(bbsSearch);
		int bbsNo = totalCount -((bbsSearch.getCurrentPage() -1)* pageCount);
		for(int i = 0; i < bbsList.size(); i++){
			bbsList.get(i).setNo(bbsNo - i);
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", bbsList);
		map.put("search", bbsSearch);

		return map;
	}

	@Override
	@Transactional
	public HashMap<String, Object> save(BbsVO bbsVO) {
		int cnt = bbsDAO.save(bbsVO);
		HashMap<String,Object> result = new HashMap<String, Object>();
		if(cnt > 0){
			result.put("code", "0000");
			result.put("nameSeq", bbsVO.getNameSeq());
		}else{
			result.put("code", "9999");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> loadBbsView(BbsSearch bbsSearch) {
		BbsVO bbsVO = bbsDAO.loadBbsView(bbsSearch);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bbsVO", bbsVO);
		map.put("search", bbsSearch);
		return map;
	}

	@Override
	@Transactional
	public HashMap<String, Object> commentSave(CommentVO commentVO) {
		int cnt = bbsDAO.commentSave(commentVO);
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(cnt > 0){
			result.put("code", "0000");
			result.put("comment", commentVO.getComment());
			result.put("regId", commentVO.getRegId());
		}else{
			result.put("code", "9999");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> update(BbsVO bbsVO) {
		int cnt = bbsDAO.update(bbsVO);
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(cnt > 0){
			result.put("code", "0000");
			result.put("nameSeq", bbsVO.getNameSeq());
			result.put("bbsSeq", bbsVO.getSeq());
		}else{
			result.put("code", "9999");
		}
		return result;
	}
}
