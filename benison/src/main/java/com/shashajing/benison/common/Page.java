package com.shashajing.benison.common;

import java.util.ArrayList;
import java.util.List;



/**
 * 分页对象
 * @author yanghanjing
 */
public class Page {
	private long start;//起始下标
	private long end;//终止下标
	private long total;//数据总数
	private long pageNum = 4L;//每页显示数量
	private long pageTotal;//总页数
	private long currentPage = 1;//当前页
	private List<String> pageShowList = new ArrayList<String>();
	
	public List<String> getPageShowList() {
		long allPageNum = getPageTotal();
		long from = currentPage - 4;
		long to = currentPage + 5;
		if (from > 0 && to <= allPageNum) {
			for (long i = from; i <= to; i++) {
				pageShowList.add(String.valueOf(i));
			}
		} else if (from <= 0) {
			for (long i = 1; i <= 10 && i <= allPageNum; i++) {
				pageShowList.add(String.valueOf(i));
			}
		} else if (from >= 0 && to > allPageNum) {
			for (long i = allPageNum - 9; i <= allPageNum; i++) {
				pageShowList.add(String.valueOf(i));
			}
		}
		
		return pageShowList;
	}
	public long getStart() {
		return (currentPage - 1)*pageNum;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getPageNum() {
		return pageNum;
	}
	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;
	}
	public long getPageTotal() {
		pageTotal = total/pageNum;
		if (total % pageNum > 0) {
			pageTotal += 1;
		}
		return pageTotal;
	}
	public void setPageTotal(long pageTotal) {
		this.pageTotal = pageTotal;
	}
	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
}