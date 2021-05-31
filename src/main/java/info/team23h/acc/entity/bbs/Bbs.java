package info.team23h.acc.entity.bbs;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import info.team23h.acc.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "TB_BBS")
public class Bbs extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name="NAME_SEQ")
	@JsonManagedReference
	private TbBbsName tbBbsName;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "REG_ID")
	private String regId;

	@Column(name = "PASSWORD")
	private String password;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="BBS_SEQ")
	@JsonManagedReference
	private List<BbsComment> bbsCommentList;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEQ")
	@JsonManagedReference
	private List<BbsFile> bbsFileList;

}
