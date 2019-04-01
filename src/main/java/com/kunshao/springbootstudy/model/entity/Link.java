package com.kunshao.springbootstudy.model.entity;

import org.springframework.beans.BeanUtils;

/**
 * 抓取网站的种子地址
 */
public class Link implements Cloneable {

	/**
	 * 目标地址
	 */
	private String url;

	private String domain;

	private String text;

	/**
	 * 目标网站名称
	 */
	private String site;

	private String areaCode;

	/**
	 * 限制的路径
	 */
	private String path;

	/**
	 * 抓取深度
	 */
	private int depth;

	private int maxDepth;
	
	private String includes;
	
	private String excludes;
	
	private boolean isApi;

	private String keyWord;

	private int currentPage;
	
	private String rules;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}

	public String getDomain() {

		return domain;
	}

	public void setDomain(String domain) {

		this.domain = domain;
	}

	public String getText() {

		return text;
	}

	public void setText(String text) {

		this.text = text;
	}

	public String getSite() {

		return site;
	}

	public void setSite(String site) {

		this.site = site;
	}

	public String getAreaCode() {

		return areaCode;
	}

	public void setAreaCode(String areaCode) {

		this.areaCode = areaCode;
	}

	public String getPath() {

		return path;
	}

	public void setPath(String path) {

		this.path = path;
	}

	public int getDepth() {

		return depth;
	}

	public void setDepth(int depth) {

		this.depth = depth;
	}

	public int getMaxDepth() {

		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {

		this.maxDepth = maxDepth;
	}

	public Link clone() {

		try {
			return (Link) super.clone();
		} catch(CloneNotSupportedException e) {
			Link link = new Link();
			BeanUtils.copyProperties(this, link);
			return link;
		}
	}

	public String getIncludes() {

		return includes;
	}

	public void setIncludes(String includes) {

		this.includes = includes;
	}

	public String getExcludes() {

		return excludes;
	}

	public void setExcludes(String excludes) {

		this.excludes = excludes;
	}

	public boolean isApi() {

		return isApi;
	}

	public void setApi(boolean isApi) {

		this.isApi = isApi;
	}

	public String getRules() {

		return rules;
	}

	public void setRules(String rules) {

		this.rules = rules;
	}
}
