package info.team23h.acc.restController.front.video;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.team23h.acc.vo.front.video.VideoSaveVO;
import info.team23h.acc.vo.front.video.VideoUpdateVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("local")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VideoRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void 비디오_목록() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/video/list?page=1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("SUC"))
				.andDo(print())
				.andReturn();
	}

	@Test
	public void 비디오_저장() throws Exception {
		String regId = "리틀킹덤";
		final VideoSaveVO build = VideoSaveVO.builder()
		                                     .videoUrl("https://www.youtube.com/watch?v=KnbyyjMX_8Q")
		                                     .regId(regId)
		                                     .password("1234")
		                                     .title("k리그2")
		                                     .build();

		mockMvc.perform(MockMvcRequestBuilders.post("/api/video/")
		                                      .contentType(MediaType.APPLICATION_JSON)
		                                      .content(objectMapper.writeValueAsString(build)))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.code").value("SUC"))
		       .andExpect(jsonPath("$..regId").value(regId))
		       .andDo(print())
		       .andReturn();
	}

	@Test
	public void 비디오_상세() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/video/detail/156"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("SUC"))
				.andDo(print())
				.andReturn();
	}

	@Test
	public void 비디오_수정() throws Exception {
		final VideoUpdateVO build = VideoUpdateVO.builder()
		                                         .videoUrl("https://www.youtube.com/watch?v=m4gLMgYst9k")
		                                         .title("바뀌나??")
		                                         .password("1234")
		                                         .build();

		mockMvc.perform(MockMvcRequestBuilders.post("/api/video/156")
		                                      .contentType(MediaType.APPLICATION_JSON)
		                                      .content(objectMapper.writeValueAsString(build)))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.code").value("SUC"))
		       .andDo(print())
		       .andReturn();
	}


	@Test
	public void 비디오_비밀번호_수정() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/video/password/156?password=1234"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.code").value("SUC"))
		       .andDo(print())
		       .andReturn();
	}

	@Test
	public void 비디오_삭제() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/video/156"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.code").value("SUC"))
		       .andReturn();
	}

}