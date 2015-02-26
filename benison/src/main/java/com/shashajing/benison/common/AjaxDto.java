package com.shashajing.benison.common;

import java.util.List;
/**
 * 
 * @author yanghanjing
 */
public class AjaxDto<T> {
	private long draw;//
	private long sEcho;//
	private long recordsTotal;//
	private long recordsFiltered;//
	
	private List<T> data;

	public long getDraw() {
		return draw;
	}

	public void setDraw(long draw) {
		this.draw = draw;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getsEcho() {
		return sEcho;
	}

	public void setsEcho(long sEcho) {
		this.sEcho = sEcho;
	}
}