package info.team23h.acc.controller.error;

import info.team23h.acc.config.Team23hRestException;
import info.team23h.acc.model.response.CommonResult;
import info.team23h.acc.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionRestAdvice {

	private final  transient ResponseService responseService;


	@ExceptionHandler(Team23hRestException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private CommonResult team23HRestHandleException(final Team23hRestException e){
		return responseService.getFailResult(e.getMessage());
	}




}
