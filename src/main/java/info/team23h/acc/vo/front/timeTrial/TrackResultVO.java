package info.team23h.acc.vo.front.timeTrial;

import info.team23h.acc.entity.track.Track;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
public class TrackResultVO {

	private Long seq;

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
