package info.team23h.acc.service.banner;

import info.team23h.acc.vo.banner.BannerSearch;
import info.team23h.acc.vo.banner.BannerVO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface BannerService {

	int saveBanner(BannerVO bannerVO) throws IOException;

	List<BannerVO> getBannerList(BannerSearch bannerSearch);

	HashMap<String, Object> delBanner(BannerVO bannerVO);

	HashMap<String, Object> getFrontBannerList(BannerSearch bannerSearch);
}
