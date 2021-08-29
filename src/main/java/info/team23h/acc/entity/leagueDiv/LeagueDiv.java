package info.team23h.acc.entity.leagueDiv;

import info.team23h.acc.entity.BaseTimeEntity;
import info.team23h.acc.entity.car.Car;
import info.team23h.acc.entity.player.Player;
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
@Table(name = "TB_LEAGUE_DIV")
public class LeagueDiv extends BaseTimeEntity {

	@Id
	@Column(name="LEAGUE_DIV_KEY")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leagueDivKey;

	@Column(name = "PLAYER_ID")
	private String playerId;

	@OneToOne
	@JoinColumn(name = "PLAYER_ID",updatable = false,insertable = false)
	private Player player;

	@Column(name="LEAGUE_DIV")
	private String leagueDiv;

	@Column(name = "BALLAST")
	private Long ballast;

	@Column(name = "CAR_MODEL")
	private Long carModel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CAR_MODEL" ,insertable = false,updatable = false)
	private Car car;

	@Column(name = "CAR_NUMBER")
	private Long carNumber;

	@Builder
	public LeagueDiv(String playerId, String leagueDiv, Long ballast, Long carModel,Long carNumber) {
		this.playerId = playerId;
		this.leagueDiv = leagueDiv;
		this.ballast = ballast;
		this.carModel = carModel;
		this.carNumber = carNumber;
	}
}
