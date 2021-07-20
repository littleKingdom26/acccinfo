package info.team23h.acc.controller.admin;

import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.service.poster.PosterService;
import info.team23h.acc.util.PageHelper;
import info.team23h.acc.util.StringUtil;
import info.team23h.acc.vo.poster.PosterPageResultVO;
import info.team23h.acc.vo.poster.PosterSaveVO;
import info.team23h.acc.vo.poster.PosterSearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/admin/poster")
@RequiredArgsConstructor
public class PosterController {

	final private PosterService posterService;

	/**
	 * 포스터 페이지 리스트
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/list")
	public String list(Model model, PosterSearchVO posterSearchVO){

		PageHelper pageHelper = new PageHelper();


		final Page<PosterPageResultVO> page = posterService.findPage(posterSearchVO);
		pageHelper.setCurrentPage(page.getNumber()+1);
		pageHelper.setTotalPage(page.getTotalPages());

		model.addAttribute("result", page);
		model.addAttribute("typeList", EnumCode.posterType.values());
		model.addAttribute("searchVO",posterSearchVO);
		model.addAttribute("pageHelper", pageHelper);

		return "/admin/poster/list";
	}

	/**
	 * 포스터 작성 페이지 이동
	 *
	 * @param model          the model
	 * @param posterSearchVO the poster search vo
	 * @return the string
	 */
	@GetMapping("/write")
	public String write(Model model,PosterSearchVO posterSearchVO){

		model.addAttribute("typeList", EnumCode.posterType.values());
		model.addAttribute("searchVO", posterSearchVO);
		model.addAttribute("markup", StringUtil.markupForm(posterSearchVO.getClass(), posterSearchVO));
		return "/admin/poster/view";
	}

	@PostMapping("/save")
	public String save(Model model, @ModelAttribute PosterSaveVO posterSaveVO) throws IOException {
		log.debug("posterSaveVO : {}", posterSaveVO);
		posterService.save(posterSaveVO);
		return "redirect:/admin/poster/list";
	}




}
