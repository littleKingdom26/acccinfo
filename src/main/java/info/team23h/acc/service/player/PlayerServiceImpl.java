package info.team23h.acc.service.player;

import info.team23h.acc.dao.PlayerDAO;
import info.team23h.acc.vo.player.PlayerSearch;
import info.team23h.acc.vo.player.PlayerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	PlayerDAO playerDAO;

	@Override
	public List<PlayerVO> getPlayerList() {

		return playerDAO.getPlayerList(new PlayerSearch());
	}

	@Override
	public int createDriver(PlayerVO playerVO) {
		return playerDAO.createDriver(playerVO);

	}

	@Override
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
		return playerDAO.getPlayerList(playerSearch);
	}

}