package info.team23h.acc.entity.poster;

import info.team23h.acc.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "TB_POSTER")
public class Poster extends BaseTimeEntity {

	@Id
	@Column(name = "POSTER_KEY")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long posterKey;

	@Column(name="TYPE")
	private String type;

	@Column(name = "TITLE")
	private String title;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_PATH")
	private String filePath;


	@Builder
	public Poster(String type, String fileName, String filePath,String title) {
		this.type = type;
		this.fileName = fileName;
		this.filePath = filePath;
		this.title = title;
	}
}
