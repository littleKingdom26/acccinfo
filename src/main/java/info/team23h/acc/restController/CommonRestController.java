package info.team23h.acc.restController;

import info.team23h.acc.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
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

}
