package info.team23h.acc.vo.front.result;

import info.team23h.acc.entity.event.Event;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import javax.persistence.Transient;


@Getter
@Setter
public class ResultReturnVO {

	@ApiModelProperty(value = "lastName", name = "lastName")
	private String lastName;

	@ApiModelProperty(value = "fistName", name = "fistName")
	private String fistName;

	@ApiModelProperty(value = "포인트", name = "point")
	private Long point;

	@ApiModelProperty(value = "랭킹", name = "rank")
	private Long rank;

	@ApiModelProperty(value = "플레이어 아이디(상세 조회시 필요)", name = "playerId")
	private String playerId;

	@ApiModelProperty(value = "이벤트 정보 키값", name = "eventInfoSeq")
	private Long eventInfoSeq;

	@ApiModelProperty(value = "라운드", name = "round")
	private Long round;

	@Transient
	private Link _link;

	public ResultReturnVO(Event event) {
		this.lastName = event.getPlayer().getLastName();
		this.fistName = event.getPlayer().getFirstName();
		this.point = event.getScore();
		this.rank = event.getRank();
		this.playerId = event.getPlayer().getPlayerId();
		this.eventInfoSeq = event.getEventInfoSeq();
		this.round = event.getRound();
	}
}
