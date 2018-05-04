package com.perficient.bcten.util;

public class Page {
	private int pageSize = Integer.parseInt("8");
	private int curPage;
	private int allRow;
	private int totalPage;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getAllRow() {
		return allRow;
	}

	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int countTotalPage(final int pageSize, final int allRow) {
		return (allRow % pageSize == 0 ? allRow / pageSize : allRow / pageSize + 1);
	}

	public int countOffset(final int pageSize, final int curPage) {
		return pageSize * (curPage <= 0 ? 1 : curPage - 1);
	}

	public void countCurPage(int page) {
		curPage = (page <= 0 ? 1 : page);
		curPage = (curPage >= totalPage ? totalPage : curPage);
	}
}
