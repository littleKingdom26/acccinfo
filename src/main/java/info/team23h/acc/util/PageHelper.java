package info.team23h.acc.util;

import lombok.Data;

/**
 * PageHelper
 * 
 * @since 2019.12.27
 * @author [오지훈]
 * @Description PageHelper 작성
 * @history [오지훈] [2019.12.27] [최초 작성]
 * 
 */

@Data
public class PageHelper {

	/**
	 * 페이징 마크업
	 */
	private String paging;

	/**
	 * 총 페이지 수
	 */
	private int totalPage;

	/**
	 * 현재 페이지 값
	 */
	private int currentPage = 1;

	public String getPaging() {
		String moveStr = "";

		long total = totalPage;
		int add = 10;
		int prev = (int) Math.floor((currentPage - 1) / add * 1.0) * add;
		int next = prev + add + 1;

		moveStr = "<div class='pagingArea'> \n";
		moveStr += "<div class='paging'> \n";

		if (currentPage > 1) {
			long page = currentPage - 1;
			moveStr += "<a href='javascript:paging(1);' class='prevEnd'>처음</a>\n";
			moveStr += "<a href='javascript:paging(" + page + ");' class='prev'>이전</a>\n";

		} else {
			moveStr += "<a href='javascript:paging(1);' class='prevEnd'>처음</a>\n";
			moveStr += "<a href='javascript:paging(1);' class='prev'>이전</a>\n";
		}

		for (int i = 1 + prev; i < next && i <= total; i++) {
			if (i == currentPage) {
				moveStr += "<strong class='on' style='margin-right:5px;'>"+i+"</strong>";
			} else {
				moveStr += "<a href='javascript:paging(" + i + ");'><em>" + i + "</em></a>\n";
			}
		}

		if (total > currentPage) {
			long page = currentPage + 1;
			moveStr += "<a href='javascript:paging(" + page + ");' class='next'>다음</a>\n";
			moveStr += "<a href='javascript:paging(" + total + ");' class='nextEnd'>끝</a>\n";
		} else {
			moveStr += "<a href='javascript:paging(" + total + ");' class='next'>다음</a>\n";
			moveStr += "<a href='javascript:paging(" + total + ");' class='nextEnd'>끝</a>\n";
		}

		moveStr += "</div> \n";
		moveStr += "</div>";
		paging = moveStr;

		return paging;
	}
}
