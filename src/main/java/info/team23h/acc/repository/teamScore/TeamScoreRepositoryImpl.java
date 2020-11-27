package info.team23h.acc.repository.teamScore;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import info.team23h.acc.entity.event.QEventInfo;
import info.team23h.acc.entity.event.QEventMeta;
import info.team23h.acc.entity.player.QPlayer;
import info.team23h.acc.entity.team.QTeam;
import info.team23h.acc.entity.team.QTeamInfo;
import info.team23h.acc.entity.team.QTeamScore;
import info.team23h.acc.entity.team.TeamScore;
import info.team23h.acc.entity.track.QTrack;
import info.team23h.acc.vo.team.TeamScoreResultDetailVO;
import info.team23h.acc.vo.team.TeamScoreSearchVO;
import info.team23h.acc.vo.team.TeamScoreTeamInfoResultVO;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TeamScoreRepositoryImpl extends QuerydslRepositorySupport implements TeamScoreRepositoryCustom{

	final DataSource dataSource;

	/**
	 * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
	 *
	 * @param domainClass must not be {@literal null}.
	 */
	public TeamScoreRepositoryImpl(DataSource dataSource) {
		super(TeamScore.class);
		this.dataSource = dataSource;
	}

	@Override
	public List<TeamScoreSearchVO> findAllEventDtGroupBy() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());
		QTeamScore teamScore = QTeamScore.teamScore;
		return queryFactory.select(Projections.bean(TeamScoreSearchVO.class, teamScore.eventDt)).from(teamScore).groupBy(teamScore.eventDt).orderBy(teamScore.eventDt.desc()).fetch();
	}

	@Override
	public List<TeamScoreTeamInfoResultVO> findTeamScore(TeamScoreSearchVO teamScoreSearchVO) {

		/* rank / rownum 등.. 사용할때.. 나중에 쓸일이 생길듯??
		SQLTemplates templates = new MySQLTemplates();
		Configuration configuration = new Configuration(templates);
		SQLQueryFactory sqlQueryFactory = new SQLQueryFactory(configuration, new SpringConnectionProvider(dataSource));

		Path<Long> testRank = Expressions.numberPath(Long.class, "testRank");
		sqlQueryFactory.query().select(SQLExpressions.rank().over().orderBy(teamScore.score.desc()).as(testRank)).from(teamScore).fetch();
		*/

		JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());
		QTeamScore teamScore = QTeamScore.teamScore;
		QTeamInfo teamInfo = QTeamInfo.teamInfo;

		List<TeamScoreTeamInfoResultVO> resultList = queryFactory
				.select(Projections.bean(TeamScoreTeamInfoResultVO.class, teamInfo.teamInfoSeq, teamInfo.teamName,teamScore.score.sum().as("teamScore"))).from(teamScore)
				.innerJoin(teamInfo).on(teamScore.teamInfo.teamInfoSeq.eq(teamInfo.teamInfoSeq)).where(eqEventDT(teamScoreSearchVO))
				.groupBy(teamScore.teamInfo.teamInfoSeq).orderBy(teamScore.score.sum().desc()).fetch();

		Long i = Long.valueOf(0);
		for(TeamScoreTeamInfoResultVO teamScoreTeamInfoResultVO : resultList){
			teamScoreTeamInfoResultVO.setRank(++i);
		}

		return resultList;
	}

	@Override
	public List<TeamScoreResultDetailVO> findByTeamScoreDetail(TeamScoreSearchVO teamScoreSearchVO) {

		JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());
		QTeamScore score  = QTeamScore.teamScore;
		QTeam team = QTeam.team;
		QPlayer player = QPlayer.player;
		QEventInfo eventInfo = QEventInfo.eventInfo;
		QEventMeta eventMeta = QEventMeta.eventMeta;
		QTrack track = QTrack.track;

		List<TeamScoreResultDetailVO> resultList = queryFactory
				.select(Projections.bean(TeamScoreResultDetailVO.class,player.firstName,player.lastName,player.steamAvatar,score.score,eventInfo.title,eventMeta.round,track.trackName,track.trackViewName))
				.from(score)
				.innerJoin(team).on(score.team.teamSeq.eq(team.teamSeq))
				.innerJoin(player).on(score.team.player.playerId.eq(player.playerId))
				.innerJoin(eventInfo).on(score.eventMeta.eventInfoSeq.eq(eventInfo.eventInfoSeq))
				.innerJoin(eventMeta).on(score.eventMeta.eventInfoSeq.eq(eventMeta.eventInfoSeq).and(score.eventMeta.round.eq(eventMeta.round)))
				.innerJoin(track).on(score.eventMeta.track.trackName.eq(track.trackName))
				.where(eqTeamInfo(teamScoreSearchVO).and(eqEventDT(teamScoreSearchVO)))
//				.orderBy(score.teamInfo.teamInfoSeq.desc(),score.eventMeta.round.desc(),score.score.desc()).fetch();
				.orderBy(score.score.desc()).fetch();


		return resultList;
	}

	private BooleanExpression eqTeamInfo(TeamScoreSearchVO teamScoreSearchVO) {
		if("".equals(teamScoreSearchVO.getTeamInfoSeq()) || teamScoreSearchVO.getTeamInfoSeq() == null){
			return null;
		}else{
			return QTeamScore.teamScore.teamInfo.teamInfoSeq.eq(teamScoreSearchVO.getTeamInfoSeq());
		}
	}

	private BooleanExpression eqEventDT(TeamScoreSearchVO teamScoreSearchVO) {
		if("".equals(teamScoreSearchVO.getSearchEventDt()) || teamScoreSearchVO.getSearchEventDt() == null){
			return null;
		}else{
			return QTeamScore.teamScore.eventDt.eq(teamScoreSearchVO.getSearchEventDt());
		}
	}

}
