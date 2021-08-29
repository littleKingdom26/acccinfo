package info.team23h.acc.service.reviewReqeust;


import info.team23h.acc.config.Team23hException;
import info.team23h.acc.entity.reviewReqeust.ReviewRequest;
import info.team23h.acc.repository.reviewReqeust.ReviewRequestRepository;
import info.team23h.acc.vo.front.reviewReqeustSaveVO.ReviewRequestResultVO;
import info.team23h.acc.vo.front.reviewReqeustSaveVO.ReviewRequestSaveVO;
import info.team23h.acc.vo.reiewReqeust.AdminReviewRequestResultVO;
import info.team23h.acc.vo.reiewReqeust.ReviewRequestPageResultVO;
import info.team23h.acc.vo.reiewReqeust.ReviewRequestSearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewRequestServiceImpl implements ReviewRequestService {

	final private ReviewRequestRepository reviewRequestRepository;

	@Override
	@Transactional
	public ReviewRequestResultVO save(ReviewRequestSaveVO reviewRequestSaveVO) {

		final ReviewRequest param = ReviewRequest.builder()
		                                         .regId(reviewRequestSaveVO.getRegId())
		                                         .reviewTarget(reviewRequestSaveVO.getReviewTarget())
		                                         .complaints(reviewRequestSaveVO.getComplaints())
		                                         .event(reviewRequestSaveVO.getEvent())
		                                         .sessionDivision(reviewRequestSaveVO.getSessionDivision())
		                                         .replayTime(reviewRequestSaveVO.getReplayTime())
		                                         .description(reviewRequestSaveVO.getDescription())
		                                         .build();
		final ReviewRequest save = reviewRequestRepository.save(param);
		return new ReviewRequestResultVO(save);
	}

	@Override
	public Page<ReviewRequestPageResultVO> findPage(ReviewRequestSearchVO reviewRequestSearchVO) {

		Page<ReviewRequest> result = null;
		final PageRequest pageRequest = PageRequest.of(reviewRequestSearchVO.getPage() - 1, reviewRequestSearchVO.getSize(), Sort.by("reviewRequestKey")
		                                                                                                                              .descending());
		if(ObjectUtils.isEmpty(reviewRequestSearchVO.getKeyword())) {
			result = reviewRequestRepository.findAll(pageRequest);
		}else{
			result = reviewRequestRepository.findAllByRegIdContains(reviewRequestSearchVO.getKeyword(),pageRequest);
		}
		return result.map(ReviewRequestPageResultVO::new);
	}

	@Override
	public AdminReviewRequestResultVO findByDetail(Long reviewRequestKey) {
		final ReviewRequest reviewRequest = reviewRequestRepository.findById(reviewRequestKey)
		                                                 .orElseThrow(() -> new Team23hException("게시물"));
		return new AdminReviewRequestResultVO(reviewRequest);
	}
}
