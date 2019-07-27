package util;

/**
 * 要往Excel里写的每一行
 * 
 * @author Administrator
 *
 */
public class ExcelBean {
	private String word;
	private String explainPeople;
	private String explainHtml;
	private int lineCount;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getExplainPeople() {
		return explainPeople;
	}

	public void setExplainPeople(String explainPeople) {
		this.explainPeople = explainPeople;
	}

	public String getExplainHtml() {
		return explainHtml;
	}

	public void setExplainHtml(String explainHtml) {
		this.explainHtml = explainHtml;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	@Override
	public String toString() {
		return "ExcelBean [word=" + word + ", explainPeople=" + explainPeople + ", explainHtml=" + explainHtml
				+ ", lineCount=" + lineCount + "]";
	}

}
