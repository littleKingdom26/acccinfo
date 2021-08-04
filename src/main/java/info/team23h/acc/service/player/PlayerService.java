package info.team23h.acc.service.player;

import info.team23h.acc.vo.player.PlayerSearch;
import info.team23h.acc.vo.player.PlayerVO;

import java.util.List;


public interface PlayerService {

	/**
	 * 플레이어 조회
	 * @return
	 */
	List<PlayerVO> getPlayerList();

	/**
	 * 플레이어 생성
	 * @param playerVO
	 * @return
	 */
	int createDriver(PlayerVO playerVO);

	/**
	 * 플레이어 정보 수정
	 *
	 * @param playerVO
	 * @return
	 */
	int updateDriver(PlayerVO playerVO);

	/**
	 * 플레이어 상세 조호 ㅣ
	 * @param playerVO
	 * @return
	 */
	int getPlayerDetail(PlayerVO playerVO);


	/**
	 * 플레이어 리스트 검색 목록
	 * @param playerSearch
	 * @return
	 */
	List<PlayerVO> getPlayerList(PlayerSearch playerSearch);


	/**
	 * 아이디 검색
	 *
	 * @param playerId the player id
	 */
	PlayerVO findById(String playerId);
}
