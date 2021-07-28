package info.team23h.acc.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Slf4j
@AutoConfigureMockMvc
@ActiveProfiles("local")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewRequestControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private WebApplicationContext ctx;

	@Autowired
	ObjectMapper objectMapper;


	@BeforeEach()
	public void setup() {
		log.debug("beforeEach {}", "beforeEach");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
		                              .addFilters(new CharacterEncodingFilter("UTF-8", true))
		                              .alwaysDo(print())
		                              .build();
	}

	@Test
	public void 심의요청_리스트() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/reviewRequest/list?keyword=리틀"))
		       .andExpect(status().isOk())
		       .andExpect(view().name("admin/reviewRequest/list"))
		       .andDo(print())
		       .andReturn();
	}

	@Test
	public void 심의요청_목록() throws  Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/reviewRequest/view/2?keyword=리틀"))
		       .andExpect(status().isOk())
		       .andExpect(view().name("admin/reviewRequest/view"))
		       .andReturn();

	}


}