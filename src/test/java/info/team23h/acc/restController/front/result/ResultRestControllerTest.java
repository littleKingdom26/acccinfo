package info.team23h.acc.restController.front.result;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
@AutoConfigureMockMvc
class ResultRestControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private TestRestTemplate template;

	@Test
	public void 결과_년도_정보() throws Exception {
		mockMvc.perform(get("/api/result/year").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print());
	}

	@Test
	public void 시즌_조회() throws  Exception{
		String year = "2021";
		String division = "DIVISION_1";
		mockMvc.perform(get("/api/result/season/"+ year+"/"+ division).contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andDo(print());
	}

	@Test
	public void 결과_라운드_조회() throws Exception {
		String eventInfoSeq = "36";
		String round ="1";
		mockMvc.perform(get("/api/result/"+eventInfoSeq+"/"+round)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print());
	}

	@Test
	public void 결과_상세_조회() throws Exception {
		String eventInfoSeq = "34";
		String round = "1";
		String carId = "1015";
		mockMvc.perform(get("/api/result/" + eventInfoSeq + "/" + round+"/"+ carId).accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(content().string(containsString(carId)))
			   .andDo(print());



	}



}