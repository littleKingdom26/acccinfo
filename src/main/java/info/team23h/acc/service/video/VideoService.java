package info.team23h.acc.service.video;

import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.vo.front.video.VideoResultVO;
import info.team23h.acc.vo.front.video.VideoSaveVO;
import info.team23h.acc.vo.front.video.VideoUpdateVO;

public interface VideoService {

	/**
	 * 비디오 저장
	 *
	 * @param videoSaveVO the video save vo
	 * @return the video result vo
	 */
	VideoResultVO save(VideoSaveVO videoSaveVO);

	/**
	 * 비디오 상세
	 *
	 * @param bbsSeq the bbs seq
	 * @return the video result vo
	 */
	VideoResultVO findByVideoSeq(Long bbsSeq);

	/**
	 * 비디오 수정
	 *
	 * @param videoUpdateVO the video update vo
	 * @return the video result vo
	 */
	VideoResultVO modify(VideoUpdateVO videoUpdateVO);

	/**
	 * 게시물 조회
	 *
	 * @param seq the seq
	 * @return the bbs
	 */
	Bbs findById(Long seq);

	/**
	 * 비디오 삭제
	 *
	 * @param seq the seq
	 */
	void delete(Long seq);
}
