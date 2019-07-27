package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 统计个数工具类
 * 
 * @author Administrator
 *
 */
public class CountUtil {

	public static void main(String[] args) {
		int total = 0;
		for (int i = 1; i <= 20; i++) {
			int countUnit = CountUtil.countUnit(i);
			System.out.println("unit" + i + ":" + countUnit);
			total += countUnit;
		}
		System.out.println("总共：" + total);
	}

	/**
	 * 统计指定单元有多少词
	 * 
	 * @param unit
	 * @return
	 */
	public static int countUnit(int unit) {
		String filePath = Constants.rootFolderPath + "\\source\\unit" + unit + ".txt";
		BufferedReader bufferedReader = null;
		int count = 0;
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			while (bufferedReader.readLine() != null) {
				count++;
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
		return count;
	}
}
