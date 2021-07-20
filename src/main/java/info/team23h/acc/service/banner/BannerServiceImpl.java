package info.team23h.acc.service.banner;

import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.dao.BannerDAO;
import info.team23h.acc.util.FileUtil;
import info.team23h.acc.vo.banner.BannerSearch;
import info.team23h.acc.vo.banner.BannerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
			String newPath = FileUtil.save(bannerVO.getBannerImg(), EnumCode.filePath.banner.name());
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

	@Override
	public HashMap<String, Object> getFrontBannerList(BannerSearch bannerSearch) {
		List<BannerVO> bannerList = this.getBannerList(bannerSearch);
		int maxFor = bannerList.size()/4;
		maxFor += bannerList.size()%4;
		HashMap<String,Object> result = new HashMap<>();
		List<HashMap<String, Object>> rowList = new ArrayList<>();
		for(int i = 0; i < maxFor; i++){
			int listSize = (i+1)*4;
			if(listSize >= bannerList.size()){
				listSize = bannerList.size();
			}
			HashMap<String,Object> colMap = new HashMap<>();
			List<BannerVO> colList = new ArrayList<>();
			for(int j=i*4;j<listSize;j++){
				colList.add(bannerList.get(j));
			}
			colMap.put("colList", colList);
			rowList.add(colMap);
		}
		result.put("rowList", rowList);

		return result;
	}

}
