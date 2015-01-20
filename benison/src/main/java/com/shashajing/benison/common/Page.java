package com.shashajing.benison.common;



/**
 * 分页对象
 * @author yanghanjing
 */
public class Page {
	private Long start;
	private Long end;
	private Long total;
	private Long pageNum = 10L;
	private Long pageTotal;
	
	public Long getStart() {
		return start;
	}
	public Long getStartNum() {
		if (null == start) {
			return 0L;
		}else {
			return (start-1)*pageNum;
		}
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getEnd() {
		return end;
	}
	public void setEnd(Long end) {
		this.end = end;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getPageNum() {
		return pageNum;
	}
	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}
	public Long getPageTotal() {
		if (total != null) {
			pageTotal = total/pageNum;
			if (total % pageNum > 0) {
				pageTotal += 1;
			}
		} else {
			pageTotal = 0L;
		}
		return pageTotal;
	}
	public void setPageTotal(Long pageTotal) {
		this.pageTotal = pageTotal;
	}
}