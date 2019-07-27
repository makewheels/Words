package util.ocr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import util.ocr.bean.JsonOcr;
import util.ocr.bean.Lines;

public class Ocr {
	public static void main(String[] args) {
		for (int i = 0; i < 7; i++) {
			int page = 382 + i;
			fromJsonToWordsFile("ocr/json/" + page + ".json", "ocr/txt" + page + ".txt");
		}
	}

	private static void fromJsonToWordsFile(String sourceFile, String targetFile) {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			String line;
			bufferedReader = new BufferedReader(new FileReader(sourceFile));
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
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
		JsonOcr jsonOcr = new Gson().fromJson(stringBuilder.toString(), JsonOcr.class);

		List<Lines> lines = jsonOcr.getLines();
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(targetFile));
			for (Lines line : lines) {
				String words = line.getWords().replace("•", "");
				bufferedWriter.write(words);
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
	}
}
