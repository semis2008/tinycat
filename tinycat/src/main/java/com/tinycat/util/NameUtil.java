package com.tinycat.util;

import java.util.Random;

public class NameUtil {
	/**
	 * 产生随机姓名,3字，姓使用百家姓，越靠后概率越低，名依据性别选取
	 * 
	 * @autor: wn 2014-8-15 上午11:36:16
	 * @param gender
	 * @return
	 */
	public static String getRandomName(boolean boy) {
		int girl_size = NameConstants.GIRL_NAME.length;
		int boy_size = NameConstants.BOY_NAME.length;
		int index = 0;
		String name = "";
		if(boy) {
			index = randomInt(0, boy_size-1);
			name = NameConstants.BOY_NAME[index];
		}else{
			index = randomInt(0, girl_size-1);
			name = NameConstants.GIRL_NAME[index];
		}
		return getRandomXing()+name;
	}

	/**
	 * 获取随机姓氏，从前向后概率依次降低
	 * 
	 * @autor: wn 2014-8-15 下午12:13:39
	 * @return
	 */
	private static String getRandomXing() {
		int totalCount = NameConstants.XING.length;
		int index = randomInt(0, totalCount);
		if (index>totalCount/5)
			index = randomInt(0, totalCount);
		if (index>totalCount/4)
			index = randomInt(0, totalCount);
		if (index>totalCount/3)
			index = randomInt(0, totalCount);
		if (index>totalCount/2)
			index = randomInt(0, totalCount);
		
		if(index==totalCount)
			index--;
		return NameConstants.XING[index];
	}
	
	private static int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}
	
	
	public static void main(String args[]) {
		int i=20;
		while(i>0) {
			String name = getRandomName(false);
			System.out.println(name);
			i--;
		}
		
	}
}