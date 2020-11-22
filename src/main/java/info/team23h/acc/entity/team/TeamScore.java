package info.team23h.acc.entity.team;

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
@Table(name = "TB_TEAM")
public class TeamScore  extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEAM_SCORE_SEQ")
	private Long teamScoreSeq;

	@Column(name = "ROUND")
	private Long round;

	@Column(name = "SCORE")
	private Long score;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEAM_SEQ")
	private Team team;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEAM_INFO_SEQ")
	private TeamInfo teamInfo;

}
