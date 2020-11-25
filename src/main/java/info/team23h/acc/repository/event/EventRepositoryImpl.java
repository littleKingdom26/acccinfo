package info.team23h.acc.repository.event;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import info.team23h.acc.entity.car.QCar;
import info.team23h.acc.entity.event.*;
import info.team23h.acc.entity.player.QPlayer;
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
		QEventInfo eventInfo = QEventInfo.eventInfo;
		QPlayer player = QPlayer.player;
		QCar car = QCar.car;
		QScoreInfo scoreInfo = QScoreInfo.scoreInfo;
		QHandicapSub handicap = QHandicapSub.handicapSub;
		QScoreSub score = QScoreSub.scoreSub;

		StringTemplate formattedDate = Expressions.stringTemplate("DATE_FORMAT({0}, {1})", eventInfo.regDt, ConstantImpl.create("%Y-%m"));

		/* nike OrderProductMapperRepositoryImpl 참고 */
		List<EventResultVO> resultList = queryFactory.select(Projections.bean(EventResultVO.class,
																			  event.rank,
																			  event.carId,
																			  player.lastName,
																			  player.firstName,
																			  player.steamAvatar,
																			  car.carName,
																			  new CaseBuilder()
																					 .when(scoreInfo.participationYn.eq("Y"))
																					 .then(score.score.coalesce(Long.valueOf(1)))
																					 .otherwise(score.score.coalesce(Long.valueOf(0)))
																					 .as("score")
																			 , handicap.handicap.coalesce(Long.valueOf(0)).as("handicap")
																			 , event.raceTime
																			 , event.penalty
																			 , event.totalTime
																			 , event.totalLap
																			 , event.bestLap
																			 , scoreInfo.participationYn
															 				 , formattedDate.as("regDt"))
															).from(event)
													 .innerJoin(eventInfo).on(event.eventInfoSeq.eq(eventInfo.eventInfoSeq))
													 .innerJoin(player).on(event.player.PlayerId.eq(player.PlayerId))
													 .innerJoin(car).on(event.car.carModel.eq(car.carModel))
													 .innerJoin(scoreInfo).on(eventInfo.scoreInfo.scoreInfoSeq.eq(scoreInfo.scoreInfoSeq))
													 .leftJoin(handicap).on(event.rank.eq(handicap.rank).and(eventInfo.handicapInfo.handicapInfoSeq.eq(handicap.handicapInfo.handicapInfoSeq)))
													 .leftJoin(score).on(event.rank.eq(score.rank).and(eventInfo.scoreInfo.scoreInfoSeq.eq(score.scoreInfo.scoreInfoSeq)))
													 .where(event.eventInfoSeq.eq(teamScoreSaveVO.getEventInfoSeq()).and(event.round.eq(teamScoreSaveVO.getRound())))
													 .orderBy(event.rank.asc())
													 .fetch();
		return resultList;
	}
}
