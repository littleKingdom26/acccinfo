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
@Table(name = "TB_HANDICAP_INFO")
public class HandicapInfo extends BaseTimeEntity {

	@Id
	@Column(name="HANDICAP_INFO_SEQ")
	private Long handicapInfoSeq;

	@Column(name="TITLE")
	private String title;

	@OneToMany(mappedBy = "handicapInfo",fetch = FetchType.LAZY)
	private List<Handicap> handicapList;

}
