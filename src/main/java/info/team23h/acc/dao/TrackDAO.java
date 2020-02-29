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

	private final String PREFIX = "trackSql";

	public long getTrackSeq(TrackVO trackVO) {
		return sqlSession.selectOne(PREFIX+".getTrackSeq", trackVO);
	}

	public void setTrack(TrackVO trackVO) {
		sqlSession.insert(PREFIX+".setTrack", trackVO);
	}

	public TrackVO getTrackNameForSeq(TrackVO param) {
		return sqlSession.selectOne(PREFIX +".getTrackNameForSeq",param);
	}

	public List<TrackVO> getTrackList() {
		return sqlSession.selectList(PREFIX +".getTrackList");
	}
}
