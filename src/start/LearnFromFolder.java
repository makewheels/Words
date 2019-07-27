package start;

import java.util.ArrayList;
import java.util.List;

import frame.MainFrame;
import util.WordsUtil;

public class LearnFromFolder {
	public static void main(String[] args) {
		List<String> importList = new ArrayList<>();
		importList = WordsUtil.loadWordsByFolder("\\unknow");
		new MainFrame(importList, false, null);
	}
}
