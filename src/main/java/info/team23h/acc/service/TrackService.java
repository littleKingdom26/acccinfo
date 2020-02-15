package info.team23h.acc.service;

import info.team23h.acc.vo.SearchVO;
import info.team23h.acc.vo.TrackVO;

import java.util.List;


public interface TrackService {

	/**
	 * 트랙명으로  SEQ 조회
	 * @param trackVO
	 * @return
	 */
	void getSeqForTrackName(TrackVO trackVO);

	/**
	 * seq 로 트랙명 조회
	 * @param param
	 * @return
	 */
	TrackVO getTrackNameForSeq(TrackVO param);

	/**
	 * 트랙 리스트 구하기
	 *
	 * @return
	 * @param searchVO
	 */
	List<TrackVO> getTrackList(SearchVO searchVO);
}
