package info.team23h.acc.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class HttpRequest {
	String url;

	HashMap<String,Object> param;


}
