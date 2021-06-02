package info.team23h.acc.model.response;


import lombok.*;
import org.springframework.hateoas.Link;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class HATEOASResult<T> extends CommonResult {

	private T data;

	private Link _link;
}
