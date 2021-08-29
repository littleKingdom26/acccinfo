package info.team23h.acc.controller.admin;

import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.service.car.CarService;
import info.team23h.acc.service.leagueDiv.LeagueDivService;
import info.team23h.acc.service.player.PlayerService;
import info.team23h.acc.vo.car.CarResultVO;
import info.team23h.acc.vo.leagueDiv.LeagueDivResultVO;
import info.team23h.acc.vo.player.PlayerVO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Setter
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/register")
public class RegisterController {

	final private PlayerService playerService;

	final private CarService carService;

	final private LeagueDivService leagueDivService;


	@GetMapping("/view")
	public String view(Model model){

		// 플레이어 리스트 조회
		final List<PlayerVO> playerList = playerService.getPlayerList();
		final List<LeagueDivResultVO> proList = leagueDivService.findByLeagueDiv(EnumCode.LeagueDivision.DIVISION_1.name());
		final List<LeagueDivResultVO> masterList = leagueDivService.findByLeagueDiv(EnumCode.LeagueDivision.DIVISION_2.name());

		for(LeagueDivResultVO leagueDivResultVO : proList) {
			playerList.removeIf(playerVO -> playerVO.getPlayerId().equals(leagueDivResultVO.getPlayerId()));
		}

		for(LeagueDivResultVO leagueDivResultVO : masterList) {
			playerList.removeIf(playerVO -> playerVO.getPlayerId()
			                                        .equals(leagueDivResultVO.getPlayerId()));
		}
		final List<CarResultVO> allCarList = carService.findAll();

		model.addAttribute("carList",allCarList);
		model.addAttribute("playerList", playerList);
		model.addAttribute("proList", proList);
		model.addAttribute("masterList", masterList);

		return "/admin/register/view";
	}

	@GetMapping("/entryFile/master")
	public String masterEntryFile(Model model){

		final List<LeagueDivResultVO> masterList = leagueDivService.findByLeagueDiv(EnumCode.LeagueDivision.DIVISION_2.name());

		String resultPage =  getEntryList(model, masterList);
		return resultPage;
	}


	@GetMapping("/entryFile/pro")
	public String proEntryFile(Model model) {

		final List<LeagueDivResultVO> proList = leagueDivService.findByLeagueDiv(EnumCode.LeagueDivision.DIVISION_1.name());
		String resultPage = getEntryList(model, proList);
		return resultPage;
	}

	private String getEntryList(Model model, List<LeagueDivResultVO> masterList) {
		StringBuffer entry = baseSet();
		for(LeagueDivResultVO leagueDivResultVO : masterList) {
			driverSet(entry, leagueDivResultVO);
		}

		lastSet(entry);

		final String text = entry.toString()
		                         .replace("},]", "}]");
		model.addAttribute("text", text);
		model.addAttribute("fileName", "entrylist.json");
		return "textFileDownloadView";
	}

	private StringBuffer baseSet(){
		StringBuffer entry = new StringBuffer();
		entry.append("{\"entries\":[");
		return entry;
	}

	private void lastSet(StringBuffer entry){
		entry.append("],\"forceEntryList\":1}");
	}

	private void driverSet(StringBuffer entry , LeagueDivResultVO leagueDivResultVO){
		entry.append("{");
		entry.append("\"drivers\":[{ \"playerID\":\"");
		entry.append(leagueDivResultVO.getPlayerId());
		entry.append("\"}],");
		entry.append("\"raceNumber\":"+leagueDivResultVO.getCarNumber()+",");
		entry.append("\"forcedCarModel\":"+leagueDivResultVO.getCarModel()+",");
		if(leagueDivResultVO.getBallast() > 0) {
			entry.append("\"ballastKg\":" + leagueDivResultVO.getBallast() + ",");
		}
		entry.append("\"isServerAdmin\":0");
		entry.append("},");
	}


}
