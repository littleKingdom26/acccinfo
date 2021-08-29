package info.team23h.acc.repository.poster;

import info.team23h.acc.entity.poster.Poster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PosterRepositoryCustom {

	Page<Poster> findByType(String type, Pageable pageable);
}
