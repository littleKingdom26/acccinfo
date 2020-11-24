package info.team23h.acc.entity.event;


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
@Table(name = "TB_SCORE_INFO")
public class ScoreInfo extends BaseTimeEntity {

	@Id
	@Column(name = "SCORE_INFO_SEQ")
	private Long scoreInfoSeq;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "PARTICIPATION_YN")
	private String participationYn;

	@OneToMany(mappedBy = "scoreInfo",fetch = FetchType.LAZY)
	private List<Score> ScoreList;



}
