package info.team23h.acc.service.bbs;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.dao.BbsDAO;
import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.entity.bbs.BbsComment;
import info.team23h.acc.entity.bbs.BbsFile;
import info.team23h.acc.entity.bbs.TbBbsName;
import info.team23h.acc.repository.bbs.BbsCommentRepository;
import info.team23h.acc.repository.bbs.BbsNameRepository;
import info.team23h.acc.repository.bbs.BbsRepository;
import info.team23h.acc.repository.file.FileRepository;
import info.team23h.acc.restController.front.event.EventRestController;
import info.team23h.acc.restController.front.gallery.GalleryRestController;
import info.team23h.acc.restController.front.notice.NoticeRestController;
import info.team23h.acc.restController.front.video.VideoRestController;
import info.team23h.acc.util.FileUtil;
import info.team23h.acc.util.PageHelper;
import info.team23h.acc.vo.bbs.*;
import info.team23h.acc.vo.comment.CommentResultVO;
import info.team23h.acc.vo.comment.CommentVO;
import info.team23h.acc.vo.front.Bbs.BbsSearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BbsServiceImpl implements BbsService {


	final private BbsDAO bbsDAO;

	@Value("${team23h.bbs.pageCount}")
	private int pageCount;


	final private BCryptPasswordEncoder bCryptPasswordEncoder;


	final private BbsNameRepository bbsNameRepository;


	final private BbsRepository bbsRepository;

	final private FileRepository fileRepository;

	final private BbsCommentRepository bbsCommentRepository;



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

	@Transactional
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

	@Transactional
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
	@Transactional
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
		final Page<Bbs> seq = bbsRepository.findAllByTbBbsName(tbBbsName, PageRequest.of(bbsSearch.getPage() - 1, bbsSearch.getSize(), Sort.by("seq").descending()));

		if(bbsSearch.getNameSeq().equals(1L)){
			seq.forEach(bbs -> {
				WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NoticeRestController.class).getBbsDetail(bbs.getSeq()));
				bbs.set_link(linkTo.withRel("detail"));
			});
		}else if(bbsSearch.getNameSeq().equals(2L)){
			seq.forEach(bbs -> {
				WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventRestController.class).findBbsDetail(bbs.getSeq()));
				bbs.set_link(linkTo.withRel("detail"));
			});
		}else if(bbsSearch.getNameSeq().equals(4L)){
			seq.forEach(bbs -> {
				WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GalleryRestController.class).findGalleryDetail(bbs.getSeq()));
				bbs.set_link(linkTo.withRel("detail"));
			});
		}
		return seq;
	}

	@Override
	public Page<Bbs> findByAllPages(BbsSearchVO bbsSearch, String keyword) {
		final TbBbsName tbBbsName = bbsNameRepository.findById(bbsSearch.getNameSeq()).orElse(new TbBbsName());
		if(ObjectUtils.isEmpty(keyword)){
			keyword = "";
		}
		final Page<Bbs> seq = bbsRepository.findAllByTbBbsNameAndTitleContains(tbBbsName,keyword, PageRequest.of(bbsSearch.getPage() - 1, bbsSearch.getSize(), Sort.by("seq").descending()));
		if(bbsSearch.getNameSeq().equals(1L)){
			seq.forEach(bbs -> {
				WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NoticeRestController.class).getBbsDetail(bbs.getSeq()));
				bbs.set_link(linkTo.withRel("detail"));
			});
		}else if(bbsSearch.getNameSeq().equals(2L)){
			seq.forEach(bbs -> {
				WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventRestController.class).findBbsDetail(bbs.getSeq()));
				bbs.set_link(linkTo.withRel("detail"));
			});
		}else if(bbsSearch.getNameSeq().equals(4L)){
			seq.forEach(bbs -> {
				WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GalleryRestController.class).findGalleryDetail(bbs.getSeq()));
				bbs.set_link(linkTo.withRel("detail"));
			});
		}else if(bbsSearch.getNameSeq().equals(5L)){
			seq.forEach(bbs -> {
				WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VideoRestController.class)
				                                                                     .findGalleryDetail(bbs.getSeq()));
				bbs.set_link(linkTo.withRel("detail"));
			});
		}
		return seq;
	}

	@Override
	public BbsResultVO findBySeq(Long bbsSeq) throws Team23hException {

		final Bbs bbs = bbsRepository.findById(bbsSeq).orElseThrow(() -> new Team23hException("게시물이 없습니다."));
		final List<BbsCommentResultVO> bbsCommentList = bbs.getBbsCommentList().stream().map(BbsCommentResultVO::new).collect(Collectors.toList());

		return BbsResultVO.builder().bbs(bbs).commentList(bbsCommentList).build();
	}

	@Override
	@Transactional
	public void deleteFile(Long fileSeq) {
		//파일 조회
		final BbsFile bbsFile = fileRepository.findById(fileSeq).orElseThrow(() -> new Team23hException("파일이 없습니다."));
		// 파일 물리 삭제
		FileUtil.delete(bbsFile.getFilePath(),bbsFile.getFileName());
		// 파일 디비 삭제
		fileRepository.delete(bbsFile);
	}

	@Override
	@Transactional
	public CommentResultVO saveComment(CommentVO commentVO) throws Team23hException{
		final Bbs bbs = bbsRepository.findById(commentVO.getBbsSeq()).orElseThrow(() -> new Team23hException("게시물이 없습니다."));
		final BbsComment build = BbsComment.builder().bbs(bbs).comment(commentVO.getComment()).regId(commentVO.getRegId()).build();

		final BbsComment save = bbsCommentRepository.save(build);
		return new CommentResultVO(save);


	}

	@Override
	public Page<AdminBbsPageResultVO> findByAllPages(Long nameSeq, AdminBbsSearchVO search) {
		final TbBbsName bbsName = bbsNameRepository.findById(nameSeq)
		                                           .orElseThrow(() -> new Team23hException("게시물이 없습니다."));

		String title= "";
		String regId= "";

		if(! ObjectUtils.isEmpty(search.getTitle())) {
			title = search.getTitle();
		}

		if(! ObjectUtils.isEmpty(search.getRegId())) {
			regId = search.getRegId();
		}

		final Page<Bbs> bbs = bbsRepository.findAllByTbBbsNameAndTitleContainsAndRegIdContains(bbsName, title, regId, PageRequest.of(search.getPage() - 1, search.getSize(), Sort.by("seq").descending()));
		return bbs.map(AdminBbsPageResultVO::new);
	}

}
