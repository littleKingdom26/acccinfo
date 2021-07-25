package info.team23h.acc.restController.front.reviewReqeust;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.vo.front.reviewReqeustSaveVO.ReviewRequestSaveVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@ActiveProfiles("local")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewRequestRestControllerTest {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	MockMvc mockMvc;


	@Test
	public void 심의_요청_저장() throws Exception {

		String regId = "리틀킹덤";

		final ReviewRequestSaveVO build = ReviewRequestSaveVO.builder()
		                                                     .reviewTarget("준형")
		                                                     .regId(regId)
		                                                     .complaints(EnumCode.ComplaintsCode.blueFlag.getKey())
		                                                     .event("8월 3라운드")
		                                                     .sessionDivision("프로")
		                                                     .replayTime("1분10초")
		                                                     .description("안비켰어요!")
		                                                     .build();

		mockMvc.perform(MockMvcRequestBuilders.post("/api/reviewRequest")
		                                      .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                      .content(objectMapper.writeValueAsString(build)))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		       .andExpect(jsonPath("$.data.regId").value(regId))
		       .andDo(print())
		       .andReturn();
	}
}