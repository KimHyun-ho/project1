package kr.human.anihospital.vo;

import java.util.List;

// 어떤 데이터든 페이지 나누는 방법은 동일하다. 그러므로 제네릭으로 만들자.
public class PagingVO<T> {
	private List<T> list; // 페이지 별 데이터가 담길 리스트
	private int totalCnt; // DB에서 넘겨받을 전체 글수
	private int currentPage; // 현재 페이지 : 생성자에서 전달받자
	private int pageSize; // 페이지 당 글수
	private int blockSize; // 하단 페이지 개수 : 생성자에서 전달받자

	// 계산이 필요한 값들
	private int totalPage; // 전체 페이지 수
	private int startNo; // 각 페이지의 글 시작 번호
	private int endNo; // 각 페이지의 글 끝 번호
	private int startPage; // 하단 페이지 색인의 시작 번호
	private int endPage; // 하단 페이지 색인의 끝 번호

	public PagingVO(int totalCnt, int currentPage, int pageSize, int blockSize) {
		super();
		this.totalCnt = totalCnt;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.blockSize = blockSize;
		calc();
	}

	private void calc() {
		if (totalCnt <= 0) {
			totalCnt = 0;
		}
		// 넘어온 값의 유효성을 검사해주자.
		// 전체 개수가 0보다 작을 수는 없다.
		// 현재페이지 번호는 1페이지보다 적을 수 없다.
		if (currentPage < 1)
			currentPage = 1;
		// 페이지 당 글수는 최소 1개 이상이어야 한다.
		if (pageSize < 1)
			pageSize = 10;
		// 하단의 페이지 수는 최소 2개 이상은 되어야한다.
		if (blockSize < 2)
			blockSize = 10;
		if (totalCnt > 0) {
			// 5개의 변수값을 계산해준다.
			// 전체 페이지 수 = (전체글 수-1)/페이지 당 글 수 + 1
			totalPage = (totalCnt - 1) / pageSize + 1;
			// 현재 페이지 번호는 전체 페이지를 넘을 수 없다.
			if (currentPage > totalPage)
				currentPage = 1;
			// 각 페이지의 글 시작 번호 : (현재 페이지-1) * 페이지 당 글 수 +1
			startNo = (currentPage - 1) * pageSize; // MariaDB는 0부터 수를 세므로 -1을 해주어야한다.
													// startNo = (currentPage - 1) * pageSize;
			// 각 페이지의 글 끝 번호 : 각 페이지의 글 시작 번호 + 페이지 당 글 수 -1
			endNo = startNo + pageSize - 1;
			// 각 페이지의 마지막 번호는 전체 글 개수를 넘을 수 없다.
			if (endNo > totalCnt)
				endNo = totalCnt - 1; // MariaDB는 0부터 수를 세므로 -1을 해주어야한다. endNo = totalCnt - 1;
			// 하단 페이지 색인의 시작 번호 : (현재 페이지-1) / 하단 페이지 개수 * 하단 페이지 개수 + 1
			startPage = (currentPage - 1) / blockSize * blockSize + 1;
			// 하단 페이지 색인의 끝 번호 : 하단 페이지 색인의 시작 번호 + 하단 페이지 개수 - 1
			endPage = startPage + blockSize - 1;
			// 마지막 페이지가 전체 페이지 수 보다 클 수 없다.
			if (endPage > totalPage)
				endPage = totalPage;
		}

	}

	// List를 제외한 필드들은 값을 get하여 계산하고 그 값을 구하지만 set해줄 필요가 없다.
	// 그러므로 List만 getter setter를 전부 생성해주고 나머지 필드는 getter만 생성해준다.
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getStartNo() {
		return startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	@Override
	public String toString() {
		return "PagingVO [list=" + list + ", totalCnt=" + totalCnt + ", currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", blockSize=" + blockSize + ", totalPage=" + totalPage + ", startNo=" + startNo
				+ ", endNo=" + endNo + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}

	// 페이지 상단에 출력할 글 정보를 리턴하는 메소드를 생성하자.
	public String getInfo() {
		String result = "Total : " + totalCnt;
		if (totalCnt > 0) {
			result += " (" + currentPage + " / " + totalPage + " 페이지)";
		}
		return result;
	}

	// 페이지 하단에 페이지 이동을 출력할 정보를 리턴하는 메소드
	public String getPageList() {
		String result = "";
		// 글이 존재할때만 페이지 정보가 있다.
		if (totalCnt > 0) {
			// 하단 페이지 색인의 시작 번호가 1보다 크다면 [이전]이 있다.
			if (startPage > 1) {
				result += "<li class='page-item'><a class='page-link' href='?c=" + (startPage - 1) + "&p=" + pageSize
						+ "&b=" + blockSize
						+ "' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>";
			}
			// 페이지 번호 출력
			for (int i = startPage; i <= endPage; i++) {
				if (i == currentPage) {
					result += "<li class='page-item'><a class='page-link' href='#'>" + i + "</a></li>";
				} else {
					result += "<li class='page-item'><a class='page-link' href='?c=" + i + "&p=" + pageSize + "&b="
							+ blockSize + "'>" + i + "</a></li>";
				}
			}

			// 하단 페이지 색인의 끝 번호가 전체페이지 번호보다 적다면 [다음]이 있다.
			if (endPage < totalPage) {
				result += "<li class='page-item'><a class='page-link' href='?c=" + (endPage + 1) + "&p=" + pageSize
						+ "&b=" + blockSize + "' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>";
			}
		}
		return result;
	}

}
