package info.team23h.acc.vo.front.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultAllResultVO {

	@ApiModelProperty(value = "lastName", name = "lastName")
	private String lastName;

	@ApiModelProperty(value = "fistName", name = "firstName")
	private String firstName;

	@ApiModelProperty(value = "포인트", name = "point")
	private Long point;

	@ApiModelProperty(value = "플레이어 아이디", name = "playerId")
	private String playerId;

	/*
	@ApiModelProperty(value = "이벤트 정보 키값", name = "eventInfoSeq")
	private Long eventInfoSeq;

	@ApiModelProperty(value = "라운드", name = "round")
	private Long round;*/


	@Builder
	public ResultAllResultVO(String lastName, String fistName, Long point, String playerId) {
		this.lastName = lastName;
		this.firstName = fistName;
		this.point = point;
		this.playerId = playerId;
	}
}
