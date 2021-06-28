package info.team23h.acc.restController.front.gallery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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

}