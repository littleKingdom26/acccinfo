package info.team23h.acc.dao;

import info.team23h.acc.vo.CarVO;
import info.team23h.acc.vo.SearchVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDAO {

	@Autowired
	private SqlSession sql;

	private final String PREFIX = "carSql";

	public List<CarVO> findAllCarList() {
		return sql.selectList(PREFIX+".findAllCarList");
	}

	public CarVO findCarDetail(SearchVO searchVO) {
		return sql.selectOne(PREFIX + ".findCarDetail",searchVO);
	}
}
