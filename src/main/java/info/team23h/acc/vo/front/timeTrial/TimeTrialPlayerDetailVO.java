package info.team23h.acc.vo.front.timeTrial;

import info.team23h.acc.vo.recode.RecordVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
public class TimeTrialPlayerDetailVO {
	@ApiModelProperty(value = "lastName", name = "lastName")
	private String lastName;

	@ApiModelProperty(value = "firstName", name = "firstName")
	private String firstName;

	@ApiModelProperty(value = "플레이어 아이디", name = "playerId")
	private String playerId;

	@ApiModelProperty(value = "트랙명", name = "trackName")
	private String trackName;

	@ApiModelProperty(value = "차량명", name = "carName")
	private String carName;

	@ApiModelProperty(value = "베스트렙", name = "bestLap")
	private Long bestLap;

	@ApiModelProperty(value = "전체 플레이어", name = "allPlayer")
	private Long allPlayer;

	@ApiModelProperty(value = "랭크", name = "rank")
	private Long rank;

	@ApiModelProperty(value = "총 랩수", name = "totalLap")
	private Long totalLap;


	public TimeTrialPlayerDetailVO(RecordVO recordVO) {
		this.lastName = recordVO.getLastName();
		this.firstName = recordVO.getFirstName();
		this.playerId = recordVO.getPlayerId();
		if(!ObjectUtils.isEmpty(recordVO.getTrackViewName())){
			this.trackName = recordVO.getTrackViewName();
		}else{
			this.trackName = recordVO.getTrackName();
		}
		this.carName = recordVO.getCarName();
		this.bestLap = Long.valueOf(recordVO.getBestLap());
		this.allPlayer = Long.valueOf(recordVO.getMaxPlayer());
		this.rank = Long.valueOf(recordVO.getRank());
		this.totalLap = recordVO.getLapCount();
	}
}
