package info.team23h.acc.vo.front.result;

import info.team23h.acc.entity.event.EventInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultSeasonResultVO {

	@ApiModelProperty(value = "시즌 이름", name = "seasonName")
	private String seasonName;

	@ApiModelProperty(value = "이벤트 정보 키값", name = "eventInfoSeq")
	private Long eventInfoSeq;

	@ApiModelProperty(value = "전체 라운드", name = "round")
	private Long round;


	public ResultSeasonResultVO(EventInfo eventInfo) {
		this.seasonName = eventInfo.getTitle();
		this.eventInfoSeq = eventInfo.getEventInfoSeq();
		this.round = eventInfo.getRound();
	}
}
