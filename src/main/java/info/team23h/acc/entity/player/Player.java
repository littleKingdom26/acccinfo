package info.team23h.acc.entity.player;


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
@Table(name = "TB_PLAYER")
public class Player extends BaseTimeEntity {

	@Id
	@Column(name = "PLAYER_ID")
	private String PlayerId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "TEAM_23")
	private String team23;

	@Column(name = "STEAM_AVATAR")
	private String steamAvatar;



}
