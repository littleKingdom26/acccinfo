package info.team23h.acc.controller.admin;

import info.team23h.acc.service.player.PlayerService;
import info.team23h.acc.service.record.RecordService;
import info.team23h.acc.vo.common.SearchVO;
import info.team23h.acc.vo.player.PlayerSearch;
import info.team23h.acc.vo.player.PlayerVO;
import info.team23h.acc.vo.recode.RecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin/player")
public class PlayerController {

	@Autowired
	PlayerService playerService;

	@Autowired
	RecordService recordService;

	@GetMapping("/main")
	public String playerList(HttpServletRequest request, Model model,
							 @ModelAttribute("playerSearch") PlayerSearch playerSearch){

		List<PlayerVO> driverList = playerService.getPlayerList(playerSearch);
		model.addAttribute("driverList", recordService.getPlayerSkillEvaluatorList(driverList));

		return "/admin/player/main";
	}


	@GetMapping("/detail")
	public String playerDetail(HttpServletRequest request,
							   Model model,
							   @ModelAttribute("searchVO") SearchVO searchVO) {
		List<RecordVO> recordVOList = recordService.recordPlayerDetail(searchVO);
		model.addAttribute("playerRecordList", recordVOList);
		for(RecordVO recordVO : recordVOList){
			searchVO.setFirstName(recordVO.getFirstName());
			searchVO.setLastName(recordVO.getLastName());
		}
		double score = recordService.playerSkillEvaluator(searchVO);
		searchVO.setPlayerId(searchVO.getPlayerId().substring(1));
		model.addAttribute("ttScore", (int) Math.round(Math.floor(score)));
		model.addAttribute("searchVO",searchVO);
		return "/admin/player/popup/detail";
	}
}
