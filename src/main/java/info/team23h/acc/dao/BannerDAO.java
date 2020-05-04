package info.team23h.acc.dao;

import info.team23h.acc.vo.BannerSearch;
import info.team23h.acc.vo.BannerVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerDAO {

	@Autowired
	private SqlSession sql;

	private final String PREFIX = "bannerSql";

	public int saveBanner(BannerVO bannerVO) {
		return sql.insert(PREFIX+".saveBanner",bannerVO);

	}

	public List<BannerVO> getBannerList(BannerSearch bannerSearch) {
		return sql.selectList(PREFIX+".getBannerList", bannerSearch);
	}

	public int delBanner(BannerVO bannerVO) {
		return sql.delete(PREFIX+".delBanner",bannerVO);
	}
}
