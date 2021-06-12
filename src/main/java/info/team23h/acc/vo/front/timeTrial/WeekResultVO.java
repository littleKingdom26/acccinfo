package info.team23h.acc.vo.front.timeTrial;

import info.team23h.acc.entity.week.Week;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
public class WeekResultVO {

	@ApiModelProperty(value = "주차 아이디", name = "sessionId")
	private Long sessionId;

	@ApiModelProperty(value = "시작일자", name = "startDt")
	private String startDt;

	@ApiModelProperty(value = "종료일자(null 이면 in progress)", name = "endDt")
	private String endDt;

	@ApiModelProperty(value = "트랙명", name = "trackName")
	private String trackName;

	@ApiModelProperty(value = "트랙시퀀스(언젠가 사용하지 않을까요??)", name = "trackSeq")
	private Long trackSeq;

	public WeekResultVO(Week week) {
		this.sessionId = week.getSessionId();
		this.startDt = week.getStartDt();
		this.endDt = week.getEndDt();
		if(ObjectUtils.isEmpty(week.getTrack().getTrackViewName())){
			this.trackName = week.getTrack().getTrackName();
		}else{
			this.trackName = week.getTrack().getTrackViewName();
		}

		this.trackSeq = week.getTrack().getSeq();
	}
}
