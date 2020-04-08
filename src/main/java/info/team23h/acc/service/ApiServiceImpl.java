package info.team23h.acc.service;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.util.StringUtil;
import info.team23h.acc.vo.PlayerVO;
import info.team23h.acc.vo.RecordVO;
import info.team23h.acc.vo.TrackVO;
import info.team23h.acc.vo.WeekVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ApiServiceImpl  implements ApiService {

	private static final Log log = LogFactory.getLog(ApiServiceImpl.class);

	@Autowired
	PlayerService playerService;

	@Autowired
	WeekService weekService;

	@Autowired
	TrackService trackService;

	@Autowired
	RecordService recordService;

	@Value("${team23h.apiKey.value}")
	String apiKey;

	/**
	 * api 서비스 레코드 저장
	 *
	 * @param param
	 * @return
	 */

	@Override
	public HashMap<String, Object> setRecord(HashMap<String, Object> param) throws Team23hException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(apiKey.equals(param.get("keyCode"))){
			if(param.get("sessionId") == null){
				throw new Team23hException("sessionId 값을 확인하세요");
			}else if(param.get("trackName") == null){
				throw new Team23hException("trackName 확인해주세요!");
			}else if(param.get("sessionFlag") == null){
				throw new Team23hException("sessionFlag 확인해주세요!");
			}else if(param.get("recordList") == null){
				throw new Team23hException("recordList 확인해주세요!");
			}

			TrackVO trackVO = new TrackVO();
			trackVO.setTrackName(String.valueOf(param.get("trackName")));
			// 트랙 번호 조회
			// 트랙 번호 없으면 입력
			trackService.getSeqForTrackName(trackVO);

			// 세션 flag 확인
			// 세션 flag 에 따라서 DB 정리
			// 세션 flag 가 0 이라면 그냥 패스
			if(String.valueOf(param.get("sessionFlag")).equals("1")){
				// 세션 변경 있음
				// 마지막 세션 정보 조회
				weekService.setSessionId(trackVO, Integer.parseInt(String.valueOf(param.get("sessionId"))));
			}
			// 리스트 돔
			ArrayList<HashMap<String, Object>> recordList = (ArrayList<HashMap<String, Object>>) param.get("recordList");

			for(HashMap<String, Object> recordData : recordList){
				if(recordData.get("PlayerId") == null || "".equals(recordData.get("PlayerId"))){
					throw new Team23hException("PlayerId 확인 해주세요.");
				}else if(recordData.get("carModel") == null || "".equals(recordData.get("carModel"))){
					throw new Team23hException("carModel 확인 해주세요");
				}else if(recordData.get("bestLap") == null || "".equals(recordData.get("bestLap"))){
					throw new Team23hException("bestLap 확인 해주세요");
				}else if(recordData.get("sector1") == null || "".equals(recordData.get("sector1"))){
					throw new Team23hException("sector1 확인 해주세요");
				}else if(recordData.get("sector2") == null || "".equals(recordData.get("sector2"))){
					throw new Team23hException("sector2 확인 해주세요");
				}else if(recordData.get("sector3") == null || "".equals(recordData.get("sector3"))){
					throw new Team23hException("sector3 확인 해주세요");
				}
				/*else if(recordData.get("lapCount") == null || "".equals(recordData.get("lapCount"))){
					throw new Team23hException("lapCount 확인 해주세요");
				}*/

				// 회원 저장 필요
				PlayerVO playerVO = new PlayerVO();
				playerVO.setFirstName(String.valueOf(recordData.get("firstName")));
				playerVO.setLastName(String.valueOf(recordData.get("lastName")));
				playerVO.setPlayerId(String.valueOf(recordData.get("PlayerId")));
				//플레이어 있는지 확인
				int playerCnt = playerService.getPlayerDetail(playerVO);
				if(playerCnt > 0){
					// 있으면 업데이트
					playerService.updateDriver(playerVO);
				}else{
					// 없으면 인서트
					playerService.createDriver(playerVO);
				}

				RecordVO recordVO = new RecordVO();
				recordVO.setSessionId(String.valueOf(param.get("sessionId")));
				recordVO.setTrackSeq(trackVO.getSeq());
				recordVO.setPlayerId(String.valueOf(recordData.get("PlayerId")));
				recordVO.setCarModel(String.valueOf(recordData.get("carModel")));
				recordVO.setBestLap(Integer.parseInt(String.valueOf(recordData.get("bestLap"))));
				recordVO.setSector1(Integer.parseInt(String.valueOf(recordData.get("sector1"))));
				recordVO.setSector2(Integer.parseInt(String.valueOf(recordData.get("sector2"))));
				recordVO.setSector3(Integer.parseInt(String.valueOf(recordData.get("sector3"))));
				recordVO.setLapCount(Integer.parseInt(StringUtil.nvl(StringUtil.nvl(recordData.get("lapCount")),"0")));
				// 레코드 입력
				recordService.setRecordData(recordVO);
			}
			result.put("status", "200");
			result.put("message", "기록 저장이 잘 되었습니다.");
			return result;
		}else{
			throw new Team23hException("keyCode 확인해주세요!");
		}
	}

	/**
	 * 회원정보 조회
	 *
	 * @return
	 */
	@Override
	public HashMap<String, Object> playerList() {
		List<PlayerVO> driverList = playerService.getPlayerList();
		WeekVO weekvo = weekService.getRecently();
		String trackName = "";
		int sessionId = 0;
		if(weekvo != null){
			// 시퀀스로 트랙명 조회
			TrackVO param = new TrackVO();
			param.setSeq(weekvo.getTrackSeq());
			TrackVO trackVO = trackService.getTrackNameForSeq(param);
			trackName  = trackVO.getTrackName();
			sessionId = weekvo.getSessionId();
		}
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("sessionID", sessionId);
		result.put("trackName", trackName);
		result.put("PlayerList", driverList);
		return result;
	}

	@Override
	public HashMap<String, Object> setTestRecord(HashMap<String, Object> param) throws Team23hException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(apiKey.equals(param.get("keyCode"))){
			if(param.get("sessionId") == null){
				throw new Team23hException("sessionId 값을 확인하세요");
			}else if(param.get("trackName") == null){
				throw new Team23hException("trackName 확인해주세요!");
			}else if(param.get("sessionFlag") == null){
				throw new Team23hException("sessionFlag 확인해주세요!");
			}else if(param.get("recordList") == null){
				throw new Team23hException("recordList 확인해주세요!");
			}

			TrackVO trackVO = new TrackVO();
			trackVO.setTrackName(String.valueOf(param.get("trackName")));
			// 트랙 번호 조회
			// 트랙 번호 없으면 입력
			trackService.getSeqForTrackName(trackVO);

			// 세션 flag 확인
			// 세션 flag 에 따라서 DB 정리
			// 세션 flag 가 0 이라면 그냥 패스
			if(String.valueOf(param.get("sessionFlag")).equals("1")){
				// 세션 변경 있음
				// 마지막 세션 정보 조회
				weekService.setSessionId(trackVO, Integer.parseInt(String.valueOf(param.get("sessionId"))));
			}
			// 리스트 돔
			ArrayList<HashMap<String, Object>> recordList = (ArrayList<HashMap<String, Object>>) param.get("recordList");

			for(HashMap<String, Object> recordData : recordList){
				if(recordData.get("PlayerId") == null || "".equals(recordData.get("PlayerId"))){
					throw new Team23hException("PlayerId 확인 해주세요.");
				}else if(recordData.get("carModel") == null || "".equals(recordData.get("carModel"))){
					throw new Team23hException("carModel 확인 해주세요");
				}else if(recordData.get("bestLap") == null || "".equals(recordData.get("bestLap"))){
					throw new Team23hException("bestLap 확인 해주세요");
				}else if(recordData.get("sector1") == null || "".equals(recordData.get("sector1"))){
					throw new Team23hException("sector1 확인 해주세요");
				}else if(recordData.get("sector2") == null || "".equals(recordData.get("sector2"))){
					throw new Team23hException("sector2 확인 해주세요");
				}else if(recordData.get("sector3") == null || "".equals(recordData.get("sector3"))){
					throw new Team23hException("sector3 확인 해주세요");
				}else if(recordData.get("lapCount") == null || "".equals(recordData.get("lapCount"))){
					throw new Team23hException("lapCount 확인 해주세요");
				}
			}
			result.put("status", "200");
			result.put("message", "Validation 체크 완료");
			return result;
		}else{
			throw new Team23hException("keyCode 확인해주세요!");
		}
	}

}
