package info.team23h.acc.restController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;

@RestController
@Slf4j
public class CommonRestController {


	@Value("${file.upload.rootpath}")
	String rootPath;

	@PostMapping("/ckEditorImgUpload")
	public HashMap<String,Object> ckEditorImgUpload(MultipartHttpServletRequest request,
													 Model model) throws IOException {
		Iterator<String> fileNames = request.getFileNames();
		MultipartFile file = null;

		if(fileNames.hasNext()){
			file = request.getFile(fileNames.next());
		}
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String fileName = file.getOriginalFilename();
		DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HHmmssSSS");
		String newFileName = LocalDateTime.now().format(dtf)+extension;
		log.debug("newFileName > " + newFileName);
		log.debug("fileName > " + fileName);
		log.debug("extension > " + extension);


		model.addAttribute("ckCsrfToken", request.getParameter("ckCsrfToken"));
		request.getSession().getServletContext().getRealPath("/");
		String newPath = rootPath+File.separator+"board"+File.separator+newFileName;
		log.debug("newPath > " + newPath);
		File dir = new File(newPath);
		if(!dir.exists()){
			dir.mkdirs();
		}

		//file.transferTo(new File(newPath));
		HashMap<String, Object> map = new HashMap<>();
		map.put("uploaded", "1");
		map.put("url", "http://localhost:8080/image/rank1.png");
		return map;
	}

}
