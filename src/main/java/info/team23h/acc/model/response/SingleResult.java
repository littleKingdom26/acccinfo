package info.team23h.acc.model.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class SingleResult<T> extends CommonResult {

	private T data;
}
