package info.team23h.acc.service.car;

import info.team23h.acc.vo.car.CarResultVO;
import info.team23h.acc.vo.car.CarVO;
import info.team23h.acc.vo.common.SearchVO;

import java.util.List;

public interface CarService {

	List<CarVO> findAllCarList();

	CarVO findCarDetail(SearchVO searchVO);

	CarResultVO findById(Long carModel);

	List<CarResultVO> findAll();
}
