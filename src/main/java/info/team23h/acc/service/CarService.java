package info.team23h.acc.service;

import info.team23h.acc.vo.CarVO;
import info.team23h.acc.vo.SearchVO;

import java.util.List;

public interface CarService {
	List<CarVO> findAllCarList();

	CarVO findCarDetail(SearchVO searchVO);
}
