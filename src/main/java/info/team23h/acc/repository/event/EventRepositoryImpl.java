package info.team23h.acc.repository.event;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import info.team23h.acc.entity.event.Event;
import info.team23h.acc.entity.event.QEvent;
import info.team23h.acc.vo.event.EventResultVO;
import info.team23h.acc.vo.team.TeamScoreSaveVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class EventRepositoryImpl extends QuerydslRepositorySupport implements EventRepositoryCustom{

	/**
	 * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
	 *
	 * @param domainClass must not be {@literal null}.
	 */
	public EventRepositoryImpl() {
		super(Event.class);
	}

	@Override
	public List<EventResultVO> findByEventList(TeamScoreSaveVO teamScoreSaveVO) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());
		QEvent event = QEvent.event;

		/* nike OrderProductMapperRepositoryImpl 참고
		JPAQuery<EventResultVO> query = queryFactory.select(Projections.bean(EventResultVO.class,
																			 event.rank,
																			 event.carId,
																			 event.la)
		)
		*/

		return null;
	}
}
