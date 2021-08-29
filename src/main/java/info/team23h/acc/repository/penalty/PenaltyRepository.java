package info.team23h.acc.repository.penalty;

import info.team23h.acc.entity.event.TbPenalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltyRepository extends JpaRepository<TbPenalty, Long> {}
