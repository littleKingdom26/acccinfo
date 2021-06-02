package info.team23h.acc.service.response;


import info.team23h.acc.model.response.CommonResult;
import info.team23h.acc.model.response.HATEOASResult;
import info.team23h.acc.model.response.SingleResult;
import org.springframework.hateoas.Link;

public interface ResponseService {

	<T> SingleResult<T> getSingleResult(final T data);

	CommonResult getSuccessResult();

	CommonResult getFailResult();

	CommonResult getFailResult(final String code, final String msg);

	<T> SingleResult<T> getFailResult(final String code, final String msg, final T data);

	CommonResult getFailResult(final String msg);

	<T> HATEOASResult<T> getHATEOASResult(final T byAllPages, Link link);
}
