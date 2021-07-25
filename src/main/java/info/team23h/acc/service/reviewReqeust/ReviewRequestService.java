package info.team23h.acc.service.reviewReqeust;

import info.team23h.acc.vo.front.reviewReqeustSaveVO.ReviewRequestResultVO;
import info.team23h.acc.vo.front.reviewReqeustSaveVO.ReviewRequestSaveVO;

public interface ReviewRequestService {

	/**
	 * 심의 신청 저장
	 *
	 * @param reviewRequestSaveVO the review request save vo
	 * @return the review request result vo
	 */
	ReviewRequestResultVO save(ReviewRequestSaveVO reviewRequestSaveVO);
}
