package info.team23h.acc.entity.team;

import info.team23h.acc.entity.BaseTimeEntity;
import info.team23h.acc.entity.event.EventMeta;
import info.team23h.acc.vo.team.TeamScoreSaveVO;
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
@Table(name = "TB_TEAM_SCORE")
public class TeamScore  extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEAM_SCORE_SEQ")
	private Long teamScoreSeq;

	@Column(name = "SCORE")
	private Long score;

	@Column(name = "EVENT_DT")
	private String eventDt;

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EVENT_INFO_SEQ",updatable = false,insertable = false)
	private EventInfo eventInfo;*/


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="EVENT_INFO_SEQ",referencedColumnName = "EVENT_INFO_SEQ")
			,@JoinColumn(name="ROUND",referencedColumnName = "ROUND")})
	private EventMeta eventMeta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEAM_SEQ")
	private Team team;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEAM_INFO_SEQ")
	private TeamInfo teamInfo;


	@Builder
	public TeamScore(final TeamScoreSaveVO teamScoreSaveVO) {
		this.score = teamScoreSaveVO.getScore();
		this.eventDt = teamScoreSaveVO.getRegDt();
		this.team = teamScoreSaveVO.getTeam();
		this.teamInfo = teamScoreSaveVO.getTeam().getTeamInfo();
		this.eventMeta = teamScoreSaveVO.getEventMeta();
	}

	public void update(Long score) {
		this.score = score;
	}
}
