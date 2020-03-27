package info.team23h.acc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

@Controller
@Slf4j
public class CommonController {

	@Value("${file.upload.rootpath}")
	String rootPath;

	@PostMapping("/fileUpload")
	public String fileUpload(MultipartHttpServletRequest request,
											  Model model) throws IOException {
		Iterator<String> fileNames = request.getFileNames();
		MultipartFile file = null;

		if(fileNames.hasNext()){
			file = request.getFile(fileNames.next());
		}
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String fileName = file.getOriginalFilename();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-ddHHmmssSSS");
		String newFileName = LocalDateTime.now().format(dtf) + extension;
		log.debug("newFileName > " + newFileName);
		log.debug("fileName > " + fileName);
		log.debug("extension > " + extension);

		request.getSession().getServletContext().getRealPath("/");
		String newPath = rootPath + File.separator + "board" + File.separator + newFileName;
		log.debug("newPath > " + newPath);
		File newFile = new File(newPath);
		if(!newFile.isFile()){
			newFile.mkdirs();
		}
		file.transferTo(new File(newPath));
		return "redirect:/admin/main";
	}
}
