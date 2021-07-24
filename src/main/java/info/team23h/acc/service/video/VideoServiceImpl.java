package info.team23h.acc.service.video;

import info.team23h.acc.config.Team23hException;
import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.entity.bbs.TbBbsName;
import info.team23h.acc.repository.bbs.BbsNameRepository;
import info.team23h.acc.repository.bbs.BbsRepository;
import info.team23h.acc.vo.front.video.VideoResultVO;
import info.team23h.acc.vo.front.video.VideoSaveVO;
import info.team23h.acc.vo.front.video.VideoUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoServiceImpl implements VideoService{

	final private BCryptPasswordEncoder bCryptPasswordEncoder;

	final private BbsRepository bbsRepository;

	final private BbsNameRepository bbsNameRepository;

	@Override
	@Transactional
	public VideoResultVO save(VideoSaveVO videoSaveVO) {

		final TbBbsName bbsName = bbsNameRepository.findById(5L)
		                                           .orElseThrow(() -> new Team23hException("등록된 비디오가 없습니다."));

		final Bbs bbs = Bbs.builder()
		                   .title(videoSaveVO.getTitle())
		                   .content(videoSaveVO.getVideoUrl())
		                   .password(bCryptPasswordEncoder.encode(videoSaveVO.getPassword()))
		                   .regId(videoSaveVO.getRegId())
		                   .tbBbsName(bbsName)
		                   .build();

		final Bbs save = bbsRepository.save(bbs);
		VideoResultVO result = new VideoResultVO(save);
		return result;
	}

	@Override
	public VideoResultVO findByVideoSeq(Long bbsSeq) {
		final Bbs bbs = bbsRepository.findById(bbsSeq)
		                             .orElseThrow(() -> new Team23hException("등록된 비디오가 없습니다."));
		return new VideoResultVO(bbs);

	}

	@Override
	@Transactional
	public VideoResultVO modify(VideoUpdateVO videoUpdateVO) {
		final Bbs bbs = bbsRepository.findById(videoUpdateVO.getSeq())
		                             .orElseThrow(() -> new Team23hException("등록된 비디오가 없습니다."));

		if(bCryptPasswordEncoder.matches(videoUpdateVO.getPassword(), bbs.getPassword())) {
			bbs.updateVideo(videoUpdateVO);
			return new VideoResultVO(bbs);
		}else{
			throw new Team23hException("비밀번호 다릅니다.");
		}
	}

	@Override
	public Bbs findById(Long seq) {
		return bbsRepository.findById(seq)
		                    .orElseThrow(() -> new Team23hException("등록된 비디오가 없습니다."));

	}

	@Override
	@Transactional
	public void delete(Long seq) {
		final Bbs bbs = bbsRepository.findById(seq)
		                             .orElseThrow(() -> new Team23hException("등록된 비디오가 없습니다."));
		bbsRepository.deleteById(seq);
	}
}
