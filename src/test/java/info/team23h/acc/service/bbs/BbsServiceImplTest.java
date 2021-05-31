package info.team23h.acc.service.bbs;

import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.vo.front.Bbs.BbsSearchVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
class BbsServiceImplTest {

	@Autowired
	BbsService bbsService;

	@Test
	public void 공지사항_조회(){

		BbsSearchVO bbsSearch = new BbsSearchVO();
		bbsSearch.setNameSeq(1L);
		final Page<Bbs> byAllPages = bbsService.findByAllPages(bbsSearch);
		log.debug("byAllPages : {}", byAllPages);
	}

}