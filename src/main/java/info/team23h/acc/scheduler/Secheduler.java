package info.team23h.acc.scheduler;

import info.team23h.acc.restController.admin.AdminSteamApiRestController;
import info.team23h.acc.service.player.PlayerService;
import info.team23h.acc.vo.player.PlayerVO;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class Secheduler {

	@Autowired
	PlayerService playerService;

	@Autowired
	AdminSteamApiRestController steamApiRestController;

	/**
	 * 정기배송 결제요청
	 * 왼쪽부터 초, 분, 시, 일, 월, 년
	 * [00초], [00분], [오전 6시], [매일], [매월], [매년]
	 */
	//@Scheduled(cron = "0 1 0 * * *")
	@Scheduled(cron = "0 0 6 * * *")
	public void steamProfileImgUpdate() throws IOException, ParseException {
		log.debug("스팀 이미지 스케쥴러 start");
		List<PlayerVO> driverList = playerService.getPlayerList();

		int i = 0;
		String driverSteamId = "";
		for(PlayerVO playerVO : driverList){
			if((i % 100) == 0){
				if(!"".equals(driverSteamId)){
					steamApiRestController.steamAvatarUpdate(driverSteamId.substring(0, driverSteamId.length() - 1));
				}
				driverSteamId = "";
			}
			String tempSteamId = playerVO.getPlayerId();
			driverSteamId += tempSteamId.substring(1) + ",";
			i++;
		}
		driverSteamId = driverSteamId.substring(0, driverSteamId.length() - 1);
		if(!"".equals(driverSteamId)){
			steamApiRestController.steamAvatarUpdate(driverSteamId.substring(0, driverSteamId.length() - 1));
		}
		log.debug("스팀 이미지 스케쥴러 end");
	}
}
