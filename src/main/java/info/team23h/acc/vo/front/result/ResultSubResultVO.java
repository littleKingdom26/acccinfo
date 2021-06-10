package info.team23h.acc.vo.front.result;

import info.team23h.acc.entity.event.EventSub;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultSubResultVO {

	@ApiModelProperty(value = "랩", name = "lap")
	private Long lap;
	@ApiModelProperty(value = "랩타임", name = "lapTime")
	private Long lapTime;
	@ApiModelProperty(value = "섹터1", name = "sector1")
	private Long sector1;
	@ApiModelProperty(value = "섹터2", name = "sector2")
	private Long sector2;
	@ApiModelProperty(value = "섹터3", name = "sector3")
	private Long sector3;

	@ApiModelProperty(value = "조회 라운드", name = "round")
	private Long round;

	@ApiModelProperty(value = "차량 아이디", name = "carId")
	private String carId;



	public ResultSubResultVO(EventSub eventSub) {
		this.lap = eventSub.getLap();
		this.lapTime = eventSub.getLapTime();
		this.sector1 = eventSub.getSector1();
		this.sector2 = eventSub.getSector2();
		this.sector3 = eventSub.getSector3();
		this.round = eventSub.getRound();
		this.carId = eventSub.getCarId();
	}
}
