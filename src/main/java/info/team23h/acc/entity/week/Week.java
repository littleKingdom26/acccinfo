package info.team23h.acc.entity.week;

import info.team23h.acc.entity.BaseTimeEntity;
import info.team23h.acc.entity.track.Track;
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
@Table(name = "TB_WEEK")
public class Week extends BaseTimeEntity {


//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ")
	private Long seq;

	@Id
	@Column(name="SESSION_ID")
	private Long sessionId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TRACK_SEQ")
	private Track track;

	@Column(name = "START_DT")
	private String startDt;

	@Column(name = "END_DT")
	private String endDt;

}
