package info.team23h.acc.service.event;

import info.team23h.acc.vo.event.EventAddPenaltySaveVO;
import info.team23h.acc.vo.event.EventAdminResultVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
class EventServiceImplTest {

	@Autowired
	EventService eventService;

	@Test
	public void 이벤트_라운드_조회(){

		eventService.findEventResultWithRound(36L, 1L);
	}

	@Test
	public void 이벤트_상세_조회(){
		eventService.findByEventPlayerDetail(34L, 6L, "1015");
	}

	@Test
	public void 이벤트_전체조회(){
		eventService.findEventResult(36L);
	}

	@Test
	public void 이벤트_년도_시즌_전체조회(){
		eventService.getEventSeasonAll(2021L,"DIVISION_1");
	}


	@Test
	public void 패널치_추가(){

		final EventAddPenaltySaveVO reason = EventAddPenaltySaveVO.builder()
		                                                         .eventInfoSeq(2L)
		                                                         .round(1L)
		                                                         .playerId("S76561198004892038")
		                                                         .addPenalty(120L)
		                                                         .reason("겁나 방해했음")
		                                                         .build();
//		final EventAdminResultVO eventAdminResultVO = eventService.addPenalty(reason.getEventInfoSeq(), reason.getRound(), reason.getPlayerId(), reason.getAddPenalty(), reason.getReason());
		EventAdminResultVO eventAdminResultVO = new EventAdminResultVO();
		eventAdminResultVO.setEventInfoSeq(reason.getEventInfoSeq());
		eventAdminResultVO.setRound(reason.getRound());

		eventService.eventRankReSetting(eventAdminResultVO);
	}

}