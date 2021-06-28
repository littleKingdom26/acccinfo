package info.team23h.acc.service.gallery;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.entity.bbs.BbsFile;
import info.team23h.acc.entity.bbs.TbBbsName;
import info.team23h.acc.repository.bbs.BbsNameRepository;
import info.team23h.acc.repository.bbs.BbsRepository;
import info.team23h.acc.util.FileUtil;
import info.team23h.acc.vo.gallery.GalleryResultVO;
import info.team23h.acc.vo.gallery.GallerySaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GalleryServiceImpl implements GalleryService {


	final private BCryptPasswordEncoder bCryptPasswordEncoder;

	final private BbsNameRepository bbsNameRepository;

	final private BbsRepository bbsRepository;

	@Override
	@Transactional
	public GalleryResultVO save(GallerySaveVO gallerySaveVO) throws IOException {
		// 파일 저장 해야함..
		final TbBbsName bbsName = bbsNameRepository.findById(4L).orElseThrow(() -> new Team23hException("게시판 없음"));

		List<BbsFile> fileList = new ArrayList<>();
		for(MultipartFile multipartFile : gallerySaveVO.getUploadFile()){
			final String subPath = "gallery";
			final String fileName = FileUtil.save(multipartFile, subPath);
			fileList.add(BbsFile.builder().fileName(fileName).oriFileName(multipartFile.getOriginalFilename()).filePath(subPath).build());
		}

		final Bbs bbs = Bbs.builder()
						   .content("")
						   .password(bCryptPasswordEncoder.encode(gallerySaveVO.getPassword()))
						   .mainFileName(gallerySaveVO.getMainFIleName())
						   .tbBbsName(bbsName)
						   .title("")
						   .bbsFileList(fileList)
						   .regId(gallerySaveVO.getRegId())
						   .build();
		return new GalleryResultVO(bbsRepository.save(bbs));
	}
}
