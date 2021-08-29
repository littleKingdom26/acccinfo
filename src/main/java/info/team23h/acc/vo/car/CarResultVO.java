package info.team23h.acc.vo.car;

import info.team23h.acc.entity.car.Car;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResultVO {
	private Long carModel;
	private String carName;
	private String carType;

	public CarResultVO(Car car) {
		this.carModel = car.getCarModel();
		this.carName = car.getCarName();
		this.carType = car.getCarType();
	}



}
