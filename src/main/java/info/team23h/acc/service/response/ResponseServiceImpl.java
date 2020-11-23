package info.team23h.acc.service.response;

import info.team23h.acc.config.variable.CommonResponse;
import info.team23h.acc.model.response.CommonResult;
import info.team23h.acc.model.response.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

	private static final CommonResponse FAIL = CommonResponse.FAIL;

	private static final CommonResponse SUCCESS = CommonResponse.SUCCESS;

	public <T> SingleResult<T> getSingleResult(final T data) {
		final SingleResult<T> result = new SingleResult<>();
		result.setData(data);
		setSuccessResult(result);
		return result;
	}

	public CommonResult getSuccessResult() {
		final CommonResult result = new CommonResult();
		setSuccessResult(result);
		return result;
	}

	public CommonResult getFailResult() {
		final CommonResult result = new CommonResult();
		result.setSuccess(false);
		result.setCode(FAIL.getCode());
		result.setMsg(FAIL.getMsg());
		return result;
	}

	public CommonResult getFailResult(final String code, final String msg) {
		final CommonResult result = new CommonResult();
		result.setSuccess(false);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	public <T> SingleResult<T> getFailResult(final String code, final String msg, final T data) {
		final SingleResult<T> result = new SingleResult<>();
		result.setSuccess(false);
		result.setCode(code);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}

	public CommonResult getFailResult(final String msg) {
		return getFailResult(FAIL.getCode(), msg);
	}

	private static void setSuccessResult(final CommonResult result) {
		result.setSuccess(true);
		result.setCode(SUCCESS.getCode());
		result.setMsg(SUCCESS.getMsg());
	}


}
