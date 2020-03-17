package info.team23h.acc.controller.error;

import info.team23h.acc.service.BbsService;
import info.team23h.acc.service.ViewService;
import info.team23h.acc.vo.BbsNameVO;
import info.team23h.acc.vo.ViewVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
public class CustomerErrorController implements ErrorController {

	@Autowired
	BbsService bbsService;

	@Autowired
	ViewService viewService;

	@GetMapping("/error")
	public String error(HttpServletRequest request,
						HttpServletResponse response
						,Model model) {


		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
		log.error(httpStatus.toString());
		log.error(httpStatus.getReasonPhrase());
		log.error("에러 경로 > "+ request.getAttribute("javax.servlet.error.request_uri"));
		String errorPage = "400";
		if(response.getStatus()>500 && response.getStatus()<600){
			errorPage = "500";
		}
		ViewVo viewVo = viewService.getViewCount();
		model.addAttribute("viewCount", viewVo.getPageViewCount()).addAttribute("todayViewCount", viewVo.getTodayViewCount());
		List<BbsNameVO> bbsNameList = bbsService.loadBbsName();
		model.addAttribute("bbsList", bbsNameList);

		return "error/"+ errorPage;
	}

	@Override
	public String getErrorPath() {
		return "/error/400";
	}
}
