package queryword.youdao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import youdao.bean.JsonResult;

public class Youdao {

	public static List<String> getExplains(String word) {
		String jsonString = Youdao.query(word).replace("-", "_");
		JsonResult jsonResult = new Gson().fromJson(jsonString, JsonResult.class);
		if (jsonResult.getBasic() == null) {
			return jsonResult.getTranslation();
		} else {
			return jsonResult.getBasic().getExplains();
		}
	}

	private static String query(String queryWord) {
		String appKey = "1a0c303b75b136b6";
		String query = queryWord;
		String salt = String.valueOf(System.currentTimeMillis());
		String from = "EN";
		String to = "zh-CHS";
		String sign = md5(appKey + query + salt + "yQc2J7yQgcqptxkcmjPa8dkrRHE3GrrB");
		Map<String, String> params = new HashMap<>();
		params.put("q", query);
		params.put("from", from);
		params.put("to", to);
		params.put("sign", sign);
		params.put("salt", salt);
		params.put("appKey", appKey);
		String result = null;
		try {
			result = requestForHttp("http://openapi.youdao.com/api", params);
		} catch (Exception e) {
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("all")
	private static String requestForHttp(String url, Map<String, String> requestParams) throws Exception {
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		List params = new ArrayList();
		Iterator it = requestParams.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> en = (Entry<String, String>) it.next();
			String key = en.getKey();
			String value = en.getValue();
			if (value != null) {
				params.add(new BasicNameValuePair(key, value));
			}
		}
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		try {
			HttpEntity httpEntity = httpResponse.getEntity();
			result = EntityUtils.toString(httpEntity, "utf-8");
			EntityUtils.consume(httpEntity);
		} finally {
			try {
				if (httpResponse != null) {
					httpResponse.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 生成32位MD5摘要
	 * 
	 * @param string
	 * @return
	 */
	private static String md5(String string) {
		if (string == null) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = string.getBytes("utf-8");
			/** 获得MD5摘要算法的 MessageDigest 对象 */
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			/** 使用指定的字节更新摘要 */
			mdInst.update(btInput);
			/** 获得密文 */
			byte[] md = mdInst.digest();
			/** 把密文转换成十六进制的字符串形式 */
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (byte byte0 : md) {
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			return null;
		}
	}

}
