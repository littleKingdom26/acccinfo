package info.team23h.acc.vo.front.timeTrial;

import info.team23h.acc.entity.recode.Record;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import javax.persistence.Transient;

@Getter
@Setter
public class TimeTrialResultVO {

	@ApiModelProperty(value = "베스트랩", name = "bestLap")
	private Long bestLap;

	@ApiModelProperty(value = "섹터1", name = "sector1")
	private Long sector1;

	@ApiModelProperty(value = "섹터2", name = "sector2")
	private Long sector2;

	@ApiModelProperty(value = "섹터3", name = "sector3")
	private Long sector3;

	@ApiModelProperty(value = "차량명", name = "carName")
	private String carName;

	@ApiModelProperty(value = "lastName", name = "lastName")
	private String lastName;

	@ApiModelProperty(value = "firstName", name = "firstName")
	private String firstName;

	@ApiModelProperty(value = "플레이어 아이디", name = "playerId")
	private String playerId;

	@Transient
	private Link _link;

	public TimeTrialResultVO(Record record) {
		this.bestLap = record.getBestLap();
		this.sector1 = record.getSector1();
		this.sector2 = record.getSector2();
		this.sector3 = record.getSector3();
		this.carName = record.getCar().getCarName();
		this.lastName = record.getPlayer().getLastName();
		this.firstName = record.getPlayer().getFirstName();
		this.playerId = record.getPlayer().getPlayerId();
	}
}
