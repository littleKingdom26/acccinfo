package info.team23h.acc.service;

import info.team23h.acc.vo.PlayerVO;
import info.team23h.acc.vo.RecordVO;
import info.team23h.acc.vo.SearchVO;

import java.util.List;

public interface RecordService {

	/**
	 * 데이터 기록
	 * @param recordVO
	 * @return
	 */
	int setRecordData(RecordVO recordVO);


	int setRecordData_GT4(RecordVO recordVO);

	/**
	 * 기존 데이터 조회  [베렙 가장 낮은것]
	 * @param recordVO
	 * @return
	 */
	RecordVO getRecordData(RecordVO recordVO);

	/**
	 * 레코드 데이터 조회
	 * @param searchVO
	 * @return
	 */
	List<RecordVO> getRecordDataList(SearchVO searchVO);

	List<RecordVO> getRecordDataList_GT4(SearchVO searchVO);

	/**
	 * 플레이어 코스별 기록 조회
	 * @param searchVO
	 * @return
	 */
	List<RecordVO> recordPlayerDetail(SearchVO searchVO);

	List<RecordVO> recordPlayerDetail_GT4(SearchVO searchVO);

	/**
	 * 차량 코스별 기록 조회
	 * @param searchVO
	 * @return
	 */
	List<RecordVO> recordCarDetail(SearchVO searchVO);

	/**
	 * 플레이어 별 스코어
	 * @param searchVO
	 * @return
	 */
	Double playerSkillEvaluator(SearchVO searchVO);

	/**
	 * 플레이어 리스트  tt 스코어 입력
	 * @param driverList
	 * @return
	 */
	List<PlayerVO> getPlayerSkillEvaluatorList(List<PlayerVO> driverList);
}
