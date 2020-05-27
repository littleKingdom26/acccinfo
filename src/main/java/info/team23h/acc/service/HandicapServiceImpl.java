package info.team23h.acc.service;

import info.team23h.acc.dao.HandicapDAO;
import info.team23h.acc.vo.HandicapInfoVO;
import info.team23h.acc.vo.HandicapVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class HandicapServiceImpl implements HandicapService {

	@Autowired
	HandicapDAO handicapDAO;

	@Override
	public List<HandicapInfoVO> getHandicapInfoList(){
		return handicapDAO.getHandicapInfoList();
	}

	@Override
	@Transactional
	public int insertHandicapInfo(HandicapInfoVO handicapInfoVO) {
		int cnt = handicapDAO.insertHandicapInfo(handicapInfoVO);
		log.debug("scoreInfoVO.toString() > " + handicapInfoVO.toString());
		for(int i = 0; i < handicapInfoVO.getRanks().size(); i++){
			HandicapVO handicapVO = new HandicapVO();
			if(!handicapInfoVO.getRanks().get(i).isEmpty() && !handicapInfoVO.getHandicaps().get(i).isEmpty()){
				handicapVO.setRank(Integer.parseInt(handicapInfoVO.getRanks().get(i)));
				handicapVO.setHandicap(Integer.parseInt(handicapInfoVO.getHandicaps().get(i)));
				handicapVO.setHandicapInfoSeq(handicapInfoVO.getHandicapInfoSeq());
				int handicapCnt = handicapDAO.insertHandicap(handicapVO);
			}
		}
		return cnt;
	}

	@Override
	public List<HandicapVO> getHandicap(HandicapInfoVO handicapInfoVO) {
		return handicapDAO.getHandicap(handicapInfoVO);
	}

	@Override
	public HandicapInfoVO getHandicapInfo(HandicapInfoVO handicapInfoVO) {
		HandicapInfoVO result = handicapDAO.getHandicapInfo(handicapInfoVO);
		return result;
	}

	@Override
	public HashMap<String, Object> delHandicapInfo(HandicapInfoVO handicapInfoVO) throws Exception {

		int cnt = handicapDAO.delHandicapInfo(handicapInfoVO);

		if(cnt > 0){
			cnt = handicapDAO.delHandicap(handicapInfoVO);
		}

		HashMap<String, Object> result = new HashMap<>();
		if(cnt == 0){
			throw new Exception();
		}else{
			result.put("code", "0000");
		}
		return result;
	}
}
