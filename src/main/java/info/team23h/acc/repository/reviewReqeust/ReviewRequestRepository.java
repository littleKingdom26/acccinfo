package info.team23h.acc.repository.reviewReqeust;

import info.team23h.acc.entity.reviewReqeust.ReviewRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRequestRepository extends JpaRepository<ReviewRequest, String> {

	Page<ReviewRequest> findAllByRegIdContains(String regId,Pageable pageable);
}
