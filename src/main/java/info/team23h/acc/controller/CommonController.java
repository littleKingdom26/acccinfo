package info.team23h.acc.controller;

import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
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
		String newPath = "";
		if(fileNames.hasNext()){
			file = request.getFile(fileNames.next());
			newPath = FileUtil.save(file, EnumCode.FilePath.board.name());
		}
		log.debug("newPath > " + newPath);

		return "redirect:/admin/main";
	}


}
