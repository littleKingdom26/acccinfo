package info.team23h.acc.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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

	/**
	 * 파일 저장
	 *
	 * @param file      the file
	 * @param subFolder the sub folder
	 * @return the string
	 * @throws IOException the io exception
	 */
	public static String save(MultipartFile file, String subFolder) throws IOException {
		final String filename = StringUtils.getFilename(file.getOriginalFilename());
		final String extension = "."+StringUtils.getFilenameExtension(file.getOriginalFilename());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuuMMddHHmmssSSS");
		String newFileName = LocalDateTime.now().format(dtf) + extension;

		String newPath = ROOT_PATH + File.separator + subFolder;
		Path path = Paths.get(newPath);
		// 경로 생성
		if(!Files.isDirectory(path)){
			Files.createDirectories(path);
		}
		String newFilePath = newPath + File.separator + newFileName;
		File newFile = new File(newFilePath);
		newFile.createNewFile();
		file.transferTo(newFile);
		return newFileName;
	}


	/**
	 * 파일 삭제
	 *
	 * @param subFolder the sub folder
	 * @param fileName  the file name
	 */
	public static void delete(String subFolder,String fileName){
		final String path = ROOT_PATH+ File.separator + subFolder+File.separator+fileName;
		File file = new File(path);
		if(file.isFile()){
			file.delete();
		}
	}
}
