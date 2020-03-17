package info.team23h.acc.vo;

import info.team23h.acc.util.SearchHelper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BbsSearch extends SearchHelper {
	private long nameSeq;
	private long bbsSeq;
	private String schRegUserName = "";

}
