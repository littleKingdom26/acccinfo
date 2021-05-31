package info.team23h.acc.util;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SearchHelper {

	/**
	 * 검색 구분
	 */
	private String searchType;

	private String regId;

	/**
	 * 검색어
	 */
	private String keyword = "";

	/**
	 * 시작번호
	 */
	private int startNum;

	/**
	 * 끝번호
	 */
	private int endNum;

	/**
	 * 데이터 총 개수
	 */
	private int totalCount;

	/**
	 * 1페이지의 데이터 수
	 */
	private int pageCount = 15;

	/**
	 * 총 페이지 수
	 */
	private int totalPage;

	/**
	 * 현재 페이지 값
	 */
	private int currentPage = 1;

	public int getStartNum() {
		return getCurrentPage() > 1 ? (getCurrentPage() - 1) * getPageCount() : 0;
	}

	public int getEndNum() {
		return this.pageCount;
	}

	public int getTotalPage() {
		return getPageCount() > 0 ? (getTotalCount() / getPageCount()) + (getTotalCount()-(getPageCount()*(getTotalCount() / getPageCount())) > 0 ? 1 : 0) : 0;
	}
	
}
