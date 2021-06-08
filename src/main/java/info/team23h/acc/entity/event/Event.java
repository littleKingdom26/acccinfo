package info.team23h.acc.entity.event;


import info.team23h.acc.entity.BaseTimeEntity;
import info.team23h.acc.entity.car.Car;
import info.team23h.acc.entity.player.Player;
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
@Table(name = "TB_EVENT")
public class Event extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EVENT_SEQ")
	private Long eventSeq;

	@Column(name = "EVENT_INFO_SEQ")
	private Long eventInfoSeq;

	@Column(name = "ROUND")
	private Long round;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLAYER_ID")
	private Player player;

	@Column(name="CAR_ID")
	private String carId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CAR_MODEL")
	private Car car;

	@Column(name="RACE_TIME")
	private Long raceTime;

	@Column(name = "TOTAL_TIME")
	private Long totalTime;

	@Column(name = "TOTAL_LAP")
	private Long totalLap;

	@Column(name = "BEST_LAP")
	private Long bestLap;

	@Column(name = "SECTOR1")
	private Long sector1;

	@Column(name = "SECTOR2")
	private Long sector2;

	@Column(name = "SECTOR3")
	private Long sector3;

	@Column(name = "RANK")
	private Long rank;

	@Column(name = "SCORE")
	private Long score;

	@Column(name = "HANDICAP")
	private Long handicap;

	@Column(name = "PENALTY")
	private Long penalty;

	@Column(name = "MISS_MANDATORY_PIT_STOP")
	private Long missMandatoryPitStop;

}
