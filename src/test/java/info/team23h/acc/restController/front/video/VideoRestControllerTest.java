package info.team23h.acc.restController.front.video;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	public void video_List() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/video/list"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("SUC"))
				.andDo(print())
				.andReturn();
	}

	@Test
	public void video_save() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/video/")
		                                      .contentType(MediaType.APPLICATION_JSON)
		                                      .content(""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("SUC"))
				.andDo(print())
				.andReturn();
	}

}