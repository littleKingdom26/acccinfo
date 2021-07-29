package info.team23h.acc.entity.bbs;


import com.fasterxml.jackson.annotation.JsonBackReference;
import info.team23h.acc.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "TB_BBS_NAME")
public class TbBbsName extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ")
	private Long seq;

	@Column(name = "BBS_NAME")
	private String bbsName;

	@Column(name="BBS_TYPE")
	private String bbsType;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="NAME_SEQ")
	@JsonBackReference
	private List<Bbs> bbsList;

}
