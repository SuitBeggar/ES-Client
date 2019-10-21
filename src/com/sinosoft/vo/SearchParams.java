package com.sinosoft.vo;

import java.util.List;
import java.util.Map;

/**
 * ²éÑ¯²ÎÊý vo
 * @author 71013273
 *
 */
public class SearchParams {
	private String indexName;
	private String indexType;
	private Map<String, List<Map<String, Object>>> searchType;
	public SearchParams(String indexName, String indexType) {
		
	}
	
	public void addTerm(String name, String value) {
		
	}
}
