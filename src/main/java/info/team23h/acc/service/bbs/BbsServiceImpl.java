package info.team23h.acc.service.bbs;

import info.team23h.acc.dao.BbsDAO;
import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.entity.bbs.TbBbsName;
import info.team23h.acc.repository.bbs.BbsNameRepository;
import info.team23h.acc.repository.bbs.BbsRepository;
import info.team23h.acc.util.PageHelper;
import info.team23h.acc.vo.bbs.BbsNameVO;
import info.team23h.acc.vo.bbs.BbsSearch;
import info.team23h.acc.vo.bbs.BbsVO;
import info.team23h.acc.vo.comment.CommentVO;
import info.team23h.acc.vo.front.Bbs.BbsSearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	BbsDAO bbsDAO;

	@Value("${team23h.bbs.pageCount}")
	private int pageCount;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	BbsNameRepository bbsNameRepository;

	@Autowired
	BbsRepository bbsRepository;

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
		PageHelper pageHelper = new PageHelper();
		pageHelper.setCurrentPage(bbsSearch.getCurrentPage());
		pageHelper.setTotalPage(bbsSearch.getTotalPage());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", bbsList);
		map.put("search", bbsSearch);
		map.put("page", pageHelper);

		return map;
	}

	@Override
	@Transactional
	public HashMap<String, Object> save(BbsVO bbsVO) {
		if(bbsVO.getPassword() != null && bbsVO.getPassword() != ""){
			bbsVO.setPassword(bCryptPasswordEncoder.encode(bbsVO.getPassword()));
		}
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

	@Override
	public HashMap<String, Object> commentDel(CommentVO commentVO) {
		int cnt = bbsDAO.commentDel(commentVO);
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(cnt > 0){
			result.put("code", "0000");
		}else{
			result.put("code", "9999");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> bbsDel(BbsVO bbsVO) {
		int cnt = bbsDAO.bbsDel(bbsVO);
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(cnt > 0){
			result.put("code", "0000");
		}else{
			result.put("code", "9999");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> modifyCheck(BbsVO bbsVO) {
		BbsSearch bbsSearch = new BbsSearch();
		bbsSearch.setNameSeq(bbsVO.getNameSeq());
		bbsSearch.setBbsSeq(bbsVO.getSeq());
		BbsVO result = bbsDAO.loadBbsView(bbsSearch);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if(bCryptPasswordEncoder.matches(bbsVO.getPassword(), result.getPassword())){
			resultMap.put("code", "0000");
		}else{
			resultMap.put("code", "9999");
		}
		return resultMap;
	}

	@Override
	public Page<Bbs> findPages(PageRequest pageRequest, BbsSearch bbsSearch) {
		return bbsNameRepository.findPages(pageRequest, bbsSearch);
	}

	@Override
	public Page<Bbs> findByAllPages(BbsSearchVO bbsSearch) {
		final TbBbsName tbBbsName = bbsNameRepository.findById(bbsSearch.getNameSeq()).orElse(new TbBbsName());
		return bbsRepository.findAllByTbBbsName(tbBbsName, PageRequest.of(bbsSearch.getPage()-1, bbsSearch.getSize(), Sort.by("seq").descending()));
	}
}
