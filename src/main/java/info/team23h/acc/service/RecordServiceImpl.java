package info.team23h.acc.service;

import info.team23h.acc.dao.RecordDAO;
import info.team23h.acc.util.AccumulatedSkillEvaluator;
import info.team23h.acc.util.MathUtil;
import info.team23h.acc.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class RecordServiceImpl implements RecordService {

	@Autowired
	RecordDAO recordDAO;

	@Autowired
	WeekService weekService;

	@Autowired
	CarService carService;

	@Override
	public int setRecordData(RecordVO recordVO) {

		int result = 0;
		// 내 기록중 내 베렙보다 빠른게 있는지 확인 필요
		RecordVO beforeRecord = this.getRecordData(recordVO);

		if(beforeRecord != null && beforeRecord.getBestLap() > 0){
			// 기존 기록이 있음
			RecordVO param = new RecordVO();
			param.setSessionId(recordVO.getSessionId());
			param.setPlayerId(recordVO.getPlayerId());			
			param.setTrackSeq(recordVO.getTrackSeq());
			param.setLapCount(recordVO.getLapCount());
			if(beforeRecord.getBestLap() > recordVO.getBestLap()){
				// 기존 베스트 랩이 느림
				param.setBestLap(recordVO.getBestLap());
				param.setCarModel(recordVO.getCarModel());
			}
			if(beforeRecord.getSector1() > recordVO.getSector1()){
				// 섹터 1 기존 기록이 느림
				param.setSector1(recordVO.getSector1());
			}
			if(beforeRecord.getSector2() > recordVO.getSector2()){
				// 섹터 2 기존 기록이 느림
				param.setSector2(recordVO.getSector2());
			}
			if(beforeRecord.getSector3() > recordVO.getSector3()){
				// 섹터 3 기존 기록이 느림
				param.setSector3(recordVO.getSector3());
			}

			result = this.updateRecordData(param);
		}else {
			// 기존 기록 없음
			result = this.createRecordData(recordVO);
		}
		return result;
	}

	@Override
	public int setRecordData_GT4(RecordVO recordVO) {

		int result = 0;
		// 내 기록중 내 베렙보다 빠른게 있는지 확인 필요
		RecordVO beforeRecord = this.getRecordData_GT4(recordVO);

		if(beforeRecord != null && beforeRecord.getBestLap() > 0){
			// 기존 기록이 있음
			RecordVO param = new RecordVO();
			param.setSessionId(recordVO.getSessionId());
			param.setPlayerId(recordVO.getPlayerId());
			param.setTrackSeq(recordVO.getTrackSeq());
			param.setLapCount(recordVO.getLapCount());
			if(beforeRecord.getBestLap() > recordVO.getBestLap()){
				// 기존 베스트 랩이 느림
				param.setBestLap(recordVO.getBestLap());
				param.setCarModel(recordVO.getCarModel());
			}
			if(beforeRecord.getSector1() > recordVO.getSector1()){
				// 섹터 1 기존 기록이 느림
				param.setSector1(recordVO.getSector1());
			}
			if(beforeRecord.getSector2() > recordVO.getSector2()){
				// 섹터 2 기존 기록이 느림
				param.setSector2(recordVO.getSector2());
			}
			if(beforeRecord.getSector3() > recordVO.getSector3()){
				// 섹터 3 기존 기록이 느림
				param.setSector3(recordVO.getSector3());
			}

			result = this.updateRecordData_GT4(param);
		}else{
			// 기존 기록 없음
			result = this.createRecordData_GT4(recordVO);
		}
		return result;
	}

	/**
	 * 기록 저장
	 * @param recordVO
	 * @return
	 */
	private int createRecordData(RecordVO recordVO) {
		return recordDAO.createRecordData(recordVO);
	}

	private int createRecordData_GT4(RecordVO recordVO) {
		return recordDAO.createRecordData_GT4(recordVO);
	}

	/**
	 * 기존 기록 수정
	 * @param recordVO
	 * @return
	 */
	private int updateRecordData(RecordVO recordVO) {
		return recordDAO.updateRecordData(recordVO);
	}

	private int updateRecordData_GT4(RecordVO recordVO) {
		return recordDAO.updateRecordData_GT4(recordVO);
	}

	@Override
	public RecordVO getRecordData(RecordVO recordVO) {
		return recordDAO.getRecordData(recordVO);
	}

	public RecordVO getRecordData_GT4(RecordVO recordVO) {
		return recordDAO.getRecordData_GT4(recordVO);
	}

	@Override
	public List<RecordVO> getRecordDataList(SearchVO searchVO) {

		if(searchVO.getSessionId() == null && searchVO.getTrackSeq() == null
		|| "".equals(searchVO.getSessionId()) && "".equals(searchVO.getTrackSeq())){
			WeekVO weekVO = weekService.getRecently();
			if(weekVO!= null){
				searchVO.setSessionId(String.valueOf(weekVO.getSessionId()));
				searchVO.setTrackSeq(String.valueOf(weekVO.getTrackSeq()));
			}
		}
		List<RecordVO> list = new ArrayList<RecordVO>();
		if("0".equals(searchVO.getSessionId())){
			list = recordDAO.getRecordDataListForTrackSeq(searchVO);
		} else{
			list = recordDAO.getRecordDataListForWeek(searchVO);
		}

		int bestLap = 0;
		for(int i = 0; i < list.size(); i++){
			RecordVO temp = list.get(i);
			if(bestLap == 0){
				bestLap = temp.getBestLap();
			}else{
				temp.setGap(MathUtil.secToMin(temp.getBestLap() - bestLap,false));
			}
			viewSetting(temp);
			if(temp.getRank() == 1){
				temp.setRankImg("/image/rank1.png");
			}else if(temp.getRank() == 2){
				temp.setRankImg("/image/rank2.png");
			}else if(temp.getRank() == 3){
				temp.setRankImg("/image/rank3.png");
			}

		}
		return list;
	}

	@Override
	public List<RecordVO> getRecordDataList_GT4(SearchVO searchVO) {

		if(searchVO.getSessionId() == null && searchVO.getTrackSeq() == null || "".equals(searchVO.getSessionId()) && "".equals(searchVO.getTrackSeq())){
			WeekVO weekVO = weekService.getRecently();
			if(weekVO != null){
				searchVO.setSessionId(String.valueOf(weekVO.getSessionId()));
				searchVO.setTrackSeq(String.valueOf(weekVO.getTrackSeq()));
			}
		}
		List<RecordVO> list = new ArrayList<RecordVO>();
		if("0".equals(searchVO.getSessionId())){
			list = recordDAO.getRecordDataListForTrackSeq_GT4(searchVO);
		}else{
			list = recordDAO.getRecordDataListForWeek_GT4(searchVO);
		}

		int bestLap = 0;
		for(int i = 0; i < list.size(); i++){
			RecordVO temp = list.get(i);
			if(bestLap == 0){
				bestLap = temp.getBestLap();
			}else{
				temp.setGap(MathUtil.secToMin(temp.getBestLap() - bestLap,
											  false));
			}
			viewSetting(temp);
			if(temp.getRank() == 1){
				temp.setRankImg("/image/rank1.png");
			}else if(temp.getRank() == 2){
				temp.setRankImg("/image/rank2.png");
			}else if(temp.getRank() == 3){
				temp.setRankImg("/image/rank3.png");
			}

		}
		return list;
	}

	private void viewSetting(RecordVO temp) {
		temp.setBestLapView(MathUtil.secToMin(temp.getBestLap(), true));
		temp.setSector1View(MathUtil.secToMin(temp.getSector1(), true));
		temp.setSector2View(MathUtil.secToMin(temp.getSector2(), true));
		temp.setSector3View(MathUtil.secToMin(temp.getSector3(), true));
		temp.setPotential(temp.getSector1()+temp.getSector2()+temp.getSector3());
		temp.setPotentialView(MathUtil.secToMin(temp.getPotential(), true));
	}

	@Override
	public List<RecordVO> recordPlayerDetail(SearchVO searchVO) {

		List<RecordVO> list = recordDAO.recordPlayerDetail(searchVO);
		return getRecordVOS(list);
	}

	@Override
	public List<RecordVO> recordPlayerDetail_GT4(SearchVO searchVO) {

		List<RecordVO> list = recordDAO.recordPlayerDetail_GT4(searchVO);
		return getRecordVOS(list);
	}

	@Override
	public List<RecordVO> recordCarDetail(SearchVO searchVO) {

		CarVO carVO = carService.findCarDetail(searchVO);
		List<RecordVO> list = null;
		if(carVO.getCarType().equals("GT3")){
			list = recordDAO.recordCarDetail(searchVO);
			list = getRecordVOS(list);
		}else if(carVO.getCarType().equals("GT4")){
			list = recordDAO.recordCarDetail_GT4(searchVO);
			list = getRecordVOS_GT4(list);
		}

		searchVO.setCarName(list.get(0).getCarName());
		return list;
	}

	private List<RecordVO> getRecordVOS(List<RecordVO> list) {
		List<RecordVO> allRecode = recordDAO.loadAllRecodeTrackData();
		for(int i = 0; i < list.size(); i++){
			RecordVO temp = list.get(i);
			viewSetting(temp);
			int rank = 0;
			int maxPlayer = 0;
			for(RecordVO recordVO : allRecode){
				if(temp.getTrackSeq() == recordVO.getTrackSeq()){
					maxPlayer = maxPlayer + 1;
					if(temp.getPlayerId().equals(recordVO.getPlayerId())){
						rank = maxPlayer;
					}
				}
			}
			temp.setRank(rank);
			temp.setMaxPlayer(maxPlayer);
		}
		return list;
	}

	private List<RecordVO> getRecordVOS_GT4(List<RecordVO> list) {
		List<RecordVO> allRecode = recordDAO.loadAllRecodeTrackData_GT4();
		for(int i = 0; i < list.size(); i++){
			RecordVO temp = list.get(i);
			viewSetting(temp);
			int rank = 0;
			int maxPlayer = 0;
			for(RecordVO recordVO : allRecode){
				if(temp.getTrackSeq() == recordVO.getTrackSeq()){
					maxPlayer = maxPlayer + 1;
					if(temp.getPlayerId()
						   .equals(recordVO.getPlayerId())){
						rank = maxPlayer;
					}
				}
			}
			temp.setRank(rank);
			temp.setMaxPlayer(maxPlayer);
		}
		return list;
	}

	@Override
	public Double playerSkillEvaluator(SearchVO searchVO) {

		// 최신주차 10개 구하기
		WeekVO weekVO = weekService.getRecently();
		List<List<RecordVO>> result = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			searchVO.setSessionId(String.valueOf(weekVO.getSessionId()-i));
			result.add(recordDAO.getRecordDataListForWeek(searchVO));
		}

		AccumulatedSkillEvaluator user = new AccumulatedSkillEvaluator();

		for(int i = 0; i < result.size(); i++){
			List<RecordVO> recordVOList = result.get(i);
			for(RecordVO recordVO : recordVOList){
				if(searchVO.getPlayerId().equals(recordVO.getPlayerId())){
					user.pushData(i,recordVO.getRank(), recordVOList.size());
					log.debug("user.pushData("+i+", "+recordVO.getRank()+", "+recordVOList.size()+")");
				}
			}
		}

		return 100 - user.getScore();
	}

	@Override
	public List<PlayerVO> getPlayerSkillEvaluatorList(List<PlayerVO> driverList) {
		WeekVO weekVO = weekService.getRecently();

		List<List<RecordVO>> result = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			SearchVO searchVO = new SearchVO();
			searchVO.setSessionId(String.valueOf(weekVO.getSessionId() - i));
			result.add(recordDAO.getRecordDataListForWeek(searchVO));
		}

		for(int i = 0; i < driverList.size(); i++){
			PlayerVO playerVO =  driverList.get(i);
			AccumulatedSkillEvaluator user = new AccumulatedSkillEvaluator();
			for(int j = 0; j < result.size(); j++){
				List<RecordVO> recordVOList = result.get(j);
				for(RecordVO recordVO : recordVOList){
					if(playerVO.getPlayerId().equals(recordVO.getPlayerId())){
						user.pushData(j, recordVO.getRank(), recordVOList.size());
					}
				}
			}
			playerVO.setTtScore((int)Math.round(Math.floor(100 - user.getScore())));
		}

		driverList.sort(Comparator.reverseOrder());

		for(int i = 0; i < driverList.size(); i++){
			PlayerVO playerVO = driverList.get(i);
			playerVO.setNo(i+1);
		}

		return driverList;
	}
}
