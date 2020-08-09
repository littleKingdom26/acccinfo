package info.team23h.acc.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component("fileUtil")
public class FileUtil {

	private final ServletContext servletContext;
	public FileUtil(ServletContext servletContext) {
		log.info("Component FileUtil Scan Complete.");
		this.servletContext = servletContext;
	}

	public static String ROOT_PATH;


	@Value("${file.upload.rootpath}")
	public void setRootPath(String rootpath) {
		ROOT_PATH = rootpath;
	}

	public static String save(MultipartFile file, String subFolder) throws IOException {
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String fileName = file.getOriginalFilename();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuuMMddHHmmssSSS");
		String newFileName = LocalDateTime.now().format(dtf) + extension;

		String newPath = ROOT_PATH + File.separator + subFolder;
		Path path = Paths.get(newPath);
		// 경로 생성
		if(!Files.isDirectory(path)){
			Files.createDirectories(path);
		}
		String newFilePath = newPath + File.separator + newFileName;
		System.out.println("newPath > " + newPath);
		File newFile = new File(newFilePath);
		newFile.createNewFile();
		file.transferTo(newFile);
		return newFileName;
	}
}
