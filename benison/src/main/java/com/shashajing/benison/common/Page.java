package com.shashajing.benison.common;

import java.util.ArrayList;
import java.util.List;
/**
 * 分页对象
 * @author yanghanjing
 */
public class Page {
	private long start;//起始下标
	private long total;//数据总数
	private long pageNum = 10L;//每页显示数量
	private long pageTotal;//总页数
	private long currentPage = 1;//当前页
	private List<String> pageShowList = new ArrayList<String>();//分页栏显示的页码列表
	
	/*
	 * 获得分页栏显示的页码列表
	 */
	public List<String> getPageShowList() {
		long allPageNum = getPageTotal();
		//使得当前页前后都有一定数目的页码，情况理想的话前面4个，后面5个
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
				if (i > 0) {
					pageShowList.add(String.valueOf(i));
				}
			}
		}
		return pageShowList;
	}
	public long getStart() {
		this.start = (currentPage - 1)*pageNum;
		return start;
	}
	public void setStart(long start) {
		this.start = start;
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