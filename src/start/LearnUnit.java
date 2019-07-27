package start;

import java.util.ArrayList;
import java.util.List;

import frame.MainFrame;
import util.WordsUtil;

public class LearnUnit {
	public static void main(String[] args) {
		// 单元表达式范例：
		// 只有第一单元：1
		// 从第一单元到第五单元：1-5
		String unitString = "6";
		List<String> importList = new ArrayList<>();
		if (unitString.contains("-")) {
			String[] split = unitString.split("-");
			int start = Integer.parseInt(split[0]);
			int end = Integer.parseInt(split[1]);
			for (int i = start; i <= end; i++) {
				importList.addAll(WordsUtil.loadWordsByUnit(i));
			}
		} else {
			importList = WordsUtil.loadWordsByUnit(Integer.parseInt(unitString));
		}
		new MainFrame(importList, true, unitString);
	}
}
