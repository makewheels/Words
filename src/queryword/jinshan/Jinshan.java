package queryword.jinshan;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import queryword.jinshan.bean.JsonResult;
import queryword.jinshan.bean.Parts;
import queryword.result.QueryResult;
import queryword.youdao.Youdao;
import util.WebUtil;

/**
 * 金山查词
 * 
 * @author Administrator
 *
 */
public class Jinshan {

	public static QueryResult query(String word) {
		// 最终要返回的查询结果
		QueryResult queryResult = new QueryResult();
		String url = "http://dict-co.iciba.com/api/dictionary.php?w=" + word
				+ "&key=50C9AE4DA77048E57306F32931484BB5&type=json";
		// 发起查询
		String json = WebUtil.sendGet(url);
		// 获得解释
		JsonResult jsonResult = new Gson().fromJson(json, JsonResult.class);
		List<String> explain = new ArrayList<>();
		List<Parts> parts = null;
		try {
			parts = jsonResult.getSymbols().get(0).getParts();
			for (Parts part : parts) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(part.getPart());
				List<String> means = part.getMeans();
				for (String mean : means) {
					stringBuilder.append(mean + "，");
				}
				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
				stringBuilder.append("；");
				explain.add(stringBuilder.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("金山gg！！！！word=" + word);
			try {
				explain = Youdao.getExplains(word);
				System.out.println("有道成功了word=" + word);
				System.out.println(explain);
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("有道也gg了word=" + word);
			}
		}
		// 设置解释
		queryResult.setExplainList(explain);
		// 获得音标
		String ph_am = jsonResult.getSymbols().get(0).getPh_am();
		// 设置音标
		queryResult.setSound(ph_am);
		// 返回查询结果
		return queryResult;
	}
}
