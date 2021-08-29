package info.team23h.acc.restController.admin;

import info.team23h.acc.service.player.PlayerService;
import info.team23h.acc.util.HttpUtil;
import info.team23h.acc.vo.common.HttpRequest;
import info.team23h.acc.vo.player.PlayerVO;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/steam/api")
public class AdminSteamApiRestController {

	@Value("${steam.api.url}")
	String steamUrl;

	@Value("${file.upload.rootpath}")
	String rootPath;

	final String KEY = "D45DB973CD8A5A596A8BA2F9CD91022C";

	@Autowired
	private PlayerService playerService;

	@GetMapping("/getPlayer")
	public HashMap<String, Object> player() throws IOException, ParseException {

		List<PlayerVO> driverList = playerService.getPlayerList();

		int i=0;
		String driverSteamId ="";
		for(PlayerVO playerVO : driverList){
			if((i%100) == 0){
				if(!"".equals(driverSteamId)){
					steamAvatarUpdate(driverSteamId.substring(0, driverSteamId.length() - 1));
				}
				driverSteamId = "";
			}
			String tempSteamId = playerVO.getPlayerId();
			driverSteamId += tempSteamId.substring(1) + ",";
			i++;
		}
		driverSteamId = driverSteamId.substring(0, driverSteamId.length() - 1);
		if(!"".equals(driverSteamId)){
			steamAvatarUpdate(driverSteamId.substring(0, driverSteamId.length() - 1));
		}
		HashMap<String, Object> returnMap = new HashMap<>();
		returnMap.put("data","0000");

		return returnMap;
	}

	public void steamAvatarUpdate(String steamIds) throws IOException, ParseException {
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(steamUrl + "/ISteamUser/GetPlayerSummaries/v2");
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("key", KEY);
		paramMap.put("steamids", steamIds);
		httpRequest.setParam(paramMap);
		HashMap<String, Object> result = HttpUtil.httpsRequest(httpRequest);

		JSONObject response = (JSONObject) result.get("response");
		JSONArray players = (JSONArray) response.get("players");
		for(Object obj : players){
			JSONObject player = (JSONObject) obj;
			PlayerVO playerVO = new PlayerVO();
			playerVO.setSteamAvatar(player.getAsString("avatar"));
			playerVO.setPlayerId("S"+ player.getAsString("steamid"));
			playerService.updateDriver(playerVO);
		}
	}
}
