package info.team23h.acc.entity.event;


import info.team23h.acc.entity.BaseTimeEntity;
import info.team23h.acc.entity.track.Track;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_EVENT_META")
public class EventMeta extends BaseTimeEntity implements Serializable {

	@Id
	@Column(name="EVENT_INFO_SEQ")
	private Long eventInfoSeq;

	@Column(name = "ROUND")
	private Long round;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TRACK_SEQ")
	private Track track;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EVENT_INFO_SEQ",insertable = false,updatable = false)
	private EventInfo eventInfo;
}
