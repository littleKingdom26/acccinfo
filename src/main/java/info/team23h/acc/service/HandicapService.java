package info.team23h.acc.service;

import info.team23h.acc.vo.HandicapInfoVO;
import info.team23h.acc.vo.HandicapVO;

import java.util.HashMap;
import java.util.List;

public interface HandicapService {
	/**
	 * 핸디캡 정보 리스트 조회
	 * @return
	 */
	List<HandicapInfoVO> getHandicapInfoList();

	/**
	 * 핸디캡 정보 입력
	 *
	 * @param handicapInfoVO the handicap info vo
	 * @return int
	 */
	int insertHandicapInfo(HandicapInfoVO handicapInfoVO);

	/**
	 * 핸디캡 조회
	 *
	 * @param handicapInfoVO the handicap info vo
	 * @return handicap
	 */
	List<HandicapVO> getHandicap(HandicapInfoVO handicapInfoVO);

	/**
	 * 핸디캡 정보 조회
	 *
	 * @param handicapInfoVO the handicap info vo
	 * @return handicap info
	 */
	HandicapInfoVO getHandicapInfo(HandicapInfoVO handicapInfoVO);

	/**
	 * 핸디캡 정보 삭제
	 *
	 * @param handicapInfoVO the handicap info vo
	 * @return hash map
	 * @throws Exception the exception
	 */
	HashMap<String, Object> delHandicapInfo(HandicapInfoVO handicapInfoVO) throws Exception;

}
