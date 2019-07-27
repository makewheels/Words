package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import queryword.youdao.Youdao;
import util.WordsUtil;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 5514815696919682515L;

	// 词义是否正在显示
	private boolean isExplainsShowing = false;
	// 是否需要记录未掌握单词
	private boolean needOutputUnknow;
	// 是否已经结束
	private boolean isEnded = false;

	private String unitString;
	private int index;
	private List<String> importList;
	private List<String> unknowList = new ArrayList<>();

	private int progressCount = 0;
	private int listSize;
	private JProgressBar progressBar = new JProgressBar();
	private Font font_word = new Font("Consolas", Font.PLAIN, 100);
	private Font font_explain = new Font("微软雅黑", Font.PLAIN, 40);
	private JLabel label_word = new JLabel();
	private JLabel label_explains = new JLabel();

	public MainFrame(List<String> importList, boolean needOutputUnknow, String unitString) {
		this.unitString = unitString;
		this.importList = importList;
		this.needOutputUnknow = needOutputUnknow;
		this.listSize = importList.size();
		init();
		addListeners();
		begin();
	}

	/**
	 * 初始化窗体
	 */
	private void init() {
		if (unitString != null) {
			this.setTitle(unitString);
		}
		this.setLayout(null);
		this.setSize(1250, 700);
		this.setLocation(420, 80);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		label_word.setFont(font_word);
		label_word.setForeground(Color.BLACK);
		label_word.setSize(1000, 100);
		label_word.setLocation(100, 110);
		this.add(label_word);

		progressBar.setSize(800, 25);
		progressBar.setLocation(100, 25);
		progressBar.setStringPainted(true);
		this.add(progressBar);

		label_explains.setFont(font_explain);
		label_explains.setForeground(Color.BLACK);
		label_explains.setVerticalAlignment(JLabel.VERTICAL);
		label_explains.setSize(1000, 350);
		label_explains.setLocation(100, 240);
		this.add(label_explains);

		this.setVisible(true);
	}

	/**
	 * 添加监听器
	 */
	private void addListeners() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (isEnded == true) {
					return;
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					know();
				} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
					// 如果没显示词义，说明是第一次按，就显示词义
					if (isExplainsShowing == false) {
						showExplains(importList.get(index));
						isExplainsShowing = true;
					} else {
						unknow();
					}
				}
				super.keyPressed(e);
			}
		});
	}

	/**
	 * 刷新Label的词
	 */
	private void refresh() {
		// 显示新的词
		label_explains.setText("");
		isExplainsShowing = false;
		index = new Random().nextInt(importList.size());
		label_word.setText(importList.get(index));
		// 更新进度条
		progressBar.setValue((int) (100 * progressCount / listSize));
		progressBar.setString(progressCount + " / " + listSize);
		progressCount++;
	}

	/**
	 * 开始单词之旅的初始化
	 */
	private void begin() {
		refresh();
	}

	/**
	 * 结束
	 */
	private void end() {
		progressBar.setValue((int) (100 * progressCount / listSize));
		progressBar.setString(progressCount + " / " + listSize);
		label_word.setText("It's over!!!");
		label_word.setForeground(Color.RED);
		label_explains.setText("");
		if (unknowList.size() > 0 && needOutputUnknow == true) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			String filename = "unit" + unitString + " " + simpleDateFormat.format(System.currentTimeMillis()) + ".txt";
			WordsUtil.outputToFile(unknowList, filename);
		}
		isEnded = true;
		if (unknowList.size() != 0) {
			JOptionPane.showMessageDialog(this, "即将开始回溯不会的，共" + unknowList.size() + "个");
			new MainFrame(unknowList, false, null);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "全对，厉害啊，兄dei！");
			this.dispose();
		}
	}

	/**
	 * 键盘按空格，表示会这个词，执行的操作
	 */
	private void know() {
		importList.remove(index);
		if (importList.size() == 0) {
			end();
			return;
		}
		refresh();
	}

	/**
	 * 键盘按数字键盘'0'，表示该词不会，执行的操作
	 */
	private void unknow() {
		unknowList.add(importList.get(index));
		importList.remove(index);
		if (importList.size() == 0) {
			end();
			return;
		}
		refresh();
	}

	/**
	 * 查询有道接口，获得词义，并显示出来
	 */
	private void showExplains(String word) {
		List<String> explains = Youdao.getExplains(word);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<html>");
		for (String explain : explains) {
			stringBuilder.append(explain);
			stringBuilder.append("<br/>");
		}
		stringBuilder.append("</html>");
		label_explains.setText(stringBuilder.toString());
		isExplainsShowing = true;
	}
}
