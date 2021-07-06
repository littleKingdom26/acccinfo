package info.team23h.acc.service.gallery;

import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.vo.front.gallery.GalleryResultVO;
import info.team23h.acc.vo.front.gallery.GallerySaveVO;
import info.team23h.acc.vo.front.gallery.GalleryUpdateVO;

import java.io.IOException;

public interface GalleryService {

	/**
	 * 겔러리 저장
	 *
	 * @param gallerySaveVO the gallery save vo
	 * @return the gallery result vo
	 * @throws IOException the io exception
	 */
	GalleryResultVO save(GallerySaveVO gallerySaveVO) throws IOException;

	/**
	 * 겔러리 수정
	 *
	 * @param galleryUpdateVO the gallery update vo
	 * @return the gallery result vo
	 */
	GalleryResultVO modify(GalleryUpdateVO galleryUpdateVO) throws IOException;

	/**
	 * 게시물 검색
	 *
	 * @param seq the seq
	 * @return the bbs
	 */
	Bbs findById(Long seq);

	/**
	 * 게시물 삭제
	 *
	 * @param seq the seq
	 */
	void delete(Long seq);

	void deleteFile(Long fileSeq);
}
