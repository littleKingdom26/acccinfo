package info.team23h.acc.service;

import info.team23h.acc.vo.BannerSearch;
import info.team23h.acc.vo.BannerVO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface BannerService {

	int saveBanner(BannerVO bannerVO) throws IOException;

	List<BannerVO> getBannerList(BannerSearch bannerSearch);

	HashMap<String, Object> delBanner(BannerVO bannerVO);
}
