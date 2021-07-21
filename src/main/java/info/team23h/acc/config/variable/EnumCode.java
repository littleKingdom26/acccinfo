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


	@Getter
	public enum FilePath {
		board,
		banner,
		gallery,
		poster
	}


}
