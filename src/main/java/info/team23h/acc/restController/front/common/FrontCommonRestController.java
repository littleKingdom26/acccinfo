package info.team23h.acc.restController.front.common;

import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.config.variable.EnumModel;
import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.bbs.BbsService;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.vo.comment.CommentResultVO;
import info.team23h.acc.vo.comment.CommentVO;
import info.team23h.acc.vo.front.common.EnumVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
@Api(tags = "common API", description = "공통 api 리스트")
public class FrontCommonRestController {

	private final ResponseService responseService;

	private final BbsService bbsService;

	@ApiOperation(value = "리그 클래스 목록 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value="/class",produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<EnumVO>> findDivision(){

		Map<String, List<EnumVO>> enumValues = new LinkedHashMap<>();
		List<EnumVO> enumList = new ArrayList<>();
		Class<?>[] classes = EnumCode.class.getClasses();
		for(Class<?> aClass : classes){
			if(aClass.getSimpleName().contains("LeagueDivision")){
				for(Object enumType : aClass.getEnumConstants()){
					EnumModel em = (EnumModel) enumType;
					enumList.add(new EnumVO(em));
				}
			}
		}
		enumValues.put("code", enumList);
		return responseService.getSingleResult(enumList);
	}

	@ApiOperation(value = "차량 클래스 목록조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value="/carClass",produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<EnumVO>> findCarClass(){
		Map<String, List<EnumVO>> enumValues = new LinkedHashMap<>();
		List<EnumVO> enumList = new ArrayList<>();
		Class<?>[] classes = EnumCode.class.getClasses();
		for(Class<?> aClass : classes){
			if(aClass.getSimpleName().contains("CarClass")){
				for(Object enumType : aClass.getEnumConstants()){
					EnumModel em = (EnumModel) enumType;
					enumList.add(new EnumVO(em));
				}
			}
		}
		enumValues.put("code", enumList);
		return responseService.getSingleResult(enumList);
	}

	@ApiOperation(value = "공통 댓글 저장", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@PostMapping(value="/bbs/comment",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<CommentResultVO> commentSave(@RequestBody CommentVO commentVO){
		return responseService.getSingleResult(bbsService.saveComment(commentVO));
	}


	@ApiOperation(value="포스터 타입 코드", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value="/posterType",produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<EnumVO>> findPosterType(){
		Map<String, List<EnumVO>> enumValues = new LinkedHashMap<>();
		List<EnumVO> enumList = new ArrayList<>();
		Class<?>[] classes = EnumCode.class.getClasses();
		for(Class<?> aClass : classes) {
			if(aClass.getSimpleName()
			         .contains("PosterType")) {
				for(Object enumType : aClass.getEnumConstants()) {
					EnumModel em = (EnumModel) enumType;
					enumList.add(new EnumVO(em));
				}
			}
		}
		enumValues.put("code", enumList);
		return responseService.getSingleResult(enumList);
	}

	@ApiOperation(value="항의 범주 코드", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value="/complaintsCode" , produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<EnumVO>> findComplaintsCode(){
		Map<String, List<EnumVO>> enumValues = new LinkedHashMap<>();
		List<EnumVO> enumList = new ArrayList<>();
		Class<?>[] classes = EnumCode.class.getClasses();
		for(Class<?> aClass : classes) {
			if(aClass.getSimpleName()
			         .contains("ComplaintsCode")) {
				for(Object enumType : aClass.getEnumConstants()) {
					EnumModel em = (EnumModel) enumType;
					enumList.add(new EnumVO(em));
				}
			}
		}
		enumValues.put("code", enumList);
		return responseService.getSingleResult(enumList);
	}

}
