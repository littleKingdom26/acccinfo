package info.team23h.acc.vo.front.result;

import info.team23h.acc.entity.event.EventInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultSeasonResultVO {

	private String seasonName;

	private Long eventInfoSeq;

	private Long round;


	public ResultSeasonResultVO(EventInfo eventInfo) {
		this.seasonName = eventInfo.getTitle();
		this.eventInfoSeq = eventInfo.getEventInfoSeq();
		this.round = eventInfo.getRound();
	}
}
