package info.team23h.acc.repository.track;

import info.team23h.acc.entity.track.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrackRepository extends JpaRepository<Track,Long> {

	Optional<Track> findByTrackName(String trackName);
}
