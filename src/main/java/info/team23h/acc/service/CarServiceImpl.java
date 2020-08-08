package info.team23h.acc.service;

import info.team23h.acc.dao.CarDAO;
import info.team23h.acc.vo.CarVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarDAO carDAO;

	@Override
	public List<CarVO> findAllCarList() {
		return carDAO.findAllCarList();
	}
}
