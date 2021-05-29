package info.team23h.acc.restController.front;

import info.team23h.acc.service.event.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
@AutoConfigureMockMvc
class MainRestControllerTest {

	@Autowired
	EventService eventService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private WebApplicationContext ctx;

	@Test
	public void 이전시즌_랭커조회_서비스() {
		eventService.getBeforeLeagueRanker();
	}

	@Test
	public void 이전시즌_랭커조회() throws Exception {
		mockMvc.perform(get("/api/main/beforeLeagueRanker").contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andDo(print());

	}


}