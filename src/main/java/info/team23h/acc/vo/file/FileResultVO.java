package info.team23h.acc.vo.file;

import info.team23h.acc.entity.bbs.BbsFile;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class FileResultVO {

	private Long fileSeq;

	private String fileName;

	private String filePath;

	private String oriFileName;

	public FileResultVO(BbsFile bbsFile) {
		this.fileSeq = bbsFile.getFileSeq();
		this.fileName = bbsFile.getFileName();
		this.oriFileName = bbsFile.getOriFileName();
		this.filePath = bbsFile.getFilePath();
	}

	public String getFullPath(){
		return File.separator+"imageView"+File.separator+filePath+File.separator+fileName;
	}

}
