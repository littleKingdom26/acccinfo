package info.team23h.acc.vo.recode;

import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecordVO extends CommonVO {

	/** 세션 아이디 */
	private String sessionId;
	/**
	 * 플레이어 아이디
	 */
	private String playerId;
	/**
	 * 베스트 랩
	 */
	private int bestLap;
	/**
	 * 섹터 1
	 */
	private int sector1;
	/**
	 * 섹터 2
	 */
	private int sector2;
	/**
	 * 섹터 3
	 */
	private int sector3;

	private int potential;

	/**
	 * 트랙 번호
	 */
	private long trackSeq;
	/**
	 * 차량 모델
	 */
	private String carModel;

	/**
	 * 차량 명
	 */
	private String carName;

	/**
	 * 랩 카운터
	 */
	private long lapCount;

	/** 추가 변수 */
	/*
	 * 세션 초기화 정보
	 */
	private String SessionFlag;

	/**
	 * 트랙 명
	 */
	private String trackName;

	private String trackViewName;

	/**
	 * 랭킹
	 */
	private int rank;

	/**
	 * 이름
	 */
	private String firstName;

	/**
	 * 성
	 */
	private String lastName;

	/**
	 * 베스트 타임 겝
	 */
	private String gap;

	private String bestLapView;

	private String sector1View;

	private String sector2View;

	private String sector3View;

	private String potentialView;

	private String rankImg;

	private int maxPlayer;

	private String steamAvatar;
}
