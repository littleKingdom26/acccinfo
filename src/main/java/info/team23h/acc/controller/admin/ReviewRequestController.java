package info.team23h.acc.controller.admin;

import info.team23h.acc.service.reviewReqeust.ReviewRequestService;
import info.team23h.acc.util.PageHelper;
import info.team23h.acc.vo.reiewReqeust.ReviewRequestPageResultVO;
import info.team23h.acc.vo.reiewReqeust.ReviewRequestSearchVO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Setter
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/reviewRequest")
public class ReviewRequestController {

	final private ReviewRequestService reviewRequestService;

	@GetMapping("/list")
	public String list(@ModelAttribute ReviewRequestSearchVO reviewRequestSearchVO, Model model){


		final Page<ReviewRequestPageResultVO> page = reviewRequestService.findPage(reviewRequestSearchVO);
		PageHelper pageHelper = new PageHelper();
		pageHelper.setCurrentPage(page.getNumber() + 1);
		pageHelper.setTotalPage(page.getTotalPages());

		model.addAttribute("result", page);
		model.addAttribute("pageHelper",pageHelper);
		model.addAttribute("searchVO",reviewRequestSearchVO);
		return "admin/reviewRequest/list";
	}

}
