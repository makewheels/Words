package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 单词工具类
 * 
 * @author Adminis
 *
 */
public class WordsUtil {

	/**
	 * 恋练有词：根据单元加载单词
	 * 
	 * @param unitNumber
	 * @return
	 */
	public static List<String> loadWordsByUnit(int unitNumber) {
		String filePath = Constants.rootFolderPath + "\\source\\unit" + unitNumber + ".txt";
		List<String> list = new ArrayList<>();
		BufferedReader bufferedReader = null;
		try {
			String line;
			bufferedReader = new BufferedReader(new FileReader(filePath));
			while ((line = bufferedReader.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 根据文件导入单词
	 * 
	 * @param filepath
	 */
	public static List<String> loadWordsByFile(String filepath) {
		String filePath = Constants.rootFolderPath + filepath;
		List<String> list = new ArrayList<>();
		BufferedReader bufferedReader = null;
		try {
			String line;
			bufferedReader = new BufferedReader(new FileReader(filePath));
			while ((line = bufferedReader.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 把指定文件夹中所有单词文件全部导入
	 * 
	 * @param folderpath
	 * @return
	 */
	public static List<String> loadWordsByFolder(String folderpath) {
		File folder = new File(Constants.rootFolderPath + folderpath);
		File[] fileList = folder.listFiles();
		List<String> list = new ArrayList<>();
		for (File file : fileList) {
			BufferedReader bufferedReader = null;
			try {
				String line;
				bufferedReader = new BufferedReader(new FileReader(file));
				while ((line = bufferedReader.readLine()) != null) {
					list.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 将不会的单词输出至文件
	 * 
	 * @param words
	 * @param filename
	 */
	public static void outputToFile(List<String> words, String filename) {
		File file = new File(Constants.rootFolderPath + "\\unknow\\" + filename);
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(file));
			for (String word : words) {
				bufferedWriter.write(word);
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 打开记录不会的词的记事本文件
		// try {
		// Runtime.getRuntime().exec("notepad " + file);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}
}
