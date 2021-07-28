package info.team23h.acc.controller.admin;

import info.team23h.acc.service.reviewReqeust.ReviewRequestService;
import info.team23h.acc.util.PageHelper;
import info.team23h.acc.util.StringUtil;
import info.team23h.acc.vo.reiewReqeust.AdminReviewRequestResultVO;
import info.team23h.acc.vo.reiewReqeust.ReviewRequestPageResultVO;
import info.team23h.acc.vo.reiewReqeust.ReviewRequestSearchVO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/view/{reviewRequestKey}")
	public String view(@PathVariable("reviewRequestKey") Long reviewRequestKey, @ModelAttribute ReviewRequestSearchVO reviewRequestSearchVO, Model model){
		final AdminReviewRequestResultVO resultVO = reviewRequestService.findByDetail(reviewRequestKey);

		model.addAttribute("result", resultVO);
		model.addAttribute("searchVO", reviewRequestSearchVO);
		model.addAttribute("markup", StringUtil.markupForm(reviewRequestSearchVO.getClass(), reviewRequestSearchVO));

		return "admin/reviewRequest/view";
	}

}
