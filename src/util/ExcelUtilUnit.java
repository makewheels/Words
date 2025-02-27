package util;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import queryword.jinshan.Jinshan;
import queryword.result.QueryResult;

/**
 * Excel表格工具类：单元词汇<br/>
 * 第一列：单词<br/>
 * 第二列：人看的意思<br/>
 * 第三列：Html意思<br/>
 * 
 * @author Administrator
 *
 */
public class ExcelUtilUnit {

	public static void main(String[] args) {
		long startMillis = System.currentTimeMillis();
		// 参数配置
		int start = 1;
		int end = 30;
		boolean random = true;
		List<String> sourceWordList = new ArrayList<>();
		for (int i = start; i <= end; i++) {
			// 文件名
			String filename = i + "";
			// 为了每个单元一个文件，首先清空list
			sourceWordList.clear();
			// 再把一个添加到list中
			sourceWordList.addAll(WordsUtil.loadWordsByUnit(i));
			String filepath = Constants.rootFolderPath + "\\excel\\sequence\\people\\unit" + filename + ".xls";
			if (random) {
				filepath = Constants.rootFolderPath + "\\excel\\random\\people\\unit" + filename + ".xls";
			}
			outputExcelPeople(sourceWordList, filename, filepath, random);
		}
		System.out.println("运行耗时：" + (System.currentTimeMillis() - startMillis) + " millis");
	}

	/**
	 * 输出单词至excel文件<br/>
	 * 格式1：人看的意思
	 * 
	 * @param sourceWordList源词
	 * @param filename文件名
	 * @param filepath文件保存的绝对路径
	 * @param random顺序是否随机
	 */
	@SuppressWarnings("deprecation")
	public static void outputExcelPeople(List<String> sourceWordList, String filename, String filepath,
			boolean random) {
		// Excel单词表
		List<ExcelBean> excelWordList = new ArrayList<>();
		// 随机
		if (random) {
			Collections.shuffle(sourceWordList);
		}
		// 遍历每一个源词
		for (String sourceWord : sourceWordList) {
			// Excel对象
			ExcelBean excelBean = new ExcelBean();
			// 设置单词
			excelBean.setWord(sourceWord);
			// 查词
			QueryResult queryResult = Jinshan.query(sourceWord);
			System.out.println(sourceWord);
			System.out.println(queryResult);
			StringBuilder stringBuilder = new StringBuilder();
			// 意思列表
			List<String> explainList = queryResult.getExplainList();
			for (int i = 0; i < explainList.size(); i++) {
				String enter = "";
				if (i != explainList.size() - 1) {
					enter = "\r\n";
					excelBean.setLineCount(excelBean.getLineCount() + 1);
				}
				stringBuilder.append((new HSSFRichTextString(explainList.get(i) + enter)));
			}
			// 设置意思
			excelBean.setExplainPeople(stringBuilder.toString());
			// 设置音标
			excelBean.setSound(queryResult.getSound());
			excelWordList.add(excelBean);
		}
		String fontName = "微软雅黑";
		short fontSize = 11 * 20;
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		sheet.setHorizontallyCenter(true);
		sheet.setColumnWidth(0, 4500);
		sheet.setColumnWidth(1, 4500);
		sheet.setColumnWidth(2, 14000);
		HSSFHeader header = sheet.getHeader();
		String randomHeader = "";
		if (random) {
			randomHeader = "  乱序";
		}
		header.setCenter("unit " + filename + randomHeader);
		HSSFFooter footer = sheet.getFooter();
		footer.setLeft(
				"生成时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		footer.setCenter("总数：" + sourceWordList.size());
		footer.setRight("第 " + HSSFFooter.page() + " 页, 共 " + HSSFFooter.numPages() + " 页");
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontName(fontName);
		font.setFontHeight(fontSize);
		cellStyle.setFont(font);
		cellStyle.setWrapText(true);
		cellStyle.setBorderTop(BorderStyle.DOTTED);
		cellStyle.setBorderBottom(BorderStyle.DOTTED);
		cellStyle.setBorderLeft(BorderStyle.DOTTED);
		cellStyle.setBorderRight(BorderStyle.DOTTED);
		cellStyle.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
		cellStyle.setBottomBorderColor(HSSFColor.GREY_50_PERCENT.index);
		cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		for (int i = 0; i < excelWordList.size(); i++) {
			ExcelBean excelBean = excelWordList.get(i);
			String word = excelBean.getWord();
			String explainPeople = excelBean.getExplainPeople();
			String sound = excelBean.getSound();
			HSSFRow row = sheet.createRow(i);

			HSSFCell cell = row.createCell(0);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(word);

			cell = row.createCell(1);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(sound);

			cell = row.createCell(2);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(explainPeople);
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filepath);
			workbook.write(fileOutputStream);
			fileOutputStream.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
