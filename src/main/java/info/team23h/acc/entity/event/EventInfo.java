package info.team23h.acc.entity.event;


import info.team23h.acc.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_EVENT_INFO")
public class EventInfo  extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EVENT_INFO_SEQ")
	private Long eventInfoSeq;

	@Column(name="TITLE")
	private String title;

	@Column(name = "ROUND")
	private Long round;

	@Column(name = "DIVISION")
	private Long devision;

	@OneToOne
	@JoinColumn(name="SCORE_INFO_SEQ")
	private ScoreInfo scoreInfo;

	@OneToOne
	@JoinColumn(name = "HANDICAP_INFO_SEQ")
	private HandicapInfo handicapInfo;
}
