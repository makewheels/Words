package queryword.result;

import java.util.List;

/**
 * 使用api接口查单词的结果
 * 
 * @author Administrator
 *
 */
public class QueryResult {
	// 解释列表
	private List<String> explainList;
	// 音标
	private String sound;

	public List<String> getExplainList() {
		return explainList;
	}

	public void setExplainList(List<String> explainList) {
		this.explainList = explainList;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	@Override
	public String toString() {
		return "QueryResult [explainList=" + explainList + ", sound=" + sound + "]";
	}

}
