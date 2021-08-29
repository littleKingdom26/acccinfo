package info.team23h.acc.restController;

import info.team23h.acc.vo.car.CarVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
@Api(hidden = true)
@RequestMapping(value="/api/sample")
public class SampleRestController {

	@PostMapping("/post/test")
	public HashMap<String, Object> test(@RequestBody CarVO carVo){
		log.debug("carVo.toString() : {}", carVo.toString());
		HashMap<String, Object> map = new HashMap<>();
		map.put("data", carVo.toString());
		return map;
	}
}
