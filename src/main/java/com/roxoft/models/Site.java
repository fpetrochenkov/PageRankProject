package com.roxoft.models;

import java.util.List;

public class Site {

	public Site() {
	}

	private int id;
	private String url;
	private double pageRank;
	private String html;
	private List<Integer> linksOut;
	private List<String> linksOutStr;

	public Site(int id, String url, double pageRank, String html, List<Integer> linksOut) {
		this.id = id;
		this.url = url;
		this.pageRank = pageRank;
		this.html = html;
		this.linksOut = linksOut;
	}

	public List<String> getLinksOutStr() {
		return linksOutStr;
	}

	public void setLinksOutStr(List<String> linksOutStr) {
		this.linksOutStr = linksOutStr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public double getPageRank() {
		return pageRank;
	}

	public void setPageRank(double pageRank) {
		this.pageRank = pageRank;
	}

	public List<Integer> getLinksOut() {
		return linksOut;
	}

	public void setLinksOut(List<Integer> linksOut) {
		this.linksOut = linksOut;
	}

	public String toString() {
		return "\n" + id + ". " + url + "; Rank: " + pageRank + ";";
	}

}
