package info.team23h.acc.entity.track;


import info.team23h.acc.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_TRACK")
public class Track extends BaseTimeEntity {

	@Column(name="SEQ")
	private Long seq;

	@Id
	@Column(name = "TRACK_NAME")
	private String trackName;

	@Column(name = "TRACK_VIEW_NAME")
	private String trackViewName;
}
