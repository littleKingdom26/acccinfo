package info.team23h.acc.repository.poster;

import info.team23h.acc.entity.poster.Poster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosterRepository extends JpaRepository<Poster, Long>, PosterRepositoryCustom {


	List<Poster> findAllByTypeOrderByRegDtDesc(String type);


	List<Poster> findAllByOrderByTypeAscRegDtDesc();
}
