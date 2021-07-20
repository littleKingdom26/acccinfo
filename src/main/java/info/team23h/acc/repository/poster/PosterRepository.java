package info.team23h.acc.repository.poster;

import info.team23h.acc.entity.poster.Poster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosterRepository extends JpaRepository<Poster, Long>, PosterRepositoryCustom {



}
