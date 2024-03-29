package info.team23h.acc.service.event;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.dao.EventDAO;
import info.team23h.acc.entity.event.*;
import info.team23h.acc.entity.leagueDiv.LeagueDiv;
import info.team23h.acc.entity.player.Player;
import info.team23h.acc.entity.track.Track;
import info.team23h.acc.repository.event.EventRepository;
import info.team23h.acc.repository.eventInfo.EventInfoRepository;
import info.team23h.acc.repository.eventSub.EventSubRepository;
import info.team23h.acc.repository.leagueDiv.LeagueDivRepository;
import info.team23h.acc.repository.penalty.PenaltyRepository;
import info.team23h.acc.repository.player.PlayerRepository;
import info.team23h.acc.repository.track.TrackRepository;
import info.team23h.acc.restController.front.result.ResultRestController;
import info.team23h.acc.service.handicap.HandicapService;
import info.team23h.acc.service.score.ScoreService;
import info.team23h.acc.util.MathUtil;
import info.team23h.acc.util.StringUtil;
import info.team23h.acc.vo.event.*;
import info.team23h.acc.vo.front.main.BeforeLeagueRankerGroupResultVO;
import info.team23h.acc.vo.front.main.BeforeLeagueRankerResultVO;
import info.team23h.acc.vo.front.result.ResultAllResultVO;
import info.team23h.acc.vo.front.result.ResultReturnVO;
import info.team23h.acc.vo.front.result.ResultSeasonResultVO;
import info.team23h.acc.vo.front.result.ResultSubResultVO;
import info.team23h.acc.vo.handicap.HandicapInfoVO;
import info.team23h.acc.vo.handicap.HandicapVO;
import info.team23h.acc.vo.penalty.PenaltyVO;
import info.team23h.acc.vo.score.ScoreInfoVO;
import info.team23h.acc.vo.score.ScoreVO;
import info.team23h.acc.vo.team.TeamScoreSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {
	final private EventDAO eventDAO;

	final private ScoreService scoreService;

	final private HandicapService handicapService;

	final private EventRepository eventRepository;

	final private EventInfoRepository eventInfoRepository;

	final private EventSubRepository eventSubRepository;

	final private TrackRepository trackRepository;

	final private PlayerRepository playerRepository;

	final private PenaltyRepository penaltyRepository;

	final private LeagueDivRepository leagueDivRepository;

	@Override
	public List<EventInfoVO> getEventInfoList() {
		return eventDAO.getEventInfoList();
	}

	@Override
	@Transactional
	public int insertEventInfo(EventInfoVO eventInfoVO) {
		return eventDAO.insertEventInfo(eventInfoVO);
	}

	@Override
	public EventInfoVO getEventInfo(EventInfoVO eventInfoVO) {
		return eventDAO.getEventInfo(eventInfoVO);
	}


	@Override
	public HashMap<String, Object> delEventInfo(EventInfoVO param) throws Exception {
		int cnt = eventDAO.delEventInfo(param);
		cnt = eventDAO.delEvent(param);
		cnt= eventDAO.delEventMeta(param);
		cnt = eventDAO.delEventSub(param);
		cnt = eventDAO.delPenalty(param);

		HashMap<String, Object> result = new HashMap<>();
		result.put("code", "0000");
		return result;
	}

	@Override
	@Transactional
	public int setEvent(EventInfoVO eventInfoVO) throws ParseException {

		JSONParser parser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
		JSONObject jsonObject = (JSONObject) parser.parse(eventInfoVO.getParserString());

		HashMap<String, Object> map = new HashMap<>(jsonObject);
		EventInfoVO eventInfo = getEventInfo(eventInfoVO);

		EventMetaVO eventMetaVO = new EventMetaVO();
		eventMetaVO.setEventInfoSeq(eventInfoVO.getEventInfoSeq());
		eventMetaVO.setRound(eventInfoVO.getRound());
		eventMetaVO.setTrackName(String.valueOf(map.get("trackName")));

		// 스코어 정보
		ScoreInfoVO scoreInfoParam = new ScoreInfoVO();
		scoreInfoParam.setScoreInfoSeq(eventInfo.getScoreInfoSeq());
		List<ScoreVO> scoreList = scoreService.getScore(scoreInfoParam);

		ScoreInfoVO scoreInfoVO =  scoreService.getScoreInfo(scoreInfoParam);

		// 핸디캡 정보
		HandicapInfoVO handicapInfoVO = new HandicapInfoVO();
		handicapInfoVO.setHandicapInfoSeq(eventInfo.getHandicapInfoSeq());
		List<HandicapVO> handicapList = handicapService.getHandicap(handicapInfoVO);

		//패널티 리스트 만들기
		List<PenaltyVO> penaltyList = new ArrayList<PenaltyVO>();
		JSONArray penaltiesJsonArray = (JSONArray) map.get("penalties");
		for(Object temp : penaltiesJsonArray){
			JSONObject jo = (JSONObject) temp;
			PenaltyVO penaltyVO = new PenaltyVO();
			penaltyVO.setEventInfoSeq(eventInfoVO.getEventInfoSeq());
			penaltyVO.setRound(eventInfoVO.getRound());
			penaltyVO.setPlayerId("");
			penaltyVO.setAddTime(0);
			penaltyVO.setCarId(String.valueOf(jo.get("carId")));
			penaltyVO.setReason(String.valueOf(jo.get("reason")));
			penaltyVO.setPenalty(String.valueOf(jo.get("penalty")));
			penaltyList.add(penaltyVO);
		}

		JSONArray postRacePenaltiesJsonArray = (JSONArray) map.get("post_race_penalties");
		for(Object temp : postRacePenaltiesJsonArray){
			JSONObject jo = (JSONObject) temp;
			PenaltyVO penaltyVO = new PenaltyVO();
			penaltyVO.setEventInfoSeq(eventInfoVO.getEventInfoSeq());
			penaltyVO.setRound(eventInfoVO.getRound());
			penaltyVO.setPlayerId("");
			penaltyVO.setAddTime(Integer.parseInt(String.valueOf(jo.get("penaltyValue"))));
			penaltyVO.setCarId(String.valueOf(jo.get("carId")));
			penaltyVO.setReason(String.valueOf(jo.get("reason")));
			penaltyVO.setPenalty(String.valueOf(jo.get("penalty")));
			penaltyList.add(penaltyVO);
		}

		// 레이스 후 패널티 입력
		JSONObject sessionResult = (JSONObject) map.get("sessionResult");
		JSONArray leaderBoardLinesArray = (JSONArray) sessionResult.get("leaderBoardLines");
		List<EventVO> eventList = new ArrayList<>();
		int rank = 1;
		for(Object temp : leaderBoardLinesArray){
			JSONObject leaderBoardLines = (JSONObject) temp;
			EventVO eventVO = new EventVO();
			// 기본정보
			eventVO.setRank(rank);
			eventVO.setEventInfoSeq(eventInfoVO.getEventInfoSeq());
			eventVO.setRound(eventInfoVO.getRound());
			// 차량 정보
			JSONObject carInfo = (JSONObject) leaderBoardLines.get("car");
			eventVO.setCarId(StringUtil.nvl(carInfo.getAsString("carId")));
			eventVO.setCarModel(carInfo.getAsNumber("carModel").intValue());
			// 드라이버 정보
			JSONArray drivers = (JSONArray) carInfo.get("drivers");
			if(drivers.size() == 1){ //추후 드라이버 2명이면 어찌 할지 고민..필요
				JSONObject firstDriver = (JSONObject) drivers.get(0);
				eventVO.setPlayerId(StringUtil.nvl(firstDriver.get("playerId")));
			}
			// 시간 정보
			JSONObject timing = (JSONObject) leaderBoardLines.get("timing");
			eventVO.setBestLap(timing.getAsNumber("bestLap").intValue());
			eventVO.setRaceTime(timing.getAsNumber("totalTime").intValue());
			eventVO.setTotalTime(eventVO.getRaceTime());
			eventVO.setTotalLap(timing.getAsNumber("lapCount").intValue());

			JSONArray bestSplits = (JSONArray) timing.get("bestSplits");
			// 섹터 타임
			for(int i = 0; i < bestSplits.size(); i++){
				Object splits = bestSplits.get(i);
				switch(i){
					case 0: eventVO.setSector1(StringUtil.nvlToInt(splits));
						break;
					case 1:
						eventVO.setSector2(StringUtil.nvlToInt(splits));
						break;
					case 2:
						eventVO.setSector3(StringUtil.nvlToInt(splits));
						break;
				}
			}

			// 토탈 타임
			for(PenaltyVO penaltyVO : penaltyList){
				if(penaltyVO.getCarId().equals(eventVO.getCarId())){
					penaltyVO.setPlayerId(eventVO.getPlayerId());

					if(penaltyVO.getAddTime() > 0){
						eventVO.setPenalty(penaltyVO.getAddTime());
						eventVO.setTotalTime(eventVO.getTotalTime() + (penaltyVO.getAddTime()*1000));
					}
				}
			}
			if("Y".equals(scoreInfoVO.getParticipationYn())){
				eventVO.setScore(1);
			}
			// 스코어 점수 뽑기
			for(ScoreVO scoreVO : scoreList){
				if(scoreVO.getRank() == rank){
					eventVO.setScore(scoreVO.getScore());
				}
			}
			boolean handiCheck = true;
			int appencRank = 0;
			// 핸디캡 정보 넣기
			for(HandicapVO handicapVO : handicapList){
				if(handicapVO.getRank() == rank){
					handiCheck = false;
					eventVO.setHandicap(handicapVO.getHandicap());
				}
			}

			// 핸드캡이 안들어갔다면 핸디캡 마지막껄로..
			if(handiCheck){
				eventVO.setHandicap(handicapList.get(handicapList.size() - 1).getHandicap());
			}

			// 의무 피트스탑 안한 횟수
			eventVO.setMissMandatoryPitStop(leaderBoardLines.getAsNumber("missingMandatoryPitstop").intValue());
			if(eventVO.getTotalTime() < 2147483647){ // 토탈 타임이 2147483647보다 적은 사람
				rank++;
				eventList.add(eventVO);
			}

		}

		// 랩 정보 넣기(TB_EVENT_SUB)
		JSONArray laps = (JSONArray) map.get("laps");
		ArrayList<EventSubVO> eventSubList = new ArrayList<>();
		ArrayList<HashMap<String, Integer>> playerLap = new ArrayList<>();
		for(Object temp : laps){
			JSONObject lap = (JSONObject)temp;
			boolean check = false;
			int lapCnt = 0;
			for(HashMap<String, Integer> playerLapMap : playerLap){
				for(Map.Entry<String, Integer> elem : playerLapMap.entrySet()){
					if(lap.getAsString("carId").equals(elem.getKey())){
						lapCnt = elem.getValue() +1;
						playerLapMap.put(elem.getKey(),lapCnt);
						check = true;
					}
				}
			}

			if(!check){
				lapCnt = 1;
				HashMap<String, Integer> playerLapMap = new HashMap<>();
				playerLapMap.put(lap.getAsString("carId"), lapCnt);
				playerLap.add(playerLapMap);
			}

			EventSubVO eventSubVO = new EventSubVO();

			eventSubVO.setEventInfoSeq(eventInfoVO.getEventInfoSeq());
			eventSubVO.setRound(eventInfoVO.getRound());
			eventSubVO.setCarId(lap.getAsString("carId"));
			eventSubVO.setLapTime(lap.getAsString("laptime"));
			eventSubVO.setLap(lapCnt);
			JSONArray splits = (JSONArray) lap.get("splits");
			for(int i = 0; i < splits.size(); i++){
				Object split = splits.get(i);
				switch(i){
					case 0:
						eventSubVO.setSector1(StringUtil.nvl(split));
						break;
					case 1:
						eventSubVO.setSector2(StringUtil.nvl(split));
						break;
					case 2:
						eventSubVO.setSector3(StringUtil.nvl(split));
						break;
				}
			}
			eventSubList.add(eventSubVO);
		}

		for(PenaltyVO penaltyVO : penaltyList){
			eventDAO.insertPenalty(penaltyVO);
		}

		for(EventVO eventVO : eventList){
			eventDAO.insertEvent(eventVO);
		}

		for(EventSubVO eventSubVO : eventSubList){
			eventDAO.insertEventSub(eventSubVO);
		}

		int cnt = 0;
		if(eventDAO.selectEventMeta(eventMetaVO) == 0){
			final Track track = trackRepository.findByTrackName(eventMetaVO.getTrackName()).get();
			eventMetaVO.setTrackSeq(track.getSeq());
			cnt = eventDAO.insertEventMeta(eventMetaVO);
		}

		return 1;
	}


	@Override
	@Transactional
	public int setEventBigGrid(EventInfoVO eventInfoVO) throws ParseException {

		JSONParser parser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
		JSONObject jsonObject = (JSONObject) parser.parse(eventInfoVO.getParserString());

		HashMap<String, Object> map = new HashMap<>(jsonObject);
		// 이벤트 정보 조회(등급)
		EventInfoVO eventInfo = getEventInfo(eventInfoVO);

		EventMetaVO eventMetaVO = new EventMetaVO();
		eventMetaVO.setEventInfoSeq(eventInfoVO.getEventInfoSeq());
		eventMetaVO.setRound(eventInfoVO.getRound());
		eventMetaVO.setTrackName(String.valueOf(map.get("trackName")));

		// 스코어 정보
		ScoreInfoVO scoreInfoParam = new ScoreInfoVO();
		scoreInfoParam.setScoreInfoSeq(eventInfo.getScoreInfoSeq());
		List<ScoreVO> scoreList = scoreService.getScore(scoreInfoParam);

		ScoreInfoVO scoreInfoVO = scoreService.getScoreInfo(scoreInfoParam);

		// 핸디캡 정보
		HandicapInfoVO handicapInfoVO = new HandicapInfoVO();
		handicapInfoVO.setHandicapInfoSeq(eventInfo.getHandicapInfoSeq());
		List<HandicapVO> handicapList = handicapService.getHandicap(handicapInfoVO);

//		List<EventVO> playerList = eventDAO.getEventPlayerList(eventInfoVO);
		List<String> carIdList = new ArrayList<>();

		JSONObject sessionResult = (JSONObject) map.get("sessionResult");
		JSONArray leaderBoardLinesArray = (JSONArray) sessionResult.get("leaderBoardLines");
		for(Object temp : leaderBoardLinesArray){
			JSONObject leaderBoardLines = (JSONObject) temp;
			JSONObject carInfo = (JSONObject) leaderBoardLines.get("car");
			JSONArray drivers = (JSONArray) carInfo.get("drivers");
			String playerId = "";
			if(drivers.size() == 1){ //추후 드라이버 2명이면 어찌 할지 고민..필요
				JSONObject firstDriver = (JSONObject) drivers.get(0);
				playerId = StringUtil.nvl(firstDriver.get("playerId"));
			}

			// 리그 등급 조회
			final LeagueDiv leagueDiv = leagueDivRepository.findByPlayerIdEquals(playerId).orElse(null);

			if(! ObjectUtils.isEmpty(leagueDiv)) {
				if(leagueDiv.getLeagueDiv().equals(eventInfoVO.getDivision())) {
					carIdList.add(StringUtil.nvl(carInfo.getAsString("carId")));
				}
			}
			/*for(EventVO eventVO : playerList){
				if(playerId.equals(eventVO.getPlayerId())){
					carIdList.add(StringUtil.nvl(carInfo.getAsString("carId")));
				}
			}*/
		}

		List<PenaltyVO> penaltyList = new ArrayList<PenaltyVO>();
		JSONArray penaltiesJsonArray = (JSONArray) map.get("penalties");
		for(Object temp : penaltiesJsonArray){
			JSONObject jo = (JSONObject) temp;
			for(String s : carIdList){
				if(s.equals(String.valueOf(jo.get("carId")))){
					PenaltyVO penaltyVO = new PenaltyVO();
					penaltyVO.setEventInfoSeq(eventInfoVO.getEventInfoSeq());
					penaltyVO.setRound(eventInfoVO.getRound());
					penaltyVO.setPlayerId("");
					penaltyVO.setAddTime(0);
					penaltyVO.setCarId(String.valueOf(jo.get("carId")));
					penaltyVO.setReason(String.valueOf(jo.get("reason")));
					penaltyVO.setPenalty(String.valueOf(jo.get("penalty")));
					penaltyList.add(penaltyVO);
				}
			}
		}

		JSONArray postRacePenaltiesJsonArray = (JSONArray) map.get("post_race_penalties");
		for(Object temp : postRacePenaltiesJsonArray){
			JSONObject jo = (JSONObject) temp;
			for(String s : carIdList){
				if(s.equals(String.valueOf(jo.get("carId")))){
					log.debug("jo.get(reason) > " + jo.get("reason"));
					PenaltyVO penaltyVO = new PenaltyVO();
					penaltyVO.setEventInfoSeq(eventInfoVO.getEventInfoSeq());
					penaltyVO.setRound(eventInfoVO.getRound());
					penaltyVO.setPlayerId("");
					penaltyVO.setAddTime(Integer.parseInt(String.valueOf(jo.get("penaltyValue"))));
					penaltyVO.setCarId(String.valueOf(jo.get("carId")));
					penaltyVO.setReason(String.valueOf(jo.get("reason")));
					penaltyVO.setPenalty(String.valueOf(jo.get("penalty")));
					penaltyList.add(penaltyVO);
				}
			}
		}

		// 레이스 후 패널티 입력
		List<EventVO> eventList = new ArrayList<>();
		int rank = 1;
		for(Object temp : leaderBoardLinesArray){
			JSONObject leaderBoardLines = (JSONObject) temp;
			JSONObject carInfo = (JSONObject) leaderBoardLines.get("car");
			for(String s : carIdList){
				if(s.equals(StringUtil.nvl(carInfo.getAsString("carId")))){
					EventVO eventVO = new EventVO();
					// 기본정보
					eventVO.setRank(rank);
					eventVO.setEventInfoSeq(eventInfoVO.getEventInfoSeq());
					eventVO.setRound(eventInfoVO.getRound());
					// 차량 정보
					eventVO.setCarId(StringUtil.nvl(carInfo.getAsString("carId")));
					eventVO.setCarModel(carInfo.getAsNumber("carModel").intValue());
					// 드라이버 정보
					JSONArray drivers = (JSONArray) carInfo.get("drivers");
					if(drivers.size() == 1){ //추후 드라이버 2명이면 어찌 할지 고민..필요
						JSONObject firstDriver = (JSONObject) drivers.get(0);
						eventVO.setPlayerId(StringUtil.nvl(firstDriver.get("playerId")));
					}
					// 시간 정보
					JSONObject timing = (JSONObject) leaderBoardLines.get("timing");
					eventVO.setBestLap(timing.getAsNumber("bestLap").intValue());
					eventVO.setRaceTime(timing.getAsNumber("totalTime").intValue());
					eventVO.setTotalTime(eventVO.getRaceTime());
					eventVO.setTotalLap(timing.getAsNumber("lapCount").intValue());

					JSONArray bestSplits = (JSONArray) timing.get("bestSplits");
					// 섹터 타임
					for(int i = 0; i < bestSplits.size(); i++){
						Object splits = bestSplits.get(i);
						switch(i){
							case 0:
								eventVO.setSector1(StringUtil.nvlToInt(splits));
								break;
							case 1:
								eventVO.setSector2(StringUtil.nvlToInt(splits));
								break;
							case 2:
								eventVO.setSector3(StringUtil.nvlToInt(splits));
								break;
						}
					}

					// 토탈 타임
					for(PenaltyVO penaltyVO : penaltyList){
						if(penaltyVO.getCarId().equals(eventVO.getCarId())){
							penaltyVO.setPlayerId(eventVO.getPlayerId());

							if(penaltyVO.getAddTime() > 0){
								eventVO.setPenalty(penaltyVO.getAddTime());
								eventVO.setTotalTime(eventVO.getTotalTime() + (penaltyVO.getAddTime() * 1000));
							}
						}
					}
					if("Y".equals(scoreInfoVO.getParticipationYn())){
						eventVO.setScore(1);
					}
					// 스코어 점수 뽑기
					for(ScoreVO scoreVO : scoreList){
						if(scoreVO.getRank() == rank){
							eventVO.setScore(scoreVO.getScore());
						}
					}
					// 핸디캡 정보 넣기
					for(HandicapVO handicapVO : handicapList){
						if(handicapVO.getRank() == rank){
							eventVO.setHandicap(handicapVO.getHandicap());
						}
					}
					// 의무 피트스탑 안한 횟수
					eventVO.setMissMandatoryPitStop(leaderBoardLines.getAsNumber("missingMandatoryPitstop").intValue());
					if(eventVO.getTotalTime() < 2147483647){ // 토탈 타임이 이것보다 적은 사람
						rank++;
						eventList.add(eventVO);
					}
				}
			}
		}

		// 랩 정보 넣기(TB_EVENT_SUB)
		JSONArray laps = (JSONArray) map.get("laps");
		ArrayList<EventSubVO> eventSubList = new ArrayList<>();
		ArrayList<HashMap<String, Integer>> playerLap = new ArrayList<>();
		for(Object temp : laps){
			JSONObject lap = (JSONObject) temp;
			for(String s : carIdList){
				if(s.equals(lap.getAsString("carId"))){
					boolean check = false;
					int lapCnt = 0;
					for(HashMap<String, Integer> playerLapMap : playerLap){
						for(Map.Entry<String, Integer> elem : playerLapMap.entrySet()){
							if(lap.getAsString("carId").equals(elem.getKey())){
								lapCnt = elem.getValue() + 1;
								playerLapMap.put(elem.getKey(), lapCnt);
								check = true;
							}
						}
					}

					if(!check){
						lapCnt = 1;
						HashMap<String, Integer> playerLapMap = new HashMap<>();
						playerLapMap.put(lap.getAsString("carId"), lapCnt);
						playerLap.add(playerLapMap);
					}

					EventSubVO eventSubVO = new EventSubVO();

					eventSubVO.setEventInfoSeq(eventInfoVO.getEventInfoSeq());
					eventSubVO.setRound(eventInfoVO.getRound());
					eventSubVO.setCarId(lap.getAsString("carId"));
					eventSubVO.setLapTime(lap.getAsString("laptime"));
					eventSubVO.setLap(lapCnt);
					JSONArray splits = (JSONArray) lap.get("splits");
					for(int i = 0; i < splits.size(); i++){
						Object split = splits.get(i);
						switch(i){
							case 0:
								eventSubVO.setSector1(StringUtil.nvl(split));
								break;
							case 1:
								eventSubVO.setSector2(StringUtil.nvl(split));
								break;
							case 2:
								eventSubVO.setSector3(StringUtil.nvl(split));
								break;
						}
					}
					eventSubList.add(eventSubVO);
				}
			}
		}

		for(PenaltyVO penaltyVO : penaltyList){
			eventDAO.insertPenalty(penaltyVO);
		}

		for(EventVO eventVO : eventList){
			eventDAO.insertEvent(eventVO);
		}

		for(EventSubVO eventSubVO : eventSubList){
			eventDAO.insertEventSub(eventSubVO);
		}

		int cnt=0;
		if(eventDAO.selectEventMeta(eventMetaVO) == 0){
			final Track track = trackRepository.findByTrackName(eventMetaVO.getTrackName()).get();
			eventMetaVO.setTrackSeq(track.getSeq());
			cnt = eventDAO.insertEventMeta(eventMetaVO);
		}

		return 1;
	}

	@Override
	public List<HashMap<String, Object>> getEventRoundResult(EventInfoVO eventInfoVO) {
		List<HashMap<String, Object>> result = eventDAO.getEventRoundResult(eventInfoVO);
		for(HashMap<String, Object> tempMap : result){
			tempMap.put("RACE_TIME", MathUtil.secToMin(tempMap.get("RACE_TIME")));
			tempMap.put("TOTAL_TIME", MathUtil.secToMin(tempMap.get("TOTAL_TIME")));
			tempMap.put("BEST_LAP", MathUtil.secToMin(tempMap.get("BEST_LAP")));
		}
		return result;
	}

	@Override
	public EventMetaVO getEventMeta(EventInfoVO eventInfoVO) {
		return eventDAO.getEventMeta(eventInfoVO);
	}

	@Override
	public List<HashMap<String, Object>> getEventAllResult(EventInfoVO eventInfoVO) {
		List<HashMap<String, Object>> result = eventDAO.getEventAllResult(eventInfoVO);
		int finalRank = 0;
		int tempScore = 0;
		int tempRank = 0;
		for(HashMap<String, Object> tempMap : result){
			List<HashMap<String,Object>> roundList = new ArrayList<>();
			for(int i = 0; i < eventInfoVO.getRounds().size(); i++){
				HashMap<String, Object> temp2 = new HashMap<>();
				temp2.put("value", "<span>" +tempMap.get("RANK_" + (i + 1)) + "</span>");
				roundList.add(temp2);
				HashMap<String, Object> temp1 = new HashMap<>();
				temp1.put("value", "<span style='color:blue;'>" +tempMap.get("ROUND_" + (i + 1)) + "</span>");
				roundList.add(temp1);
				HashMap<String, Object> temp3 = new HashMap<>();
				temp3.put("value", "<span style='color:red;'>" +tempMap.get("HANDICAP_" + (i + 1)) + "</span>");
				roundList.add(temp3);
				/*roundList.add(tempMap.get("ROUND_" + (i + 1)));
				roundList.add(tempMap.get("RANK_" + (i + 1)));
				roundList.add(tempMap.get("HANDICAP_" + (i + 1)));*/
			}
			tempMap.put("roundList",roundList);
			int checkScore = StringUtil.nvlToInt(tempMap.get("SCORE"),0);
			tempRank++;
			if(tempScore != checkScore){
				tempScore = checkScore;
				finalRank = tempRank;
			}else{

			}
			tempMap.put("finalRank",finalRank);
		}
		return result;
	}

	@Override
	public List<EventMetaVO> getEventMetaList(EventInfoVO eventInfoVO) {
		return eventDAO.getEventMetaList(eventInfoVO);
	}


	@Override
	public HashMap<String, Object> delEvent(EventInfoVO eventInfoVO) {
		HashMap<String,Object> result = new HashMap<>();
		result.put("code","0000");

		int cnt = eventDAO.delEvent(eventInfoVO);
		cnt = eventDAO.delEventSub(eventInfoVO);
		cnt = eventDAO.delEventMeta(eventInfoVO);
		cnt = eventDAO.delPenalty(eventInfoVO);

		return result;
	}

	@Override
	public List<PenaltyVO> getEventPenalty(EventInfoVO eventInfoVO) {
		return eventDAO.getEventPenalty(eventInfoVO);
	}

	@Override
	public List<EventSubVO> getEventSubList(EventSubVO eventSubVO) {
		List<EventSubVO> result = eventDAO.getEventSubList(eventSubVO);
		for(EventSubVO subVO : result){
			subVO.setLapTime(MathUtil.secToMin(subVO.getLapTime(),true));
			subVO.setSector1(MathUtil.secToMin(subVO.getSector1(),true));
			subVO.setSector2(MathUtil.secToMin(subVO.getSector2(),true));
			subVO.setSector3(MathUtil.secToMin(subVO.getSector3(),true));
		}
		return result;
	}

	@Override
	public List<HashMap<String, Object>> getEventYearResult(EventInfoVO eventInfoVO) {
		return eventDAO.getEventYearResult(eventInfoVO);
	}

	@Override
	public List<EventResultVO> findByEventList(TeamScoreSaveVO teamScoreSaveVO) {
		List<EventResultVO> resultList = eventRepository.findByEventList(teamScoreSaveVO);
		return resultList;
	}

	@Override
	public List<BeforeLeagueRankerGroupResultVO> getBeforeLeagueRanker() {

		List<BeforeLeagueRankerGroupResultVO> resultList = new ArrayList<>();
		final List<EventInfo> eventAllList = eventInfoRepository.findAll(Sort.by(Sort.Direction.DESC, "eventInfoSeq"));
		// Gt3 최신 리그 조회
		eventAllList.stream().filter(eventInfo -> eventInfo.getDivision().equals(EnumCode.LeagueDivision.DIVISION_4.name())).findFirst().ifPresent(eventInfo -> {
			final List<EventResultVO> ranker = eventRepository.findByEventRanker(eventInfo.getEventInfoSeq());
			if(ranker.size()>3){
				final List<EventResultVO> eventResultVOS = ranker.subList(0, 3);
				int rank = 0;
				List<BeforeLeagueRankerResultVO> subResultList = new ArrayList<>();
				for(EventResultVO eventResultVO : eventResultVOS) {
					rank += 1;
					subResultList.add(BeforeLeagueRankerResultVO.builder()
																.rank(rank)
																.firstName(eventResultVO.getFirstName())
																.lastName(eventResultVO.getLastName())
																.steamAvatar(eventResultVO.getSteamAvatar())
																.build());

				}
				resultList.add(BeforeLeagueRankerGroupResultVO.builder()
															  .leagueName(eventInfo.getTitle())
															  .beforeLeagueRankerResultList(subResultList)
															  .division(EnumCode.LeagueDivision.valueOf(eventInfo.getDivision())
																							   .getValue())
															  .build());
			}

		});

		// GT4 최고 위에 결과 가지고 오기
		eventAllList.stream().filter(eventInfo -> eventInfo.getDivision().equals(EnumCode.LeagueDivision.DIVISION_3.name())).findFirst().ifPresent(eventInfo -> {
			final List<EventResultVO> ranker = eventRepository.findByEventRanker(eventInfo.getEventInfoSeq());
			final List<EventResultVO> eventResultVOS = ranker.subList(0, 3);
			int rank = 0;
			List<BeforeLeagueRankerResultVO> subResultList = new ArrayList<>();
			for(EventResultVO eventResultVO : eventResultVOS) {
				rank += 1;
				subResultList.add(BeforeLeagueRankerResultVO.builder()
				                                            .rank(rank)
				                                            .firstName(eventResultVO.getFirstName())
				                                            .lastName(eventResultVO.getLastName())
				                                            .steamAvatar(eventResultVO.getSteamAvatar())
				                                            .build());
			}
			resultList.add(BeforeLeagueRankerGroupResultVO.builder()
			                                              .leagueName(eventInfo.getTitle())
			                                              .beforeLeagueRankerResultList(subResultList)
			                                              .division(EnumCode.LeagueDivision.valueOf(eventInfo.getDivision())
			                                                                               .getValue())
			                                              .build());

		});

		return resultList;
	}

	@Override
	public List<Long> findYearGroup() {
		final List<EventInfo> eventInfoList = eventInfoRepository.findAll(Sort.by("regDt").ascending());
		final List<Long> yearList = eventInfoList.stream().map(eventInfo -> Long.parseLong(eventInfo.getRegDt().format(DateTimeFormatter.ofPattern("YYYY")))).distinct().collect(Collectors.toList());
		return yearList;
	}

	@Override
	public List<ResultSeasonResultVO> getEventSeason(Long year, String division) {

		final LocalDateTime startDt = LocalDateTime.of(year.intValue(),1,1,0,0,0);
		final LocalDateTime endDt = LocalDateTime.of(year.intValue(),12,31,23,59,59);
		final List<EventInfo> resultList = eventInfoRepository.findAllByAndDivisionAndRegDtBetweenOrderByRegDtDesc(division, startDt, endDt);
		return resultList.stream().map(ResultSeasonResultVO::new).collect(Collectors.toList());

	}

	@Override
	public List<ResultReturnVO> findEventResultWithRound(Long eventInfoSeq, Long round) {
		List<Long> eventInfoSeqList = new ArrayList<>();
		eventInfoSeqList.add(eventInfoSeq);

		final List<Event> resultWithRoundList = eventRepository.findAllByEventInfoSeqAndRoundOrderByRankAsc(eventInfoSeq, round);
		final List<ResultReturnVO> returnList = resultWithRoundList.stream().map(ResultReturnVO::new).map(resultReturnVO -> {
			WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(ResultRestController.class).getResultDetail(resultReturnVO.getEventInfoSeq(), resultReturnVO.getRound(), resultReturnVO.getCarId()));
			resultReturnVO.set_link(linkTo.withRel("detail"));
			return resultReturnVO;
			}).collect(Collectors.toList());
		return returnList;
	}

	@Override
	public List<ResultSubResultVO> findByEventPlayerDetail(Long eventInfoSeq, Long round, String carId) {
		final List<EventSub> allByEventInfoSeqAndCarIdAndRound = eventSubRepository.findAllByEventInfoSeqAndCarIdAndRoundOrderByLapAsc(eventInfoSeq, carId, round);
		return allByEventInfoSeqAndCarIdAndRound.stream().map(ResultSubResultVO::new).collect(Collectors.toList());

	}

	@Override
	public List<ResultAllResultVO> findEventResult(Long eventInfoSeq) {
		final List<Event> eventAllList = eventRepository.findAllByEventInfoSeqOrderByRoundAsc(eventInfoSeq);// 라운드 기록 조회
		final List<String> playerId = eventAllList.stream().map(event -> event.getPlayer().getPlayerId()).distinct().collect(Collectors.toList());// player Id 조회 / 중복제거
		List<ResultAllResultVO> resultAllResultList = new ArrayList<>();
		playerId.forEach(s -> {
			final Event eventFirst = eventAllList.stream().filter(event -> event.getPlayer().getPlayerId() == s).findFirst().get(); // 플레이어 id 첫번쨰 값 구함
			resultAllResultList.add(ResultAllResultVO.builder()
													 .playerId(s)
													 .lastName(eventFirst.getPlayer().getLastName())
													 .fistName(eventFirst.getPlayer().getFirstName())
													 .point(eventAllList.stream().filter(event -> event.getPlayer().getPlayerId().equals(s)).mapToLong(event -> event.getScore()).sum())
													 .build());
		});

		final List<ResultAllResultVO> resultList = resultAllResultList.stream().sorted(Comparator.comparingLong(ResultAllResultVO::getPoint).reversed()).collect(Collectors.toList());

		return resultList;


	}

	@Override
	public List<ResultAllResultVO> getEventSeasonAll(Long year, String division) {
		final List<EventInfo> allByDivisionAndYear = eventInfoRepository.findAllByDivisionAndYear(division, year);
		final List<Long> eventSeqList = allByDivisionAndYear.stream().map(EventInfo::getEventInfoSeq).collect(Collectors.toList());
		final List<Event> allByEventInfoSeq = eventRepository.findAllByEventInfoSeqIn(eventSeqList);
		// 플레이어만 검색
		final List<String> playerIdList = allByEventInfoSeq.parallelStream().map(Event::getPlayer).map(Player::getPlayerId).distinct().collect(Collectors.toList());

		List<ResultAllResultVO> resultList = new ArrayList<>();
		playerIdList.forEach(s -> {
			final Map<String, List<Event>> collect = allByEventInfoSeq.stream()
																	  .filter(event -> event.getPlayer().getPlayerId().equals(s))
																	  .collect(Collectors.groupingBy(event -> event.getPlayer().getPlayerId()));

			if(collect.get(s).size() > 0){
				final ResultAllResultVO resultAllResultVO = new ResultAllResultVO(collect.get(s).get(0));
				final Long scoreSum = allByEventInfoSeq.stream().filter(event -> event.getPlayer().getPlayerId().equals(s)).map(Event::getScore).reduce(0L, Long::sum);
				resultAllResultVO.setPoint(scoreSum);
				resultList.add(resultAllResultVO);
			}
		});

		resultList.sort(Comparator.comparingLong(ResultAllResultVO::getPoint).reversed());
		return resultList;
	}

	@Override
	@Transactional
	public EventAdminResultVO addPenalty(Long eventInfoSeq, Long round, String playerId, Long addPenalty, String reason) {
		//
		final Player player = playerRepository.findById(playerId)
		                                      .orElseThrow(() -> new Team23hException("유저정보가 없습니다."));


		final Event event = eventRepository.findByEventInfoSeqAndRoundAndPlayer(eventInfoSeq, round, player)
		                                   .orElseThrow(() -> new Team23hException("이벤트 정보가 없습니다."));
		event.update(addPenalty);

		final TbPenalty postRaceTime = TbPenalty.builder()
		                                        .addTime(addPenalty)
		                                        .penalty("PostRaceTime")
		                                        .reason(reason)
		                                        .carId(event.getCarId())
		                                        .round(round)
		                                        .eventInfoSeq(eventInfoSeq)
		                                        .playerId(playerId)
		                                        .build();

		penaltyRepository.save(postRaceTime);
		// 패널티에 정보 추가
		return new EventAdminResultVO(event);

	}

	@Override
	@Transactional
	public void eventRankReSetting(EventAdminResultVO eventAdminResultVO) {

		EventInfo eventInfo = eventInfoRepository.findById(eventAdminResultVO.getEventInfoSeq())
		                                         .orElseThrow(() -> new Team23hException("리그 정보 없음!"));

		// 리그 라운드 조회
		final List<Event> eventRoundList = eventRepository.findAllByEventInfoSeqAndRound(eventAdminResultVO.getEventInfoSeq(), eventAdminResultVO.getRound());

		Comparator<Event> compare = Comparator.comparing(Event::getTotalLap).reversed()
		                                      .thenComparing(Event::getTotalTime);

		final List<Event> reRank = eventRoundList.stream()
		                                          .sorted(compare)
		                                          .collect(Collectors.toList());

		int i = 1;
		for(Event event : reRank) {
			int finalI1 = i;
			final Long handicap = eventInfo.getHandicapInfo()
			                               .getHandicapSubList()
			                               .stream()
			                               .filter(handicapSub -> handicapSub.getRank()
			                                                                      .equals(Long.valueOf(finalI1)))
			                               .mapToLong(HandicapSub::getHandicap)
			                               .findFirst().orElse(0L);

			int finalI = i;
			Long score = eventInfo.getScoreInfo()
			                            .getScoreSubList()
			                            .stream()
			                            .filter(scoreSub -> scoreSub.getRank()
			                                                    .equals(Long.valueOf(finalI)))
			                            .mapToLong(ScoreSub::getScore)
			                            .findFirst()
			                            .orElse(0L);
			if(score.equals(0L)) {
				if(eventInfo.getScoreInfo().getParticipationYn().equals("Y")) {
					score = 1L;
				}
			}
			event.reRank(Long.valueOf(i), score, handicap);
			i++;
		}
	}

}
