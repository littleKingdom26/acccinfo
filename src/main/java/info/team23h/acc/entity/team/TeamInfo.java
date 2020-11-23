package info.team23h.acc.entity.team;


import com.fasterxml.jackson.annotation.JsonBackReference;
import info.team23h.acc.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_TEAM_INFO")
public class TeamInfo extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEAM_INFO_SEQ")
	private Long teamInfoSeq;

	@Column(name="TEAM_NAME")
	private String teamName;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "teamInfo",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Team> team;


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teamInfo")
	@JsonBackReference
	private List<TeamScore> teamScore;


	@Builder
	public TeamInfo(final String teamName){
		this.teamName = teamName;
	}



}
