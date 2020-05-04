package info.team23h.acc.service;

import info.team23h.acc.dao.BannerDAO;
import info.team23h.acc.util.FileUtil;
import info.team23h.acc.vo.BannerSearch;
import info.team23h.acc.vo.BannerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService{

	final String PATH = "banner";

	@Value("${file.upload.rootpath}")
	String rootPath;

	@Autowired
	BannerDAO bannerDAO;

	@Override
	public int saveBanner(BannerVO bannerVO) throws IOException {

		if(bannerVO.getBannerImg()!=null && !bannerVO.getBannerImg().isEmpty()){
			String newPath = FileUtil.save(bannerVO.getBannerImg(), PATH);
			bannerVO.setFilePath(File.separator+"imageView"+ File.separator+ PATH + File.separator + newPath);
			bannerVO.setUseYn("Y");
			int cnt = bannerDAO.saveBanner(bannerVO);
			return cnt;
		}
		return 0;
	}

	@Override
	public List<BannerVO> getBannerList(BannerSearch bannerSearch) {
		if(bannerSearch.getUseYn()==null || "".equals(bannerSearch.getUseYn())){
			bannerSearch.setUseYn("Y");
		}
		return bannerDAO.getBannerList(bannerSearch);
	}

	@Override
	public HashMap<String, Object> delBanner(BannerVO bannerVO) {
		int cnt = bannerDAO.delBanner(bannerVO);
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(cnt > 0){
			result.put("code", "0000");
		}else{
			result.put("code", "9999");
		}
		return result;
	}
}
