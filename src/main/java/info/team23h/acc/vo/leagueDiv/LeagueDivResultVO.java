package info.team23h.acc.vo.leagueDiv;

import info.team23h.acc.entity.leagueDiv.LeagueDiv;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeagueDivResultVO {

	private String playerId;

	private Long ballast;

	private Long carModel;

	private String lastName;

	private String firstName;

	private Long carNumber;


	public LeagueDivResultVO(LeagueDiv leagueDiv) {
		this.playerId = leagueDiv.getPlayerId();
		this.ballast = leagueDiv.getBallast();
		this.carModel = leagueDiv.getCarModel();
		this.lastName = leagueDiv.getPlayer().getLastName();
		this.firstName = leagueDiv.getPlayer().getFirstName();
		this.carNumber = leagueDiv.getCarNumber();
	}
}
