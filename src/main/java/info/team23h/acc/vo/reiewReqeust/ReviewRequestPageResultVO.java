package info.team23h.acc.vo.reiewReqeust;

import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.entity.reviewReqeust.ReviewRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ReviewRequestPageResultVO {

	private Long reviewRequestKey;

	private String regId;

	private String reviewTarget;

	private String complaints;

	private String event;

	private String sessionDivision;

	private LocalDateTime regDt;

	public String getComplaintsName() {
		if(ObjectUtils.isEmpty(this.complaints)) {
			return "";
		}else{
			return EnumCode.ComplaintsCode.valueOf(this.complaints).getValue();
		}
	}

	public String getRegDtStr(){
		if(ObjectUtils.isEmpty(this.regDt)) {
			return "";
		}else{
			return this.regDt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		}
	}

	public ReviewRequestPageResultVO(ReviewRequest reviewRequest) {
		this.reviewRequestKey = reviewRequest.getReviewRequestKey();
		this.regId = reviewRequest.getRegId();
		this.reviewTarget = reviewRequest.getReviewTarget();
		this.complaints = reviewRequest.getComplaints();
		this.event = reviewRequest.getEvent();
		this.sessionDivision = reviewRequest.getSessionDivision();
		this.regDt = reviewRequest.getRegDt();
	}
}
