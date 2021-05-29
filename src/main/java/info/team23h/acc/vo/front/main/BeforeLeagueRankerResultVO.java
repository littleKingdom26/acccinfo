package info.team23h.acc.vo.front.main;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeforeLeagueRankerResultVO {

	@ApiModelProperty(value = "스팀프로필", name = "steamAvatar")
	private String steamAvatar;

	@ApiModelProperty(value = "이름", name = "firstName")
	private String firstName;

	@ApiModelProperty(value = "성", name = "lastName")
	private String lastName;

	@ApiModelProperty(value = "순위", name = "rank")
	private int rank;

	@Builder
	public BeforeLeagueRankerResultVO(String steamAvatar, String firstName, String lastName, int rank) {
		this.steamAvatar = steamAvatar;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rank = rank;
	}
}
