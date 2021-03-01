package com.lion.utility.http.rpchttp.entity;

public class Item {
	private Integer item_pk;
	private String title;

	public Integer getItem_pk() {
		return item_pk;
	}

	public void setItem_pk(Integer item_pk) {
		this.item_pk = item_pk;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Item [item_pk=" + item_pk + ", title=" + title + "]";
	}

}
