package info.team23h.acc.vo.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamScoreResultDetailVO {

	private Long round;

	private Long score;

	private String eventDT;

	private String trackViewName;

	private String trackName;

	private String firstName;

	private String lastName;

	private String steamAvatar;

	private String title;

}
