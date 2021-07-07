package info.team23h.acc.restController.front.gallery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.FileInputStream;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
@AutoConfigureMockMvc
class GalleryRestControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void 파일테스트() throws Exception {

//		URI url = new URI("e:/upload/board/20200710012450011.jpg");
		File file = new File("e:/upload/board/20200710012450011.jpg");


		MockMultipartFile mockFile = new MockMultipartFile("uploadFile",new FileInputStream(file));

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/gallery/save").file(mockFile)).andDo(print());
	}

	@Test
	public void 게시물조회() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/gallery/list?page=1"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(content().string(containsString("test1")))
			   .andDo(print());
	}

	@Test
	public void 게시물_상세() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/gallery/detail/142"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string(containsString("test1")))
				.andDo(print());
	}

	@Test
	public void 파일_삭제() throws Exception{
		//4
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/gallery/file/4"))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void 게시물_삭제() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/gallery/142"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string(containsString("SUC")))
				.andDo(print());
	}

}