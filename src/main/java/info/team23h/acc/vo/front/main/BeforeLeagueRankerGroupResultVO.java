package info.team23h.acc.vo.front.main;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BeforeLeagueRankerGroupResultVO {


	@ApiModelProperty(value = "리그구분", name = "division")
	private String division;
	@ApiModelProperty(value = "리그명",name = "leagueName")
	private String leagueName;

	@ApiModelProperty(value = "리그 랭커 목록",name = "beforeLeagueRankerResultList")
	private List<BeforeLeagueRankerResultVO> beforeLeagueRankerResultList;

	@Builder
	public BeforeLeagueRankerGroupResultVO(String leagueName, List<BeforeLeagueRankerResultVO> beforeLeagueRankerResultList,String division) {
		this.leagueName = leagueName;
		this.beforeLeagueRankerResultList = beforeLeagueRankerResultList;
		this.division = division;
	}
}
