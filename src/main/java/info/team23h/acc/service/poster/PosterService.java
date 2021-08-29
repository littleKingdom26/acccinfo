package info.team23h.acc.service.poster;

import info.team23h.acc.vo.front.main.PosterMainResultVO;
import info.team23h.acc.vo.poster.PosterPageResultVO;
import info.team23h.acc.vo.poster.PosterSaveVO;
import info.team23h.acc.vo.poster.PosterSearchVO;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface PosterService {

	/**
	 * 포스터 리스트
	 * @param posterSearchVO
	 */
	Page<PosterPageResultVO> findPage(PosterSearchVO posterSearchVO);


	/**
	 * 포스터 저장
	 *
	 * @param posterSaveVO the poster save vo
	 * @throws IOException the io exception
	 */
	void save(PosterSaveVO posterSaveVO) throws IOException;

	/**
	 * 포스터 삭제
	 *
	 * @param posterKey the poster key
	 */
	void delete(Long posterKey);

	/**
	 * 메인 포스터 조회
	 *
	 * @param posterType the poster type
	 */
	List<PosterMainResultVO> findByMainPoster(String posterType);

	/**
	 * 메인 포스터 전체 조회
	 *
	 * @return the list
	 */
	List<PosterMainResultVO> findByMainPoster();

}
