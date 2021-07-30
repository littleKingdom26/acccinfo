package info.team23h.acc.repository.bbs;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import info.team23h.acc.entity.bbs.Bbs;
import info.team23h.acc.entity.bbs.QBbs;
import info.team23h.acc.entity.bbs.TbBbsName;
import info.team23h.acc.vo.bbs.AdminBbsSearchVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

public class BbsRepositoryImpl extends QuerydslRepositorySupport implements BbsRepositoryCustom {

	public BbsRepositoryImpl() {
		super(Bbs.class);
	}

	@Override
	public Page<Bbs> findAdminPage(TbBbsName bbsName, AdminBbsSearchVO search, Pageable pageable) {

		JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());
		final QBbs bbs = QBbs.bbs;
		final JPAQuery<Bbs> jpaQuery = queryFactory.selectFrom(bbs)
		                                           .where(bbs.tbBbsName.seq.eq(bbsName.getSeq()), containsTitleOrRegId(search));
		final List<Bbs> resultList = Objects.requireNonNull(getQuerydsl())
		                                    .applyPagination(pageable, jpaQuery)
		                                    .fetch();
		return new PageImpl<>(resultList, pageable, jpaQuery.fetchCount());
	}

	private Predicate containsTitleOrRegId(AdminBbsSearchVO searchVO) {
		BooleanBuilder result =  new BooleanBuilder();
		if(! ObjectUtils.isEmpty(searchVO.getTitle()) && !ObjectUtils.isEmpty(searchVO.getRegId())) {
			result.and(QBbs.bbs.title.contains(searchVO.getTitle()).or(QBbs.bbs.regId.contains(searchVO.getRegId())));
		}else if(! ObjectUtils.isEmpty(searchVO.getTitle())){
			result.and(QBbs.bbs.title.contains(searchVO.getTitle()));
		}else if(! ObjectUtils.isEmpty(searchVO.getRegId())){
			result.and(QBbs.bbs.regId.contains(searchVO.getRegId()));
		}

		return result;
	}
}
