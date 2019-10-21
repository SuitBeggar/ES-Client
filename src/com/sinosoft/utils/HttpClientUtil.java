package com.sinosoft.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * HttpClient������
 */
public class HttpClientUtil {

	private static RequestConfig requestConfig = null;

	static {
		// ��������ʹ��䳬ʱʱ��
		requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
	}

	/**
	 * post������json����
	 * 
	 * @param url
	 *            url��ַ
	 * @param json
	 *            ����
	 * @return
	 */
	public static JSONObject httpPost(String url, Object requestParam) {
		// post���󷵻ؽ��
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		// ��������ʹ��䳬ʱʱ��
		httpPost.setConfig(requestConfig);
		try {
			if (null != requestParam) {
				JSONObject jsonRequestParam = (JSONObject) JSON.toJSON(requestParam);
				// ���������������
				StringEntity entity = new StringEntity(jsonRequestParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// �����ͳɹ������õ���Ӧ
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// ��ȡ���������ع�����json�ַ�������
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// ��json�ַ���ת����json����
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}

	/**
	 * post������String���� ���磺name=Jack&sex=1&type=2
	 * Content-type:application/x-www-form-urlencoded
	 * 
	 * @param url
	 *            url��ַ
	 * @param strParam
	 *            ����
	 * @return
	 */
	public static JSONObject httpPost(String url, String strParam) {
		// post���󷵻ؽ��
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		try {
			if (null != strParam) {
				// ���������������
				StringEntity entity = new StringEntity(strParam, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/x-www-form-urlencoded");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// �����ͳɹ������õ���Ӧ
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// ��ȡ���������ع�����json�ַ�������
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// ��json�ַ���ת����json����
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}

	/**
	 * ����get����
	 * 
	 * @param url
	 *            ·��
	 * @return
	 */
	public static JSONObject httpGet(String url) {
		// get���󷵻ؽ��
		JSONObject jsonResult = null;
		CloseableHttpClient client = HttpClients.createDefault();
		// ����get����
		HttpGet request = new HttpGet(url);
		request.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = client.execute(request);

			// �����ͳɹ������õ���Ӧ
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// ��ȡ���������ع�����json�ַ�������
				HttpEntity entity = response.getEntity();
				String strResult = EntityUtils.toString(entity, "utf-8");
				// ��json�ַ���ת����json����
				jsonResult = JSONObject.parseObject(strResult);
			} else {
				System.out.println("get�����ύʧ��:" + url);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
		return jsonResult;
	}
}
