package info.team23h.acc.config;

import info.team23h.acc.service.BbsService;
import info.team23h.acc.service.ViewService;
import info.team23h.acc.vo.BbsNameVO;
import info.team23h.acc.vo.BbsSearch;
import info.team23h.acc.vo.ViewVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Component
@Aspect
@Slf4j
public class Team23hAOP {

	@Autowired
	BbsService bbsService;

	@Value("${team23h.bbs.write}")
	private Long write;

	/**
	 * The View service.
	 */
	@Autowired
	ViewService viewService;

	@Before("execution(* info.team23h.acc.controller.*.*(..))")
	public void onBeforeHandler(JoinPoint joinPoint){

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		if(request != null){
			HttpSession session = request.getSession();
			if(session.getAttribute("sessionKey") == null || !session.getId().equals(session.getAttribute("sessionKey"))){
				session.setAttribute("sessionKey", session.getId());
				viewService.updateViewCount();
			}
		}
	}

	@After("execution(* info.team23h.acc.controller.*.*(..))")
	public void onAfterHandler(JoinPoint joinPoint) {
		Model model = null;
		BbsSearch bbsSearch = null;
		for(Object obj : joinPoint.getArgs()){
			if(obj instanceof Model){
				model = (Model) obj;
			}
			if(obj instanceof BbsSearch){
				bbsSearch = (BbsSearch) obj;
			}
		}
		if(model != null){
			List<BbsNameVO> bbsNameList = bbsService.loadBbsName();
			model.addAttribute("bbsList", bbsNameList);
			ViewVo viewVo = viewService.getViewCount();
			model.addAttribute("viewCount", viewVo.getPageViewCount()).addAttribute("todayViewCount", viewVo.getTodayViewCount());
			if(bbsSearch != null){
				for(BbsNameVO bbsNameVO : bbsNameList){
					if(bbsSearch.getNameSeq() == bbsNameVO.getSeq()){
						model.addAttribute("title",bbsNameVO.getBbsName());
					}
				}
				model.addAttribute("write", bbsSearch.getNameSeq() == write);

			}
		}
	}
}
