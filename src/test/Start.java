package test;

import util.WebUtil;

public class Start {
	public static void main(String[] args) {
		String sendGet = WebUtil.sendGet("https://www.qqzeng.com/ip/json");
		System.out.println(sendGet);
	}
}
