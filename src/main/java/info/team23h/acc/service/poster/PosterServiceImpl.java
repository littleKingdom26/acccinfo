package info.team23h.acc.service.poster;

import info.team23h.acc.config.variable.EnumCode;
import info.team23h.acc.entity.poster.Poster;
import info.team23h.acc.repository.poster.PosterRepository;
import info.team23h.acc.util.FileUtil;
import info.team23h.acc.vo.poster.PosterPageResultVO;
import info.team23h.acc.vo.poster.PosterSaveVO;
import info.team23h.acc.vo.poster.PosterSearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PosterServiceImpl implements PosterService {

	final private PosterRepository posterRepository;

	@Override
	public Page<PosterPageResultVO> findPage(PosterSearchVO posterSearchVO) {
		final Page<Poster> posterPage = posterRepository.findByType(posterSearchVO.getSearchType(),
																   PageRequest.of(posterSearchVO.getPage() - 1, posterSearchVO.getSize(), Sort.by("posterKey").descending()));
		return posterPage.map(PosterPageResultVO::new);


	}

	@Override
	@Transactional
	public void save(PosterSaveVO posterSaveVO) throws IOException {
		final String newFileName = FileUtil.save(posterSaveVO.getFile(), EnumCode.filePath.poster.name());
		final Poster poster = Poster.builder().fileName(newFileName).filePath(EnumCode.filePath.poster.name()).type(posterSaveVO.getType()).title(posterSaveVO.getTitle()).build();
		posterRepository.save(poster);
	}

	@Override
	@Transactional
	public void delete(Long posterKey) {
		posterRepository.deleteById(posterKey);
	}
}
