package info.team23h.acc.entity.car;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "TB_CAR")
public class Car
{
	@Id
	@Column(name = "CAR_MODEL")
	private Long carModel;

	@Column(name = "CAR_NAME")
	private String carName;

	@Column(name = "CAR_TYPE")
	private String carType;
}
