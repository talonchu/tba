package com.perficient.bcten.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class PageTest {
	private Page page;

	@Test
	public void testCountCurPage() {
		page = new Page();
		page.setTotalPage(7);
		page.getTotalPage();
		page.countCurPage(9);
	}

	@Test
	public void testCountTotalPageBranch1() {
		page = new Page();
		int pageSize = 8;
		int allRow = 56;
		assertEquals(7, page.countTotalPage(pageSize, allRow));
	}

	@Test
	public void testCountTotalPageBranch2() {
		page = new Page();
		int pageSize = 8;
		int allRow = 57;
		assertEquals(8, page.countTotalPage(pageSize, allRow));
	}

	@Test
	public void testGetSetAllRow() {
		page = new Page();
		page.setAllRow(1);
		assertEquals(1, page.getAllRow());
	}

	@Test
	public void testGetSetCurPage() {
		page = new Page();
		page.setCurPage(1);
		assertEquals(1, page.getCurPage());
	}

	@Test
	public void testGetSetPageSize() {
		page = new Page();
		page.setPageSize(1);
		assertEquals(1, page.getPageSize());
	}

	@Test
	public void countOffset() {
		page = new Page();
		page.setPageSize(1);
		page.setCurPage(1);
		assertEquals(0, page.countOffset(page.getPageSize(), page.getCurPage()));
		page.setPageSize(1);
		page.setCurPage(-1);
		assertEquals(1, page.countOffset(page.getPageSize(), page.getCurPage()));
	}

}
