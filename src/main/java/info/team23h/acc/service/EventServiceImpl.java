package info.team23h.acc.service;

import info.team23h.acc.dao.EventDAO;
import info.team23h.acc.util.MathUtil;
import info.team23h.acc.util.StringUtil;
import info.team23h.acc.vo.*;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EventServiceImpl implements EventService {


	@Autowired
	EventDAO eventDAO;

	@Autowired
	ScoreService scoreService;

	@Autowired
	HandicapService handicapService;

	@Override
	public List<EventInfoVO> getEventInfoList() {
		return eventDAO.getEventInfoList();
	}

	@Override
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
		if(cnt == 0){
			throw new Exception();
		}else{
			result.put("code", "0000");
		}
		return result;
	}

	@Override
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
			// 핸디캡 정보 넣기
			for(HandicapVO handicapVO : handicapList){
				if(handicapVO.getRank() == rank){
					eventVO.setHandicap(handicapVO.getHandicap());
				}
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
			cnt = eventDAO.insertEventMeta(eventMetaVO);
		}

		return 1;
	}


	@Override
	public int setEventBigGrid(EventInfoVO eventInfoVO) throws ParseException {

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

		ScoreInfoVO scoreInfoVO = scoreService.getScoreInfo(scoreInfoParam);

		// 핸디캡 정보
		HandicapInfoVO handicapInfoVO = new HandicapInfoVO();
		handicapInfoVO.setHandicapInfoSeq(eventInfo.getHandicapInfoSeq());
		List<HandicapVO> handicapList = handicapService.getHandicap(handicapInfoVO);

		List<EventVO> playerList = eventDAO.getEventPlayerList(eventInfoVO);
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
			for(EventVO eventVO : playerList){
				if(playerId.equals(eventVO.getPlayerId())){
					carIdList.add(StringUtil.nvl(carInfo.getAsString("carId")));
				}
			}
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
			cnt = eventDAO.insertEventMeta(eventMetaVO);
		}

		return 1;
	}

	@Override
	public List<HashMap<String, Object>> getEventRoundResult(EventInfoVO eventInfoVO) {
		List<HashMap<String, Object>> result = eventDAO.getEventRoundResult(eventInfoVO);
		for(HashMap<String, Object> tempMap : result){
			log.debug("tempMap.toString() > " + tempMap.toString());
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
				temp2.put("value", tempMap.get("RANK_" + (i + 1)));
				roundList.add(temp2);
				HashMap<String, Object> temp1 = new HashMap<>();
				temp1.put("value", tempMap.get("ROUND_" + (i + 1)));
				roundList.add(temp1);
				HashMap<String, Object> temp3 = new HashMap<>();
				temp3.put("value", tempMap.get("HANDICAP_" + (i + 1)));
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


}
