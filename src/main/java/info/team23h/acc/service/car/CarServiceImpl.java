package info.team23h.acc.service.car;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.dao.CarDAO;
import info.team23h.acc.entity.car.Car;
import info.team23h.acc.repository.car.CarRepository;
import info.team23h.acc.vo.car.CarResultVO;
import info.team23h.acc.vo.car.CarVO;
import info.team23h.acc.vo.common.SearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {


	final private CarDAO carDAO;

	final private CarRepository carRepository;

	@Override
	public List<CarVO> findAllCarList() {
		return carDAO.findAllCarList();
	}

	@Override
	public CarVO findCarDetail(SearchVO searchVO) {
		return carDAO.findCarDetail(searchVO);
	}

	@Override
	public CarResultVO findById(Long carModel) {
		final Car car = carRepository.findById(carModel)
		                             .orElseThrow(() -> new Team23hException("차량정보 없음!!"));
		CarResultVO result = new CarResultVO(car);
		return result;
	}

	@Override
	public List<CarResultVO> findAll() {
		final List<CarResultVO> resultList = carRepository.findAll()
		                                          .stream()
		                                          .map(CarResultVO::new)
		                                          .collect(Collectors.toList());
		return resultList;
	}


}
