package com.tinycat.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * 日期工具类
 * 
 * @author Kalor
 * @time 2012-12-17
 */
public class DateUtil {
	/**
	 * 获取当前的时间
	 * 
	 * @return Calendar
	 */
	public static Calendar getCalendarNow() {
		Calendar cal = Calendar.getInstance();
		return cal;
	}

	/**
	 * 获取当前的时间
	 * 
	 * @return String 2013-06-07 11:45
	 */
	public static String getTimeNow() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return f.format(cal.getTime());
	}

	/**
	 * 将date对象转换为calendar
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date == null) {
			return null;
		} else {
			cal.setTime(date);
		}
		return cal;
	}

	/**
	 * 获取当前时间几天之后的日期
	 * 
	 * @param dat
	 *            数目
	 * @param format
	 *            格式
	 * @return
	 * @throws ParseException
	 */
	public static String DateAfterStr(int dat, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		Calendar calen = Calendar.getInstance();
		calen.add(Calendar.DAY_OF_YEAR, dat);
		Date c = calen.getTime();
		return f.format(c);
	}

	/**
	 * 格式化指定的日期
	 * 
	 * @param date
	 *            日期
	 * @param type
	 *            1：格式：2011-11-11 ； 2：：2011/2/2 ； 3：2011/2/2 11:45 4: 12/21 5:2012-11-11 23:22:03
	 * @return
	 */
	public static String formatDate(Date date, int type) {
		if (date == null)
			return "";
	  	String pattern = "";
		switch (type) {
		case 1:
			pattern = "yyyy-MM-dd";
			break;
		case 2:
			pattern = "yyyy/MM/dd";
			break;
		case 3:
			pattern = "yyyy/MM/dd HH:mm";
			break;
		case 4:
			pattern = "MM/dd";
			break;
		case 5:
			pattern = "yyyy-MM-dd HH:mm:ss";
			break;
		default:
			pattern = "yyyy/MM/dd HH:mm:ss";
			break;
		}
		
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String res = format.format(date);
		return res;
	}

	/**
	 * 获取所给时间到目前为止的友好的显示信息 一小时之内：XX分钟；24小时之内：XX小时；其他： X天前
	 * 
	 * @param date
	 *            所给时间
	 * @return 友好的显示信息
	 */
	public static String getPassedTime(Date date) {
		if (date == null)
			return "";
		Calendar cal = dateToCalendar(date);
		// TODO 添加主要逻辑
		int publishYear = cal.get(Calendar.YEAR);
		int publishMonth = cal.get(Calendar.MONTH) + 1;
		int publishDay = cal.get(Calendar.DAY_OF_MONTH);
		int publishHour = cal.get(Calendar.HOUR_OF_DAY);
		int publishMin = cal.get(Calendar.MINUTE);

		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")); // 获取东八区时间
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);

		/*
		 * 1小时内返回 ‘XXX分钟’
		 */
		if (publishMonth == month && publishDay == day && publishHour == hour) {
			return min - publishMin + "分钟前";
		}

		/*
		 * 24小时之内返回 ‘XXX小时’
		 */
		if (publishMonth == month
				&& (publishDay == day || publishDay == day - 1
						&& hour < publishHour)) {
			if (publishDay == day)
				return hour - publishHour + "小时前";
			if (publishDay == day - 1)
				return 24 - (publishHour - hour) + "小时前";
		}
		return dateDiff(date, c.getTime());
	}

	/**
	 * 获取两个日期的时间差 返回友好的提示
	 * 
	 * @param endDate
	 * @param startDate
	 * @return
	 */
	public static String dateDiff(Date endDate, Date startDate) {
		return dateDiff(formatDate(endDate, 2), formatDate(startDate, 2),
				"yyyy/MM/dd");
	}

	/**
	 * 获取两个日期的时间差 返回友好的提示
	 * 
	 * @param endTime
	 * @param startTime
	 * @param format
	 * @return
	 */
	public static String dateDiff(String endTime, String startTime,
			String format) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long diff;
		long day = 0l;
		try {
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			day = diff / nd;// 计算差多少天
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (day == 0l) {
			return "今天";
		} else if (day == 1l) {
			return "明天";
		} else if (day == -1l) {
			return "昨天";
		} else {
			if (day > 0) {
				return day + "天后";
			} else {
				return -day + "天前";
			}
		}
	}

	public static void main(String args[]) throws ParseException {
		Date d = new SimpleDateFormat("yyyy/MM/dd").parse("2013/11/11");
		System.out.print(getPassedTime(d));
	}
}
