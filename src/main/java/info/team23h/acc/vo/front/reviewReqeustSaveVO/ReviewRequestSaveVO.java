package info.team23h.acc.vo.front.reviewReqeustSaveVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestSaveVO {

	@ApiModelProperty(value = "심의_신청_자", name = "regId")
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

	@Builder
	public ReviewRequestSaveVO(String regId, String reviewTarget, String complaints, String event, String sessionDivision, String replayTime, String description) {
		this.regId = regId;
		this.reviewTarget = reviewTarget;
		this.complaints = complaints;
		this.event = event;
		this.sessionDivision = sessionDivision;
		this.replayTime = replayTime;
		this.description = description;
	}
}
