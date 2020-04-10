package info.team23h.acc.restController;

import info.team23h.acc.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

@RestController
@Slf4j
public class CommonRestController {

	@Value("${file.upload.rootpath}")
	String rootPath;

	@PostMapping("/ckEditorImgUpload")
	public HashMap<String,Object> fileUpload(MultipartHttpServletRequest request,
													 Model model) throws IOException {
		Iterator<String> fileNames = request.getFileNames();
		MultipartFile file = null;
		String newPath = "";
		if(fileNames.hasNext()){
			file = request.getFile(fileNames.next());
			newPath = FileUtil.save(file, "board");
		}
		log.debug("newPath > " + newPath);
		HashMap<String, Object> map = new HashMap<>();
		map.put("uploaded", "1");
		map.put("url", "http://localhost:8080/image/rank1.png");
		return map;
	}

	@GetMapping("/imageView")
	public byte[] getImageView() throws IOException{
		String url = rootPath + File.separator + "board" + File.separator + "20200328131449650.jpg";

//		InputStream in = getClass().getResourceAsStream(rootPath + File.separator + "board"+ File.separator+"20200328162902833.jpg");
		File newFile = new File(url);
		InputStream in = new FileInputStream(newFile);
		return IOUtils.toByteArray(in);
	}

}
