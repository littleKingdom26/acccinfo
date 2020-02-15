package info.team23h.acc.dao;

import info.team23h.acc.vo.TrackVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrackDAO {

	@Autowired
	private SqlSession sqlSession;


	public long getTrackSeq(TrackVO trackVO) {
		return sqlSession.selectOne("trackSql.getTrackSeq", trackVO);
	}

	public void setTrack(TrackVO trackVO) {
		sqlSession.insert("trackSql.setTrack", trackVO);
	}

	public TrackVO getTrackNameForSeq(TrackVO param) {
		return sqlSession.selectOne("trackSql.getTrackNameForSeq",param);
	}

	public List<TrackVO> getTrackList() {
		return sqlSession.selectList("trackSql.getTrackList");
	}
}
