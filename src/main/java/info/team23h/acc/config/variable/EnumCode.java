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


}
