package info.team23h.acc.entity.poster;

import info.team23h.acc.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "TB_PLAYER")
public class Poster extends BaseTimeEntity {

	@Id
	@Column(name = "POSTER_KEY")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long posterKey;

	@Column(name="TYPE")
	private String type;

	@Column(name="fileName")
	private String fileName;

	@Column(name="filePath")
	private String filePath;



}
