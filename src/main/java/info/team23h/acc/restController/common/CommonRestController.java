package info.team23h.acc.restController.common;

import info.team23h.acc.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		String fileName = "";
		if(fileNames.hasNext()){
			file = request.getFile(fileNames.next());
			fileName = FileUtil.save(file, "board");
		}
		log.debug("newPath > " + fileName);
		HashMap<String, Object> map = new HashMap<>();
		map.put("uploaded", "1");
		map.put("url", "/imageView/board/"+ fileName);
		return map;
	}

	@GetMapping("/imageView/{path}/{fileName}")
	public byte[] getImageView(@PathVariable("path") String path ,@PathVariable("fileName") String fileName) throws IOException{
		String url = rootPath + File.separator + path + File.separator + fileName;
		File newFile = new File(url);
		InputStream in = new FileInputStream(newFile);
		return IOUtils.toByteArray(in);
	}

	@GetMapping("/steam/api/getPlayer")
	public HashMap<String, Object> player(MultipartHttpServletRequest request,Model model) throws IOException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("data", "1");
		return map;
	}
}
