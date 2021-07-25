package info.team23h.acc.vo.front.reviewReqeustSaveVO;


import info.team23h.acc.entity.reviewReqeust.ReviewRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestResultVO {

	@ApiModelProperty(value = "심의_신청_키", name = "reviewRequestKey")
	private Long reviewRequestKey;

	@ApiModelProperty(value = "심의_신청_자", name = "reviewTarget")
	private String regId;

	@ApiModelProperty(value = "심의_대상_자", name = "reviewTarget")
	private String reviewTarget;

	@ApiModelProperty(value = "항의_범주", name = "complaints")
	private String complaints;

	@ApiModelProperty(value = "레이스_이벤트", name = "event")
	private String event;

	@ApiModelProperty(value = "세션_단계", name = "sessionDivision")
	private String sessionDivision;

	@ApiModelProperty(value = "리플레이_시간", name = "replayTime")
	private String replayTime;

	@ApiModelProperty(value = "설명", name = "description")
	private String description;

	public ReviewRequestResultVO(ReviewRequest reviewRequest) {
		this.reviewRequestKey = reviewRequest.getReviewRequestKey();
		this.reviewTarget = reviewRequest.getReviewTarget();
		this.complaints = reviewRequest.getComplaints();
		this.event = reviewRequest.getEvent();
		this.sessionDivision = reviewRequest.getSessionDivision();
		this.replayTime = reviewRequest.getReplayTime();
		this.description = reviewRequest.getDescription();
		this.regId = reviewRequest.getRegId();
	}
}
