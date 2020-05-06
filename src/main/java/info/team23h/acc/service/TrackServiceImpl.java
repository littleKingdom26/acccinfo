package info.team23h.acc.service;

import info.team23h.acc.dao.TrackDAO;
import info.team23h.acc.vo.SearchVO;
import info.team23h.acc.vo.TrackVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	TrackDAO trackDAO;

	/**
	 * 트랙 SEQ 조회
	 *
	 * @param trackVO
	 * @return
	 */
	@Override
	public void getSeqForTrackName(TrackVO trackVO) {
		// 트랙SEQ 조회
		long seq = trackDAO.getTrackSeq(trackVO);
		if(seq == 0){
			// 트랙 인서트
			trackDAO.setTrack(trackVO);
		}else{
			trackVO.setSeq(seq);
		}
	}

	@Override
	public TrackVO getTrackNameForSeq(TrackVO param) {
		return trackDAO.getTrackNameForSeq(param);
	}

	@Override
	public List<TrackVO> getTrackList(SearchVO searchVO) {
		List<TrackVO> trackList = trackDAO.getTrackList();
		for(int i = 0; i < trackList.size(); i++){
			TrackVO temp = trackList.get(i);
			if(String.valueOf(temp.getSeq()).equals(searchVO.getTrackSeq())){
				temp.setSelected(true);
			}
		}
		return trackList;
	}
}
