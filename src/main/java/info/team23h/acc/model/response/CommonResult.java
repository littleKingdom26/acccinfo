package info.team23h.acc.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class CommonResult {

	private boolean success;

	private String code;

	private String msg;
}
