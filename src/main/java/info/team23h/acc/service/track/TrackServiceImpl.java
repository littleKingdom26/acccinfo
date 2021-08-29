package info.team23h.acc.service.track;

import info.team23h.acc.dao.TrackDAO;
import info.team23h.acc.entity.track.Track;
import info.team23h.acc.repository.track.TrackRepository;
import info.team23h.acc.vo.common.SearchVO;
import info.team23h.acc.vo.front.timeTrial.TrackResultVO;
import info.team23h.acc.vo.track.TrackVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	TrackDAO trackDAO;

	@Autowired
	TrackRepository trackRepository;

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

	@Override
	public List<TrackResultVO> findTrackList() {
		final List<Track> all = trackRepository.findAll();
		final List<TrackResultVO> collect = all.stream().sorted(Comparator.comparing(Track::getTrackName)).map(TrackResultVO::new).collect(Collectors.toList());
		return collect;
	}
}
