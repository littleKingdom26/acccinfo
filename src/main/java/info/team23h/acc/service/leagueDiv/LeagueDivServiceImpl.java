package info.team23h.acc.service.leagueDiv;


import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.entity.leagueDiv.LeagueDiv;
import info.team23h.acc.repository.leagueDiv.LeagueDivRepository;
import info.team23h.acc.vo.leagueDiv.LeagueDivResultVO;
import info.team23h.acc.vo.leagueDiv.LeagueDivSaveVO;
import info.team23h.acc.vo.leagueDiv.LeagueDivVO;
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
public class LeagueDivServiceImpl implements LeagueDivService {

	final private LeagueDivRepository leagueDivRepository;

	@Override
	@Transactional
	public void save(LeagueDivSaveVO leagueDivSaveVO) {
		leagueDivRepository.deleteAll();
		for(LeagueDivVO leagueDivVO : leagueDivSaveVO.getMasterList()) {
			final LeagueDiv build = LeagueDiv.builder()
			                                 .playerId(leagueDivVO.getPlayerId())
			                                 .ballast(leagueDivVO.getBallast())
			                                 .carModel(leagueDivVO.getCarModel())
			                                 .leagueDiv(EnumCode.LeagueDivision.DIVISION_2.name())
			                                 .carNumber(leagueDivVO.getCarNumber())
			                                 .build();
			leagueDivRepository.save(build);
		}

		for(LeagueDivVO leagueDivVO : leagueDivSaveVO.getProList()) {
			final LeagueDiv build = LeagueDiv.builder()
			                                 .playerId(leagueDivVO.getPlayerId())
			                                 .ballast(leagueDivVO.getBallast())
			                                 .carModel(leagueDivVO.getCarModel())
			                                 .leagueDiv(EnumCode.LeagueDivision.DIVISION_1.name())
			                                 .carNumber(leagueDivVO.getCarNumber())
			                                 .build();
			leagueDivRepository.save(build);
		}
	}

	@Override
	public List<LeagueDivResultVO> findByLeagueDiv(String leagueDiv) {
		return leagueDivRepository.findByLeagueDivEquals(leagueDiv)
		                          .stream()
		                          .map(LeagueDivResultVO::new)
		                          .collect(Collectors.toList());
	}
}

