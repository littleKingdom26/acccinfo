package info.team23h.acc.entity.event;

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
@Table(name = "TB_PENALTY")
public class TbPenalty extends BaseTimeEntity {

	@Id
	@Column(name = "PENALTY_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long penaltySeq;

	@Column(name = "EVENT_INFO_SEQ")
	private Long eventInfoSeq;

	@Column(name = "ROUND")
	private Long round;

	@Column(name = "PLAYER_ID")
	private String playerId;

	@Column(name = "ADD_TIME")
	private Long addTime;

	@Column(name = "CAR_ID")
	private String carId;

	@Column(name = "REASON")
	private String reason;

	@Column(name = "PENALTY")
	private String penalty;

	@Builder
	public TbPenalty( Long eventInfoSeq, Long round, String playerId, Long addTime, String carId, String reason, String penalty) {
		this.eventInfoSeq = eventInfoSeq;
		this.round = round;
		this.playerId = playerId;
		this.addTime = addTime;
		this.carId = carId;
		this.reason = reason;
		this.penalty = penalty;
	}
}
