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
	@Column(name = "EVENT_INFO_SEQ")
	private Long eventInfoSeq;

	@Column(name = "ROUND")
	private Long round;

	@OneToOne
	@JoinColumn(name = "PLAYER_ID")
	private Player player;

	@Column(name="CAR_ID")
	private String carId;

	@OneToOne
	@JoinColumn(name="CAR_MODEL")
	private Car car;

	@Column(name="RACE_TIME")
	private long raceTime;

	@Column(name = "TOTAL_TIME")
	private long totalTime;

	@Column(name = "TOTAL_LAP")
	private long totalLap;

	@Column(name = "BEST_LAP")
	private long bestLap;

	@Column(name = "SECTOR1")
	private long sector1;

	@Column(name = "SECTOR2")
	private long sector2;

	@Column(name = "SECTOR3")
	private long sector3;

	@Column(name = "RANK")
	private long rank;

	@Column(name = "SCORE")
	private long score;

	@Column(name = "HANDICAP")
	private long handicap;

	@Column(name = "PENALTY")
	private long penalty;

	@Column(name = "MISS_MANDATORY_PIT_STOP")
	private long missMandatoryPitStop;

}
