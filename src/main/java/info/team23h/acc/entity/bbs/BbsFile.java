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
@Table(name = "TB_FILE")
public class BbsFile extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_SEQ")
	private Long fileSeq;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name = "FILE_PATH")
	private String filePath;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="SEQ")
	@JsonBackReference
	private Bbs bbs;
}
