package info.team23h.acc.vo.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventAddPenaltySaveVO {

	private Long eventInfoSeq;

	private Long round;

	private String playerId;

	private Long addPenalty;

	private String reason;

	@Builder
	public EventAddPenaltySaveVO(Long eventInfoSeq, Long round, String playerId, Long addPenalty, String reason) {
		this.eventInfoSeq = eventInfoSeq;
		this.round = round;
		this.playerId = playerId;
		this.addPenalty = addPenalty;
		this.reason = reason;
	}
}
