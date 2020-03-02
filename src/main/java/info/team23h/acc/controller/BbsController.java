package info.team23h.acc.controller;

import info.team23h.acc.service.BbsService;
import info.team23h.acc.vo.BbsSearch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@Controller
@Slf4j
public class BbsController {

	@Autowired
	BbsService bbsService;

	@Value("${team23h.bbs.admin.check}")
	private String writeCheck;

	@Value("${team23h.bbs.write}")
	private Long write;


	/**
	 * 게시판
	 * @param model
	 * @param bbsNameSeq
	 * @param bbsSearch
	 * @return
	 */
	@GetMapping("/bbs/{bbsNameSeq}")
	public String loadBbsList(Model model
							, @PathVariable("bbsNameSeq") long bbsNameSeq
							, @ModelAttribute("bbsSearch") BbsSearch bbsSearch){
		bbsSearch.setNameSeq(bbsNameSeq);
		// 게시판 조회
		HashMap<String,Object> data = bbsService.loadBbsList(bbsSearch);
		model.addAttribute("data", data);
		return "bbs/list";
	}

	@GetMapping("/bbs/moreList/{bbsNameSeq}")
	public String loadMoreBbsList(Model model,
							  @PathVariable("bbsNameSeq") long bbsNameSeq,
							  @ModelAttribute("bbsSearch") BbsSearch bbsSearch) {
		bbsSearch.setNameSeq(bbsNameSeq);
		// 게시판 조회
		HashMap<String, Object> data = bbsService.loadBbsList(bbsSearch);
		model.addAttribute("data", data);
		return "bbs/ajax/listAjax";
	}

	/**
	 * 글 작성
	 *
	 * @param model      the model
	 * @param bbsNameSeq the bbs name seq
	 * @param bbsSearch  the bbs search
	 * @param check      the check
	 * @return the string
	 */
	@GetMapping("/bbs/{bbsNameSeq}/write/{check}")
	public String createBbs(Model model
							, @PathVariable("bbsNameSeq") long bbsNameSeq
							, @ModelAttribute("bbsSearch") BbsSearch bbsSearch
							, @PathVariable("check") String check){
		bbsSearch.setNameSeq(bbsNameSeq);
		if(bbsNameSeq != write){
			if(!writeCheck.equals(check)){
				return "error/404";
			}
		}
		model.addAttribute("bbsNameSeq", bbsNameSeq);
		return "bbs/write";
	}

	/**
	 * 글 상세 보기
	 *
	 * @param model      the model
	 * @param bbsNameSeq the bbs name seq
	 * @param bbsSearch  the bbs search
	 * @param seq        the seq
	 * @return the string
	 */
	@GetMapping("/bbs/{bbsNameSeq}/{seq}")
	public String viewBbs(Model model,
							@PathVariable("bbsNameSeq") long bbsNameSeq,
							@ModelAttribute("bbsSearch") BbsSearch bbsSearch,
							@PathVariable("seq") long seq) {
		bbsSearch.setNameSeq(bbsNameSeq);
		bbsSearch.setBbsSeq(seq);
		model.addAttribute("bbsNameSeq", bbsNameSeq);
		model.addAttribute("data", bbsService.loadBbsView(bbsSearch));
		return "bbs/view";
	}

	/**
	 * 글 수정
	 *
	 * @param model      the model
	 * @param bbsNameSeq the bbs name seq
	 * @param seq        the seq
	 * @param check      the check
	 * @param bbsSearch  the bbs search
	 * @return the string
	 */
	@GetMapping("/bbs/{bbsNameSeq}/{seq}/update/{check}")
	public String createBbs(Model model,
							@PathVariable("bbsNameSeq") long bbsNameSeq,
							@PathVariable("seq") long seq,
							@PathVariable("check") String check,
							@ModelAttribute("bbsSearch") BbsSearch bbsSearch) {
		bbsSearch.setNameSeq(bbsNameSeq);
		bbsSearch.setBbsSeq(seq);
		if(bbsNameSeq != write){
			if(!writeCheck.equals(check)){
				return "error/404";
			}
		}
		model.addAttribute("data", bbsService.loadBbsView(bbsSearch));
		model.addAttribute("bbsNameSeq", bbsNameSeq);
		model.addAttribute("bbsSeq", seq);
		return "bbs/update";
	}




}
