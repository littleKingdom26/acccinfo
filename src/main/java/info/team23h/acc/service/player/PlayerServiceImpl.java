package info.team23h.acc.service.player;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.dao.PlayerDAO;
import info.team23h.acc.entity.player.Player;
import info.team23h.acc.repository.player.PlayerRepository;
import info.team23h.acc.vo.player.PlayerSearch;
import info.team23h.acc.vo.player.PlayerVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerServiceImpl implements PlayerService {

	final PlayerDAO playerDAO;

	final PlayerRepository playerRepository;

	@Override
	public List<PlayerVO> getPlayerList() {
		return playerDAO.getPlayerList(new PlayerSearch());
	}

	@Override
	public int createDriver(PlayerVO playerVO) {
		return playerDAO.createDriver(playerVO);
	}

	@Override
	@Transactional
	public int updateDriver(PlayerVO playerVO) {
		return playerDAO.updateDriver(playerVO);
	}

	@Override
	public int getPlayerDetail(PlayerVO playerVO) {
		return playerDAO.getPlayerDetail(playerVO);
	}

	/**
	 * 관리자용
	 * @param playerSearch
	 * @return
	 */
	@Override
	public List<PlayerVO> getPlayerList(PlayerSearch playerSearch) {
		//
		List<PlayerVO> resultList = playerRepository
				.findAllByFirstNameContainsOrLastNameContains(playerSearch.getKeyword(), playerSearch.getKeyword()).stream().map(PlayerVO::new)
				.collect(Collectors.toList());
		return resultList;
	}

	@Override
	public PlayerVO findById(String playerId) {
		final Player byId = playerRepository.findById(playerId).orElseThrow(() -> new Team23hException("플레이어 정보가 없습니다."));
		return new PlayerVO(byId);
	}

}
