package info.team23h.acc.entity.reviewReqeust;

import info.team23h.acc.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_REVIEW_REQUEST")
public class ReviewRequest extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REVIEW_REQUEST_KEY")
	private Long reviewRequestKey;

	@Column(name="REG_ID")
	private String regId;

	@Column(name = "REVIEW_TARGET")
	private String reviewTarget;

	@Column(name = "COMPLAINTS")
	private String complaints;

	@Column(name = "EVENT")
	private String event;

	@Column(name = "SESSION_DIVISION")
	private String sessionDivision;

	@Column(name = "REPLAY_TIME")
	private String replayTime;

	@Column(name = "DESCRIPTION")
	private String description;

	@Builder
	public ReviewRequest(String regId, String reviewTarget, String complaints, String event, String sessionDivision, String replayTime, String description) {
		this.regId = regId;
		this.reviewTarget = reviewTarget;
		this.complaints = complaints;
		this.event = event;
		this.sessionDivision = sessionDivision;
		this.replayTime = replayTime;
		this.description = description;
	}
}
