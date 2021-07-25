package info.team23h.acc.service.reviewReqeust;


import info.team23h.acc.entity.reviewReqeust.ReviewRequest;
import info.team23h.acc.repository.reviewReqeust.ReviewRequestRepository;
import info.team23h.acc.vo.front.reviewReqeustSaveVO.ReviewRequestResultVO;
import info.team23h.acc.vo.front.reviewReqeustSaveVO.ReviewRequestSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
