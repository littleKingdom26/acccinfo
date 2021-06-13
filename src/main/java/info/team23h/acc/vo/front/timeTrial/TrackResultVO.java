package info.team23h.acc.vo.front.timeTrial;

import info.team23h.acc.entity.track.Track;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
public class TrackResultVO {

	@ApiModelProperty(value = "트랙 시퀀스", name = "seq")
	private Long seq;

	@ApiModelProperty(value = "트랙명", name = "trackName")
	private String trackName;

	public TrackResultVO(Track track) {
		this.seq = track.getSeq();
		if(ObjectUtils.isEmpty(track.getTrackViewName())){
			this.trackName = track.getTrackName();
		}else{
			this.trackName = track.getTrackViewName();
		}
	}
}
