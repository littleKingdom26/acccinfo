package info.team23h.acc.service.timeTrial;


import info.team23h.acc.config.Team23hException;
import info.team23h.acc.dao.RecordDAO;
import info.team23h.acc.entity.player.Player;
import info.team23h.acc.entity.recode.Record;
import info.team23h.acc.entity.recode.RecordGt4;
import info.team23h.acc.entity.week.Week;
import info.team23h.acc.repository.recode.RecordGt4Repository;
import info.team23h.acc.repository.recode.RecordRepository;
import info.team23h.acc.repository.week.WeekRepository;
import info.team23h.acc.restController.front.timeTrial.TimeTrialRestController;
import info.team23h.acc.vo.common.SearchVO;
import info.team23h.acc.vo.front.timeTrial.TimeTrialPlayerDetailVO;
import info.team23h.acc.vo.front.timeTrial.TimeTrialResultVO;
import info.team23h.acc.vo.recode.RecordVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class timeTrialServiceImpl implements TimeTrialService{

	final private RecordRepository recodeRepository;

	final private RecordGt4Repository recodeGt4Repository;

	final private WeekRepository weekRepository;

	final private RecordDAO recordDAO;

	@Override
	public List<TimeTrialResultVO> findGt3WeekTimeTrial(Long sessionId) {
		final Week week = weekRepository.findById(sessionId).orElseThrow(() -> new Team23hException("주차 정보가 없음"));
		final List<Record> allByWeek = recodeRepository.findAllByWeek(week);
		final List<TimeTrialResultVO> detail = allByWeek.stream().sorted(Comparator.comparingLong(Record::getBestLap)).map(TimeTrialResultVO::new).map(timeTrialResultVO -> {
			WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TimeTrialRestController.class).findGt3PlayerIdDetail(timeTrialResultVO.getPlayerId()));
			timeTrialResultVO.set_link(linkTo.withRel("detail"));
			return timeTrialResultVO;
		}).collect(Collectors.toList());
		return detail;
	}

	@Override
	public List<TimeTrialPlayerDetailVO> findGt3PlayerIdDetail(String playerId) {
		SearchVO searchVO = new SearchVO();
		searchVO.setPlayerId(playerId);
		final List<RecordVO> recordList = recordDAO.recordPlayerDetail(searchVO);
		final List<Long> trackSeqList = recordList.parallelStream().map(RecordVO::getTrackSeq).collect(Collectors.toList());

		final List<Record> allRecordList = recodeRepository.findAllByTrack_SeqIn(trackSeqList);


		recordList.forEach(recordVO -> {
			// 트랙데이터만 조회
			final List<Record> collect = allRecordList.stream().filter(record -> recordVO.getTrackSeq() == record.getTrack().getSeq()).collect(Collectors.toList());
			collect.sort(Comparator.comparingLong(Record::getBestLap));
			final List<String> orderList = collect.stream().map(Record::getPlayer).map(Player::getPlayerId).distinct().collect(Collectors.toList());
			final int i = orderList.indexOf(recordVO.getPlayerId())+1;

			recordVO.setMaxPlayer(Long.valueOf(orderList.parallelStream().count()).intValue());
			recordVO.setRank(i);
			}
		);
		return recordList.parallelStream().map(TimeTrialPlayerDetailVO::new).collect(Collectors.toList());
	}

	@Override
	public List<TimeTrialResultVO> findGt4WeekTimeTrial(Long sessionId) {
		final Week week = weekRepository.findById(sessionId).orElseThrow(() -> new Team23hException("주차 정보가 없음"));
		final List<RecordGt4> allByWeek = recodeGt4Repository.findAllByWeek(week);
		final List<TimeTrialResultVO> detail = allByWeek.stream().sorted(Comparator.comparingLong(RecordGt4::getBestLap)).map(TimeTrialResultVO::new).map(timeTrialResultVO -> {
			WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TimeTrialRestController.class).findGt3PlayerIdDetail(timeTrialResultVO.getPlayerId()));
			timeTrialResultVO.set_link(linkTo.withRel("detail"));
			return timeTrialResultVO;
		}).collect(Collectors.toList());
		return detail;
	}

	@Override
	public List<TimeTrialPlayerDetailVO> findGt4PlayerIdDetail(String playerId) {
		SearchVO searchVO = new SearchVO();
		searchVO.setPlayerId(playerId);
		final List<RecordVO> recordList = recordDAO.recordPlayerDetail_GT4(searchVO);
		final List<Long> trackSeqList = recordList.parallelStream().map(RecordVO::getTrackSeq).collect(Collectors.toList());
		final List<RecordGt4> allRecordList = recodeGt4Repository.findAllByTrack_SeqIn(trackSeqList);
		recordList.forEach(recordVO -> {
			final List<RecordGt4> collect = allRecordList.stream().filter(record -> recordVO.getTrackSeq() == record.getTrack().getSeq()).collect(Collectors.toList());
			collect.sort(Comparator.comparingLong(RecordGt4::getBestLap));
			final List<String> orderList = collect.stream().map(RecordGt4::getPlayer).map(Player::getPlayerId).distinct().collect(Collectors.toList());
			final int i = orderList.indexOf(recordVO.getPlayerId()) + 1;

			recordVO.setMaxPlayer(Long.valueOf(orderList.parallelStream().count()).intValue());
			recordVO.setRank(i);
		});
		return recordList.parallelStream().map(TimeTrialPlayerDetailVO::new).collect(Collectors.toList());
	}

	@Override
	public List<TimeTrialResultVO> findGt3Track(Long trackSeq) {
		final List<Record> trackRecodeList = recodeRepository.findAllByTrack_Seq(trackSeq);
		// 플레이어 노출
		final List<Player> playerList = trackRecodeList.stream().map(Record::getPlayer).distinct().collect(Collectors.toList());

		List<TimeTrialResultVO> resultList = new ArrayList<>();
		playerList.forEach(player -> {
			trackRecodeList.stream()
						   .filter(record -> record.getPlayer().getPlayerId().equals(player.getPlayerId()))
						   .sorted(Comparator.comparingLong(Record::getBestLap))
						   .findFirst()
						   .ifPresent(record -> resultList.add(new TimeTrialResultVO(record)));
		});
		resultList.sort(Comparator.comparingLong(TimeTrialResultVO::getBestLap));
		return resultList;
	}

	@Override
	public List<TimeTrialResultVO> findGt4Track(Long trackSeq) {
		final List<RecordGt4> trackRecodeList = recodeGt4Repository.findAllByTrack_Seq(trackSeq);
		// 플레이어 노출
		final List<Player> playerList = trackRecodeList.stream().map(RecordGt4::getPlayer).distinct().collect(Collectors.toList());

		List<TimeTrialResultVO> resultList = new ArrayList<>();
		playerList.forEach(player -> {
			trackRecodeList.stream()
						   .filter(record -> record.getPlayer().getPlayerId().equals(player.getPlayerId()))
						   .sorted(Comparator.comparingLong(RecordGt4::getBestLap))
						   .findFirst()
						   .ifPresent(record -> resultList.add(new TimeTrialResultVO(record)));
		});
		resultList.sort(Comparator.comparingLong(TimeTrialResultVO::getBestLap));
		return resultList;
	}
}
