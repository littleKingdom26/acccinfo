package info.team23h.acc.service.gallery;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.entity.bbs.BbsFile;
import info.team23h.acc.entity.bbs.TbBbsName;
import info.team23h.acc.repository.bbs.BbsNameRepository;
import info.team23h.acc.repository.bbs.BbsRepository;
import info.team23h.acc.repository.file.FileRepository;
import info.team23h.acc.util.FileUtil;
import info.team23h.acc.vo.front.gallery.GalleryResultVO;
import info.team23h.acc.vo.front.gallery.GallerySaveVO;
import info.team23h.acc.vo.front.gallery.GalleryUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GalleryServiceImpl implements GalleryService {

	final private BCryptPasswordEncoder bCryptPasswordEncoder;

	final private BbsNameRepository bbsNameRepository;

	final private BbsRepository bbsRepository;

	final private FileRepository fileRepository;

	@Override
	@Transactional
	public GalleryResultVO save(GallerySaveVO gallerySaveVO) throws IOException {
		// 파일 저장 해야함..
		final TbBbsName bbsName = bbsNameRepository.findById(4L).orElseThrow(() -> new Team23hException("게시판 없음"));

		List<BbsFile> fileList = new ArrayList<>();
		for(MultipartFile multipartFile : gallerySaveVO.getUploadFile()){
			final String fileName = FileUtil.save(multipartFile, EnumCode.FilePath.gallery.name());
			fileList.add(BbsFile.builder().fileName(fileName).oriFileName(multipartFile.getOriginalFilename()).filePath(EnumCode.FilePath.gallery.name()).build());
		}

		final Bbs bbs = Bbs.builder()
						   .content("")
						   .password(bCryptPasswordEncoder.encode(gallerySaveVO.getPassword()))
						   .mainFileName(gallerySaveVO.getMainFIleName())
						   .tbBbsName(bbsName)
						   .title(gallerySaveVO.getTitle())
						   .bbsFileList(fileList)
						   .regId(gallerySaveVO.getRegId())
						   .build();
		return new GalleryResultVO(bbsRepository.save(bbs));
	}

	@Override
	@Transactional
	public GalleryResultVO modify(GalleryUpdateVO galleryUpdateVO) throws IOException ,Team23hException {
		final Bbs bbs = bbsRepository.findById(galleryUpdateVO.getSeq()).orElseThrow(() -> new Team23hException("게시물 없음"));
		if(bCryptPasswordEncoder.matches(galleryUpdateVO.getPassword(), bbs.getPassword())){
			List<BbsFile> fileList = new ArrayList<>();
			if(!ObjectUtils.isEmpty(galleryUpdateVO.getUploadFile())){
				for(MultipartFile multipartFile : galleryUpdateVO.getUploadFile()){
					final String fileName = FileUtil.save(multipartFile, EnumCode.FilePath.gallery.name());
					fileList.add(BbsFile.builder().fileName(fileName).oriFileName(multipartFile.getOriginalFilename()).filePath(EnumCode.FilePath.gallery.name()).build());
				}
			}
			bbs.update(galleryUpdateVO, fileList);
		}else{
			throw new Team23hException("비밀번호 다릅니다.");
		}
		return new GalleryResultVO(bbs);
	}

	@Override
	public Bbs findById(Long seq) {
		return bbsRepository.findById(seq).orElseThrow(() -> new Team23hException("게시물 없음"));
	}

	@Override
	@Transactional
	public void delete(Long seq) {
		final Bbs bbs = bbsRepository.findById(seq).orElseThrow(() -> new Team23hException("게시물 없음"));
		bbs.getBbsFileList().forEach(bbsFile -> {
			FileUtil.delete(bbsFile.getFilePath(), bbsFile.getFileName());
		});
		bbsRepository.deleteById(seq);
	}

	@Override
	public void deleteFile(Long fileSeq) {
		//파일 조회
		final BbsFile bbsFile = fileRepository.findById(fileSeq).orElseThrow(() -> new Team23hException("파일이 없습니다."));
		// 파일 물리 삭제
		FileUtil.delete(bbsFile.getFilePath(), bbsFile.getFileName());
		// 파일 디비 삭제
		fileRepository.delete(bbsFile);
	}

	@Override
	public List<GalleryResultVO> findByMainGallery() {
		final TbBbsName bbsName = bbsNameRepository.findById(4L).orElseThrow(() -> new Team23hException("게시판 없음"));
		final List<GalleryResultVO> resultList = bbsName.getBbsList().stream().sorted(Comparator.comparing(Bbs::getRegDt).reversed()).map(GalleryResultVO::new).limit(7).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public GalleryResultVO findByGallerySeq(Long bbsSeq) {
		final Bbs bbs = bbsRepository.findById(bbsSeq)
		                             .orElseThrow(() -> new Team23hException("게시물이 없습니다."));
		return new GalleryResultVO(bbs);
	}

}
