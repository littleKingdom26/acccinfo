package info.team23h.acc.entity.team;

import info.team23h.acc.entity.BaseTimeEntity;
import info.team23h.acc.entity.player.Player;
import info.team23h.acc.vo.team.TeamSaveVO;
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
public class Team extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEAM_SEQ")
	private Long teamSeq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLAYER_ID")
	Player player;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TEAM_INFO_SEQ")
	TeamInfo teamInfo;


	public Team(Player player, TeamInfo teamInfo) {
		this.player = player;
		this.teamInfo = teamInfo;
	}
}
