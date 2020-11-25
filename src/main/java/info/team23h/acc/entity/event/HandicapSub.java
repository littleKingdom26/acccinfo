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
@Table(name = "TB_HANDICAP")
public class HandicapSub extends BaseTimeEntity {

	@Id
	@Column(name = "RANK")
	private Long rank;

	@Column(name = "HANDICAP")
	private Long handicap;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="HANDICAP_INFO_SEQ")
	private HandicapInfo handicapInfo;
}
