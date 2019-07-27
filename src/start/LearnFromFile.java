package start;

import java.util.ArrayList;
import java.util.List;

import frame.MainFrame;
import util.WordsUtil;

public class LearnFromFile {
	public static void main(String[] args) {
		List<String> importList = new ArrayList<>();
		importList = WordsUtil.loadWordsByFile("\\unknow\\unit6 2018-02-10 19-31-50.txt");
		new MainFrame(importList, false, null);
	}
}
