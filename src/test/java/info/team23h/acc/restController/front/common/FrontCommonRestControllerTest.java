package info.team23h.acc.restController.front.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.team23h.acc.vo.comment.CommentVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@ActiveProfiles("local")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FrontCommonRestControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void 결과_클래스_정보() throws Exception {
		mockMvc.perform(get("/api/common/class").contentType(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		       .andDo(print());
	}

	@Test
	public void 자동차_클래스_조회() throws Exception {
		mockMvc.perform(get("/api/common/carClass").contentType(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		       .andDo(print());
	}

	@Test
	public void 댓글저장() throws  Exception{
		String comment = "코멘트 입니다.";

		CommentVO commentVO = new CommentVO();

		commentVO.setComment(comment);
		commentVO.setRegId("리틀킹덤");
		commentVO.setBbsSeq(101L);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/common/bbs/comment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(commentVO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$..comment").value(comment))
				.andDo(print())
				.andReturn();
	}

	@Test
	public void 포스터_타입_조회() throws Exception {
		mockMvc.perform(get("/api/common/posterType"))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		       .andExpect(jsonPath("$.data").isArray())
		       .andDo(print());

	}

	@Test
	public void 항의_범주_조회() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/common/complaintsCode"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data[0].key").value("unManner"))
				.andDo(print())
				.andReturn();
	}

}