package info.team23h.acc.service.timeTrial;


import info.team23h.acc.config.Team23hException;
import info.team23h.acc.entity.recode.Record;
import info.team23h.acc.entity.week.Week;
import info.team23h.acc.repository.recode.RecodeRepository;
import info.team23h.acc.repository.week.WeekRepository;
import info.team23h.acc.restController.front.timeTrial.TimeTrialRestController;
import info.team23h.acc.vo.front.timeTrial.TimeTrialResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class timeTrialServiceImpl implements TimeTrialService{

	final private RecodeRepository recodeRepository;

	final private WeekRepository weekRepository;

	@Override
	public List<TimeTrialResultVO> findGt3WeekTimeTrial(Long sessionId) {
		final Week week = weekRepository.findById(sessionId).orElseThrow(() -> new Team23hException("주차 정보가 없음"));
		final List<Record> allByWeek = recodeRepository.findAllByWeek(week);
		final List<TimeTrialResultVO> detail = allByWeek.stream().sorted(Comparator.comparingLong(Record::getBestLap)).map(TimeTrialResultVO::new).map(timeTrialResultVO -> {
			WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TimeTrialRestController.class).findPlayerIdDetail(timeTrialResultVO.getPlayerId()));
			timeTrialResultVO.set_link(linkTo.withRel("detail"));
			return timeTrialResultVO;
		}).collect(Collectors.toList());
		return detail;
	}
}
