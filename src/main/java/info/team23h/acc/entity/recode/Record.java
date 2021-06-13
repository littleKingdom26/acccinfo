package info.team23h.acc.entity.recode;


import info.team23h.acc.entity.car.Car;
import info.team23h.acc.entity.player.Player;
import info.team23h.acc.entity.track.Track;
import info.team23h.acc.entity.week.Week;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_RECORD")
public class Record {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ")
	private Long seq;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SESSION_ID")
	private Week week;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLAYER_ID")
	private Player player;

	@Column(name = "BEST_LAP")
	private Long bestLap;

	@Column(name = "SECTOR1")
	private Long sector1;

	@Column(name = "SECTOR2")
	private Long sector2;

	@Column(name = "SECTOR3")
	private Long sector3;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAR_MODEL")
	private Car car;

	@Column(name = "LAP_COUNT")
	private Long lapCount;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRACK_SEQ")
	private Track track;


}
