package info.team23h.acc.service;

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
}
