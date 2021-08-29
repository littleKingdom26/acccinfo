package info.team23h.acc.config.variable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class EnumCode {

	@Getter
	@RequiredArgsConstructor
	public enum LeagueDivision implements EnumModel{
		DIVISION_1("PRO"),
		DIVISION_2("MASTER"),
		DIVISION_3("ONE MAKE");

		private final String value;

		@Override
		public String getKey() {return name();}
	}

	@Getter
	@RequiredArgsConstructor
	public enum CarClass implements EnumModel {
		GT3("GT3"),
		GT4("GT4");

		private final String value;

		@Override
		public String getKey() {return name();}
	}

	/**
	 * 포스터 타입.
	 */
	@Getter
	@RequiredArgsConstructor
	public enum PosterType implements EnumModel {
		main("일요리그"),
		sub("수요리그");
		private final String value;

		@Override
		public String getKey() {
			return name();
		}
	}


	/**
	 * 파일 경로
	 */
	@Getter
	public enum FilePath {
		board,
		banner,
		gallery,
		poster
	}

	/**
	 * 항의 범주
	 */
	@Getter
	@RequiredArgsConstructor
	public enum ComplaintsCode implements EnumModel {
		unManner("01. 비매너 주행"),
		cutting("02. 코너컷팅으로 인한 순위 이득"),
		out("03. 코스이탈 후 불안전한 진입"),
		pit("04. 피트라인 침범"),
		blueFlag("05. 블루플래그 미수행"),
		etc("06. 기타");

		private final String value;

		@Override
		public String getKey() {
			return name();
		}
	}

	/**
	 * 게시물 타입
	 */
	@Getter
	@RequiredArgsConstructor
	public enum BbsType {
		TEXT("board"),
		VIDEO("video"),
		GALLERY("gallery");
		private final String folder;
	}




}
