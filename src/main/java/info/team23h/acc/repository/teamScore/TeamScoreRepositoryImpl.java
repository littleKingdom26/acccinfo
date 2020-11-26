package info.team23h.acc.repository.teamScore;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import info.team23h.acc.entity.team.QTeamScore;
import info.team23h.acc.entity.team.TeamScore;
import info.team23h.acc.vo.event.EventResultVO;
import info.team23h.acc.vo.team.TeamScoreSearchVO;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamScoreRepositoryImpl extends QuerydslRepositorySupport implements TeamScoreRepositoryCustom{

	/**
	 * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
	 *
	 * @param domainClass must not be {@literal null}.
	 */
	public TeamScoreRepositoryImpl() {
		super(TeamScore.class);
	}

	@Override
	public List<TeamScoreSearchVO> findAllEventDtGroupBy() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());
		QTeamScore teamScore = QTeamScore.teamScore;
		return queryFactory.select(Projections.bean(TeamScoreSearchVO.class, teamScore.eventDt)).from(teamScore).groupBy(teamScore.eventDt).fetch();
	}
}
