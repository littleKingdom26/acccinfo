package info.team23h.acc.entity.bbs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import info.team23h.acc.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "TB_COMMENT")
public class BbsComment extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ")
	private Long seq;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "REG_ID")
	private String regId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="BBS_SEQ")
	@JsonBackReference
	private Bbs bbs;

	@Builder
	public BbsComment(String comment, String regId, Bbs bbs) {
		this.comment = comment;
		this.regId = regId;
		this.bbs = bbs;
	}
}
