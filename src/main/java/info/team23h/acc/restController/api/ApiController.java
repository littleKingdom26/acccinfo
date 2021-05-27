package info.team23h.acc.restController.api;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.service.api.ApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ApiController {

	@Autowired
	ApiService apiService;

	/**
	 * 드라이버 목록을 가져 나감
	 *
	 * @return
	 */
	@ApiOperation(value="플레이어 정보")
	@GetMapping("/api/playerList")
	public HashMap<String, Object> playerList() {
		return apiService.playerList();
	}


	/**
	 * 기록 정보를 저장 한다.
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/api/setRecord")
	@Transactional(rollbackFor = Team23hException.class)
	public HashMap<String, Object> setRecord(@RequestBody HashMap<String, Object> param) throws Team23hException {
		// TODO [YTH] 테스트입니다.
		return apiService.setRecord(param);
	}


	/**
	 * 드라이버 목록을 가져 나감
	 *
	 * @return
	 */
	@GetMapping("/api/test/playerList")
	public HashMap<String, Object> playertestList() {
		return apiService.playerList();
	}


	/**
	 * 기록 정보를 저장 한다.
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/api/test/setRecord")
	@Transactional(rollbackFor = Team23hException.class)
	public HashMap<String, Object> setTestRecord(@RequestBody HashMap<String, Object> param) throws Team23hException {
		return apiService.setTestRecord(param);
	}


}
