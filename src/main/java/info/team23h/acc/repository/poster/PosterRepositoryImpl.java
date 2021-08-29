package info.team23h.acc.repository.poster;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import info.team23h.acc.entity.poster.Poster;
import info.team23h.acc.entity.poster.QPoster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
public class PosterRepositoryImpl extends QuerydslRepositorySupport implements PosterRepositoryCustom {

	/**
	 * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
	 *
	 * @param domainClass must not be {@literal null}.
	 */
	public PosterRepositoryImpl() {
		super(Poster.class);
	}

	@Override
	public Page<Poster> findByType(String type, Pageable pageable) {

		JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());
		final QPoster poster = QPoster.poster;

		final JPAQuery<Poster> jpaQuery = queryFactory.selectFrom(poster).where(eqType(type));
		final List<Poster> resultList = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, jpaQuery).fetch();
		return new PageImpl<>(resultList, pageable, jpaQuery.fetchCount());
	}

	private BooleanExpression eqType(String  type) {
		if(ObjectUtils.isEmpty(type)){
			return null;
		}else{
			return QPoster.poster.type.eq(type);
		}
	}
}
