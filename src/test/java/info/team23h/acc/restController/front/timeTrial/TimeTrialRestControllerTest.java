package info.team23h.acc.restController.front.timeTrial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
@AutoConfigureMockMvc
class TimeTrialRestControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void 주차_조회() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/timeTrial/week").accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
				.andExpect(content().string(containsString("2021-06-06")))
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andDo(print());
	}

	@Test
	public void 트랙_조회() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/timeTrial/track").accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(content().string(containsString("DONIGTON(2019)")))
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andDo(print());
	}

	@Test
	public void 주차_기록_조회() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/timeTrial/week/gt3/80").accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andDo(print());
	}

	@Test
	public void 플레이어_상세_조회() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/timeTrial/week/gt3/S76561198810302340/detail").accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andDo(print());
	}

	@Test
	public void 플레이어_티티스코어() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/timeTrial/week/gt3/S76561198810302340/ttScore").accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andDo(print());
	}

	@Test
	public void 주차_Gt4기록_조회() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/timeTrial/week/gt4/80").accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andDo(print());
	}

	@Test
	public void 플레이어_GT4상세_조회() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/timeTrial/week/gt4/S76561198084228484/detail").accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andDo(print());
	}

	@Test
	public void gt3_트랙_조회() throws Exception {
		Long trackSeq = 54L;
		mockMvc.perform(MockMvcRequestBuilders.get("/api/timeTrial/track/gt3/"+trackSeq))
			   .andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$..code").value("SUC"))
				.andDo(print())
				.andReturn();


	}

	@Test
	public void gt4_트랙_조회() throws Exception {
		Long trackSeq = 54L;
		mockMvc.perform(MockMvcRequestBuilders.get("/api/timeTrial/track/gt4/" + trackSeq))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$..code").value("SUC"))
			   .andDo(print())
			   .andReturn();
	}
}