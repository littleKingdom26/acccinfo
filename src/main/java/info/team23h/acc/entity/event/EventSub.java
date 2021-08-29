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
@Table(name = "TB_EVENT_SUB")
public class EventSub extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EVENT_SUB_SEQ")
	private Long eventSubSeq;

	@Column(name = "EVENT_INFO_SEQ")
	private Long eventInfoSeq;

	@Column(name = "ROUND")
	private Long round;

	@Column(name = "CAR_ID")
	private String carId;

	@Column(name = "LAP")
	private long lap;

	@Column(name = "LAP_TIME")
	private long lapTime;

	@Column(name = "SECTOR1")
	private long sector1;

	@Column(name = "SECTOR2")
	private long sector2;

	@Column(name = "SECTOR3")
	private long sector3;



}
