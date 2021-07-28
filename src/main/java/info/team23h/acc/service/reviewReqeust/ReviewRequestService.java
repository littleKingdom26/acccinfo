package info.team23h.acc.service.reviewReqeust;

import info.team23h.acc.vo.front.reviewReqeustSaveVO.ReviewRequestResultVO;
import info.team23h.acc.vo.front.reviewReqeustSaveVO.ReviewRequestSaveVO;
import info.team23h.acc.vo.reiewReqeust.AdminReviewRequestResultVO;
import info.team23h.acc.vo.reiewReqeust.ReviewRequestPageResultVO;
import info.team23h.acc.vo.reiewReqeust.ReviewRequestSearchVO;
import org.springframework.data.domain.Page;

public interface ReviewRequestService {

	/**
	 * 심의 신청 저장
	 *
	 * @param reviewRequestSaveVO the review request save vo
	 * @return the review request result vo
	 */
	ReviewRequestResultVO save(ReviewRequestSaveVO reviewRequestSaveVO);

	Page<ReviewRequestPageResultVO> findPage(ReviewRequestSearchVO reviewRequestSearchVO);

	AdminReviewRequestResultVO findByDetail(Long reviewRequestKey);
}
