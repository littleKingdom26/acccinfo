package info.team23h.acc.entity.event;

import info.team23h.acc.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_SCORE")
public class ScoreSub extends BaseTimeEntity {


	@Id
	@Column(name="RANK")
	private Long rank;

	@Column(name="SCORE")
	private Long score;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="SCORE_INFO_SEQ")
	private ScoreInfo scoreInfo;

}
