package com.sinosoft.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.utils.HttpClientUtil;
import com.sinosoft.vo.SearchParams;
import com.sinosoft.vo.SearchResult;

/**
 * 调用服务端使用的客户端jar包
 * @author xubincheng
 *
 */
public class EsClient {
	
	public static SearchResult query(String indexName, String indexType, SearchParams searchParams) {
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("indexName", "nxyw_index");
		requestMap.put("indexType", "case_index");
		requestMap.put("from", "0");
		requestMap.put("size", "10");
		return null;
	}
	public static void main(String[] args) {
		
		HashMap<String, HashMap<String, List<String>>> map = new HashMap<>();
		HashMap<String, List<String>> a = new HashMap<>();
		List<String> lista = new ArrayList<String>();
		lista.add("190045");
		a.put("REGISTNO", lista);
		map.put("wildcard", a);

		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("map", map);
		requestMap.put("indexName", "nxyw_index");
		requestMap.put("indexType", "case_index");
		requestMap.put("from", "0");
		requestMap.put("size", "10");
		String url = "http:///searchData/searchFromEsServer";
	
		JSONObject json = (JSONObject) JSON.toJSON(requestMap);
		System.out.println(HttpClientUtil.httpPost(url, json));
	}
}
