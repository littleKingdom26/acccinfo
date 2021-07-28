package info.team23h.acc.controller.admin;

import info.team23h.acc.service.bbs.BbsNameService;
import info.team23h.acc.service.bbs.BbsService;
import info.team23h.acc.util.PageHelper;
import info.team23h.acc.vo.bbs.*;
import info.team23h.acc.vo.login.LoginUserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class BoardController {


	final private BbsService bbsService;


	final private BbsNameService bbsNameService;

	@RequestMapping(value = "/board/{nameSeq}", method = {RequestMethod.GET, RequestMethod.POST})
	public String board(Model model
			,@PathVariable Long nameSeq
			,@ModelAttribute("search") AdminBbsSearchVO search
	){

		final Page<AdminBbsPageResultVO> page = bbsService.findByAllPages(nameSeq, search);
		final AdminBbsNameResultVO bbsNameResultVO = bbsNameService.findById(nameSeq);

		PageHelper pageHelper = new PageHelper();
		pageHelper.setCurrentPage(page.getNumber() + 1);
		pageHelper.setTotalPage(page.getTotalPages());


		model.addAttribute("nameSeq", nameSeq);
		model.addAttribute("search", search);
		model.addAttribute("result", page);
		model.addAttribute("bbsNameResultVO",bbsNameResultVO);
		model.addAttribute("pageHelper", pageHelper);
		return "/admin/board/main";
	}

	@RequestMapping(value = "/board/{nameSeq}/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(Model model,
						@PathVariable long nameSeq,
						@ModelAttribute("search") BbsSearch search
						) {
		search.setNameSeq(nameSeq);
		List<BbsNameVO> bbsNameVOS = bbsService.loadBbsName();
		for(BbsNameVO bbsNameVO : bbsNameVOS){
			if(bbsNameVO.getSeq() == nameSeq){
				model.addAttribute("bbsName", bbsNameVO.getBbsName());
			}
		}
		return "/admin/board/write";
	}
	@PostMapping("/board/insert")
	public String insert(@ModelAttribute("bbsVO") BbsVO bbsVO
						,@AuthenticationPrincipal LoginUserVO loginUserVO){

		bbsVO.setRegId(loginUserVO.getId());
		bbsService.save(bbsVO);
		return "redirect:/admin/board/"+ bbsVO.getNameSeq();
	}


	@PostMapping("/board/{nameSeq}/{seq}")
	public String viewBbs(Model model,
						  @PathVariable("nameSeq") long nameSeq,
						  @ModelAttribute("search") BbsSearch bbsSearch,
						  @PathVariable("seq") long seq) {
		bbsSearch.setNameSeq(nameSeq);
		bbsSearch.setBbsSeq(seq);
		List<BbsNameVO> bbsNameVOS = bbsService.loadBbsName();
		for(BbsNameVO bbsNameVO : bbsNameVOS){
			if(bbsNameVO.getSeq() == nameSeq){
				model.addAttribute("bbsName", bbsNameVO.getBbsName());
			}
		}



		model.addAttribute("bbsNameSeq", nameSeq);
		model.addAttribute("data", bbsService.loadBbsView(bbsSearch));
		return "/admin/board/view";
	}

	@PostMapping("/board/update")
	public String update(@ModelAttribute("bbsVO") BbsVO bbsVO,
						 @AuthenticationPrincipal LoginUserVO loginUserVO) {

		bbsVO.setRegId(loginUserVO.getId());
		bbsService.update(bbsVO);
		return "redirect:/admin/board/" + bbsVO.getNameSeq();
	}
}
