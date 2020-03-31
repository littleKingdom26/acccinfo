package info.team23h.acc.service;

import info.team23h.acc.dao.RecordDAO;
import info.team23h.acc.util.AccumulatedSkillEvaluator;
import info.team23h.acc.util.MathUtil;
import info.team23h.acc.vo.RecordVO;
import info.team23h.acc.vo.SearchVO;
import info.team23h.acc.vo.WeekVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RecordServiceImpl implements RecordService {

	@Autowired
	RecordDAO recordDAO;

	@Autowired
	WeekService weekService;

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

	/**
	 * 기록 저장
	 * @param recordVO
	 * @return
	 */
	private int createRecordData(RecordVO recordVO) {
		return recordDAO.createRecordData(recordVO);
	}

	/**
	 * 기존 기록 수정
	 * @param recordVO
	 * @return
	 */
	private int updateRecordData(RecordVO recordVO) {
		return recordDAO.updateRecordData(recordVO);
	}

	@Override
	public RecordVO getRecordData(RecordVO recordVO) {

		return recordDAO.getRecordData(recordVO);
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

		for(int i = 0; i < list.size(); i++){
			RecordVO temp = list.get(i);
			viewSetting(temp);
		}

		return list;
	}

	@Override
	public Double playerSkillEvaluator(SearchVO searchVO) {

		// 최신주차 10개 구하기
		WeekVO weekVO = weekService.getRecently();
		int newSessionId = weekVO.getSessionId();

		List<List<RecordVO>> result = new ArrayList<>();
		for(int i = 0; i < 11; i++){
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
		log.debug("user.getScore() > " + user.getScore());
		return user.getScore();
	}
}
