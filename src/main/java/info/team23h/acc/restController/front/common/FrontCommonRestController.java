package info.team23h.acc.restController.front.common;

import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.config.variable.EnumModel;
import info.team23h.acc.model.response.SingleResult;
import info.team23h.acc.service.response.ResponseService;
import info.team23h.acc.vo.front.common.EnumVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = "common API", description = "공통 api 리스트")
@RequestMapping("/api/common")
public class FrontCommonRestController {

	@Autowired
	final ResponseService responseService;

	@ApiOperation(value = "클래스 목록 조회", notes = "## Request ##\n" + "[하위 Parameters 참고]\n\n\n\n" + "## Response ## \n" + "[하위 Model 참고]\n\n\n\n")
	@GetMapping(value="/class",produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleResult<List<EnumVO>> getDivision(){

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
}
