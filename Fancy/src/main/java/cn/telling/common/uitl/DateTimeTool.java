/**
 * 
 */
package cn.telling.common.uitl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.time.DateFormatUtils;
/**   
 * @Title: DateTimeTool.java 
 * @Package cn.com.cits.tools.common 
 * @Description: TODO(描述该文件做什么) 
 * @author version V1.0   
 */
public class DateTimeTool {
	
	private static DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	private static final long MSECONDS_OF_ONE_DAY = 60 * 60 * 1000 * 24;
	
	public static String getYearMonthDayTimeNow(){
		return dateFormatter.format(new java.util.Date());
	}
	
	/**
	 * 获取今天的时间Andy
	 * @return
	 */
	public static String getNewTime(){
		Date date=new Date();
		return dateFormatter.format(date)+" 23:59:59";
	}
	
	/**
	 * 传入的时间和当前时间比较如果相等就返回时分秒
	 * @param sdate
	 * @return
	 */
	public static String getNewDateTimes(String sdate){
		Date date=convertDate(sdate);
		if(date==null){
			date=new Date();
		}else{
			if(dateFormatter.format(date).equals(dateFormatter.format(new Date()))){//日期相等
				return getNewTime();
			}
		}
		return dateFormatter.format(date);
	}
	
	/**
	 * 获取过去的几年和未来的几年 Andy
	 * Past and future
	 * @return
	 */
	public static String getPastFutureYear(int i){
		Calendar calendar=Calendar.getInstance();
		  calendar.add(Calendar.YEAR, i);//本月+1就表示下个月
		  CalenderAddDate(i, calendar);
		  return dateFormatter.format(calendar.getTime());
	}
	
	/**
	 * 获取过去的几天和未来的几天 Andy
	 * Past and future
	 * @return
	 */
	public static String getPastFutureDate(int i){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE, i);//本月+1就表示下个月
		return dateFormatter.format(calendar.getTime());
	}

	private static void CalenderAddDate(int i, Calendar calendar) {
		if(i>0){
			  calendar.add(Calendar.DATE, -1); 
		  }else{
			  calendar.add(Calendar.DATE, 1); 
		  }
	}
	
	/**
	 * 获取过去的几个月Andy
	 * @param orgDate
	 * @return
	 */
	public static String getPastFutureMonth(int i){
		Calendar calendar=Calendar.getInstance();
		  calendar.add(Calendar.MONTH, i);//本月+1就表示下个月
		  CalenderAddDate(i, calendar);
		  return dateFormatter.format(calendar.getTime());
	}
	
	
	

	public static Date getCurrentDate() {
		return new Date();
	}
	
	

	public static String getDateString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	public static Date getStringToDate(String date)throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");
		return df.parse(date);
	}
	
	public static String getDateNoMidline(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}

	public static String getDateString(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static String getDateTimeString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	public static String getDateTimeString(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static Date parseDate(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(date);
	}

	public static Date parseDate(String date, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(date);
	}

	public static Date parseDateTime(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.parse(date);
	}

	public static Date parseDateTime(String date, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(date);
	}

	// write by steven.shi 2004-10-18
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, year);
		cl.set(Calendar.MONTH, month - 1);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		return cl.getTime();
	}

	public static Date getLastDayOfMonth(int year, int month) {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, year);
		cl.set(Calendar.MONTH, month - 1);
		cl.set(Calendar.DAY_OF_MONTH, cl
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cl.getTime();
	}
	
	/***
	 * @author Andy
	 * 获取某年某月最后一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getOnceUponLastDay(int year, int month) {
		  Calendar cal = Calendar.getInstance();
		  cal.set(Calendar.YEAR, year);
		  cal.set(Calendar.MONTH, month-1);
		  cal.set(Calendar.DAY_OF_MONTH, 1);
		  int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		  cal.set(Calendar.DAY_OF_MONTH, value);
		return dateFormatter.format(cal.getTime());
	}
	
	/***
	 * @author Andy
	 * 获取某年某月第一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getOnceUponFirstDay(int year, int month) {
		  Calendar cal = Calendar.getInstance();
		  cal.set(Calendar.YEAR, year);
		  cal.set(Calendar.MONTH, month-1);
		  cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return dateFormatter.format(cal.getTime());
	}

	/*
	 * write by steven.shi 2004-10-18 orgDate format:YYYY-MM-DD
	 */
	public static Date convertOrgDate(String orgDate) {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, Integer.parseInt(orgDate.substring(0, 4)));
		cl.set(Calendar.MONTH, Integer.parseInt(orgDate.substring(5, 7)) - 1);
		cl.set(Calendar.DAY_OF_MONTH, Integer
				.parseInt(orgDate.substring(8, 10)));
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		return cl.getTime();
	}

	/*
	 * write by steven.shi 2004-10-18 orgDate format:YYYY-MM-DD
	 */
	public static Date convertRealTimeDate(String orgDate) {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, Integer.parseInt(orgDate.substring(0, 4)));
		cl.set(Calendar.MONTH, Integer.parseInt(orgDate.substring(5, 7)) - 1);
		cl.set(Calendar.DAY_OF_MONTH, Integer
				.parseInt(orgDate.substring(8, 10)));
		return cl.getTime();
	}

	/*
	 * Get the Next Date Write by Jeffy pan 2004-10-21 Date Format:YYYY-MM-DD
	 * YYYY:M:D YYYY/M/DD
	 */
	public static String getNextDate(String date) {

		Calendar cd = Calendar.getInstance();
		StringTokenizer token = new StringTokenizer(date, "-/ :");
		if (token.hasMoreTokens()) {
			cd.set(Calendar.YEAR, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.YEAR, 1970);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MONTH, Integer.parseInt(token.nextToken()) - 1);
		} else {
			cd.set(Calendar.MONTH, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.DAY_OF_MONTH, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.HOUR_OF_DAY, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.HOUR_OF_DAY, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MINUTE, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.MINUTE, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.SECOND, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.SECOND, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MILLISECOND, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.MILLISECOND, 0);
		}

		long nextTime = cd.getTimeInMillis() + 24 * 60 * 60 * 1000;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date(nextTime));
	}
	/*
	 * Get the previous Date Write by Jeffy pan 2004-10-21 Date Format:YYYY-MM-DD
	 * YYYY:M:D YYYY/M/DD
	 */
	public static String getPreviousDate(String date) {

		Calendar cd = Calendar.getInstance();
		StringTokenizer token = new StringTokenizer(date, "-/ :");
		if (token.hasMoreTokens()) {
			cd.set(Calendar.YEAR, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.YEAR, 1970);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MONTH, Integer.parseInt(token.nextToken()) - 1);
		} else {
			cd.set(Calendar.MONTH, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.DAY_OF_MONTH, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.HOUR_OF_DAY, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.HOUR_OF_DAY, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MINUTE, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.MINUTE, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.SECOND, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.SECOND, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MILLISECOND, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.MILLISECOND, 0);
		}

		long preTime = cd.getTimeInMillis() - 24 * 60 * 60 * 1000;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date(preTime));
	}
	/*
	 * Get the Next Date Write by Jeffy pan 2004-10-21 Date Format:(YYYY-MM-DD)
	 * (YYYY:M:D HH:MM:SS) (YYYY/M/DD hh:MM)
	 */
	public static Date stringToDate(String date) {
		if (date == null)
			return null;

		Calendar cd = Calendar.getInstance();
		StringTokenizer token = new StringTokenizer(date, "-/ :");
		if (token.hasMoreTokens()) {
			cd.set(Calendar.YEAR, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.YEAR, 1970);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MONTH, Integer.parseInt(token.nextToken()) - 1);
		} else {
			cd.set(Calendar.MONTH, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.DAY_OF_MONTH, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.HOUR_OF_DAY, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.HOUR_OF_DAY, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MINUTE, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.MINUTE, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.SECOND, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.SECOND, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MILLISECOND, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.MILLISECOND, 0);
		}

		return cd.getTime();
	}

	/*
	 * Get the Next Date Write by Jeffy pan 2004-10-21 Date Format:(YYYY-MM-DD)
	 * (YYYY:M:D HH:MM:SS) (YYYY/M/DD hh:MM)
	 */
	public static String dateToString(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date).trim();

	}

	public static String dateTimeToString(Date date) {
		if (date == null)
			return "";
		return dateTimeFormatter.format(date).trim();

	}

	/*
	 * Get the Next Date Write by Jeffy pan 2004-10-21 Date Format:(YYYY-MM-DD)
	 * (YYYY:M:D HH:MM:SS) (YYYY/M/DD hh:MM)
	 */
	public static String dateToString(Date date, String format) {

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date).trim();
	}

	/*
	 * Get the Next Date Write by Jeffy pan 2004-10-21 Date Format:(YYYY-MM-DD)
	 * (YYYY:M:D HH:MM:SS) (YYYY/M/DD hh:MM)
	 */
	public static int getDays(String fromDate, String endDate) {

		long from = stringToDate(fromDate).getTime();
		long end = stringToDate(endDate).getTime();

		return (int) ((end - from) / (24 * 60 * 60 * 1000)) + 1;
	}

	public static int getDays(Date fromDate, Date endDate) {

		long from = fromDate.getTime();
		long end = endDate.getTime();

		return (int) ((end - from) / (24 * 60 * 60 * 1000)) + 1;
	}

	public static String getTakeTime(Date startDate, Date endDate) {
		int minute = 0;
		try {
			minute = (int) (endDate.getTime() - startDate.getTime())
					/ (1000 * 60);
			return String.valueOf(minute);
		} catch (Exception e) {
			return "";
		}

	}

	/*
	 * 获取月份的第一天 written by Sammy: 2004-10-26
	 */
	public static int getFirstDateOfMonth(int year, int month) {

		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, year);
		cl.set(Calendar.MONDAY, month - 1);
		return cl.getActualMinimum(Calendar.DAY_OF_MONTH);
	}

	/*
	 * 获取月份的最后一天 written by Sammy: 2004-10-26
	 */
	public static int getLastDateOfMont(int year, int month) {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, year);
		cl.set(Calendar.MONDAY, month - 1);
		return cl.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static java.sql.Date convertUtilDateToSQLDate(java.util.Date date) {
		if (date == null)
			return null;
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		java.sql.Date jd = new java.sql.Date(cl.getTimeInMillis());
		return jd;
	}

	public static java.sql.Timestamp convertUtilDateToSQLDateWithTime(
			java.util.Date date) {
		if (date != null) {
			return new java.sql.Timestamp(date.getTime());
		} else {
			return null;
		}
	}

	public static java.sql.Date convertStringToSQLDate(String dateString) {
		return (convertUtilDateToSQLDate(stringToDate(dateString)));
	}

	public static java.sql.Date convertToSQLDateWithoutTime(java.util.Date date) {
		String dateString = dateFormatter.format(date);
		return convertStringToSQLDate(dateString);
	}

	/**
	 * get offset to the previous sunday from the specific date
	 * 
	 * @param from
	 *            the specific start date
	 * @param from
	 *            the specific end date
	 * @return offset to the previous sunday
	 * @throws ParseException
	 */
	public static List<Date> getAllSundays(Date from, Date to) {
		List<Date> sundayList = new ArrayList<Date>();
		int offset = getOffsetToNextSunday(from);
		Date firstSunday = addDate(from, offset);
		Date current = firstSunday;
		while (current.compareTo(to) <= 0) {
			sundayList.add(current);
			current = addDate(current, 7);
		}
		return sundayList;
	}

	/**
	 * get offset to the next sunday from the specific date
	 * 
	 * @param date
	 *            the specific date
	 * @return offset to the next sunday
	 * @throws ParseException
	 */
	public static int getOffsetToNextSunday(Date date) {
		if (getDayOfWeek2(date) == 1)
			return 0;
		return 8 - getDayOfWeek2(date);
	}

	/**
	 * get day index of a week for the specific date
	 * 
	 * @param date
	 *            the specific date
	 * @return day index of a week,Mon. is 1,Tues. is 2,Wed. is 3,Thurs. is
	 *         4,Fri. is 5,Sat. is 6,Sun. is 7
	 * @throws ParseException
	 */
	public static int getDayOfWeek(Date date) {
		if (date == null)
			return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int result = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (result == 0)
			result = 7;
		return result;
	}

	/**
	 * get day index of a week for the specific date
	 * 
	 * @param date
	 *            the specific date
	 * @return day index of a week,Sun. is 1,Mon. is 2,Tues. is 3,Wed. is
	 *         4,Thurs. is 5,Fri. is 6,Sat. is 7
	 * @throws ParseException
	 */
	public static int getDayOfWeek2(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * add days to the specific date
	 * 
	 * @param SourceDate
	 *            the specific date
	 * @param days
	 *            day count to be added
	 * @return java.util.Date object after add days
	 * @throws ParseException
	 */
	public static Date addDate(Date sourceDate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * add days to the specific date
	 * 
	 * @param SourceDate
	 *            the specific date
	 * @param days
	 *            day count to be added
	 * @return java.util.Date object after add days
	 * @throws ParseException
	 */
	public static Date addDate(String stringDate, int days) {
		Date sourceDate = stringToDate(stringDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * @param stringDate
	 * @return
	 */
	public static String addOneDay(Date sourceDate) {

		Date newDate = addDate(sourceDate, 1);
		return dateToString(newDate);

	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 * @throws ParseException
	 */
	public static long subDate(Date from, Date to) throws ParseException {
		long value = Math.abs(to.getTime() - from.getTime());
		return value / MSECONDS_OF_ONE_DAY;
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 * @throws ParseException
	 */
	public static long subDate(String from, String to) throws ParseException {
		return subDate(stringToDate(from), stringToDate(to));
	}

	/*
	 * 返回时间列表 (startDate, endDate, days[])
	 */
	public static List<String> getStringDateList(String startDate, String endDate,
			int[] days) {

		List<String> dateList = new ArrayList<String>();
		int days2 = DateTimeTool.getDays(startDate, endDate);
		Date fromDate2 = DateTimeTool.stringToDate(startDate);

		Calendar cal = Calendar.getInstance();

		for (int i = 0; i < days2; i++) {

			cal.setTime(fromDate2);
			cal.add(Calendar.DATE, i);

			for (int j = 0; j < days.length; j++) {
				// 星期数等于所选的
				if (days[j] == cal.get(Calendar.DAY_OF_WEEK)) {

					dateList.add(DateTimeTool.dateToString(cal.getTime()));
				}// if
			}// for

		}// for

		return dateList;
	}

	public static List<Date> getDateList(String startDate, String endDate, int[] days) {

		List<Date> dateList = new ArrayList<Date>();

		int days2 = DateTimeTool.getDays(startDate, endDate);
		Date fromDate2 = DateTimeTool.stringToDate(startDate);

		Calendar cal = Calendar.getInstance();

		for (int i = 0; i < days2; i++) {

			cal.setTime(fromDate2);
			cal.add(Calendar.DATE, i);

			for (int j = 0; j < days.length; j++) {
				// 星期数等于所选的
				if (days[j] == cal.get(Calendar.DAY_OF_WEEK)) {
					dateList.add(cal.getTime());
				}// if
			}// for

		}// for

		return dateList;
	}

	public static int compareDate(Date firstDate, Date secondDate) {
		return firstDate.compareTo(secondDate);
	}
	
	
	
	
	
	
	// 2005-05-05 00:00:00
	/**
	 * 转换类型
	 * 
	 * @param sDate 日期
	 * @return 日期Timestamp
	 */
	public static Timestamp convertTimestamp(String sDate) {
		/*
		 * long lDate = 0; if(sDate.length() == 10){ try { lDate = convertDate(sDate).getTime(); } catch (Exception e) { e.printStackTrace(); } } return new Timestamp(lDate);
		 */
		if (sDate.length() == 10) {
			sDate = sDate + " 00:00:00";
		}
		if (sDate.length() == 16) {
			sDate = sDate + ":00";
		}
		return Timestamp.valueOf(sDate);
	}

	// 2005-05-05 23:59:59.0
	/**
	 * 转换类型
	 * 
	 * @param sDate 日期
	 * @return 日期Timestamp
	 */
	public static Timestamp convertTimestampE(String sDate) {

		if (sDate.length() == 10) {
			sDate = sDate + " 23:59:59";
		}

		return Timestamp.valueOf(sDate);
	}
	/**
	 * 
	 * @Description: 将年月日 变成 Oracle Timestatmps类型
	 * 	2011-10-10 变成 2011-10-10 00:00:00
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      李欢
	 * @date 2011-11-28 下午8:10:49 
	 * @version V1.0
	 */
	public static String Str2Str4Date(String sDate,boolean isBegin) {
		//如果为空
		if(sDate==null || "".equals(sDate))return "";
		//如果是日期要 00:00:00
		if(isBegin){
			sDate+=" 00:00:00";
		}else{//如果日期要 23:59:59
			sDate+=" 23:59:59";
		}
		return sDate;
		
	}
	/**
	 * 转换类型
	 * 
	 * @param sDate 日期"yyyy-MM-dd"
	 * @return 日期Date
	 */
	public static java.util.Date convertDate(String sDate) {
		SimpleDateFormat sFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sFormat2.parse(sDate);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 日期格式化
	 * 
	 * @param date 日期
	 * @return 日期"yyyy-MM-dd"
	 */
	public static String formatDate(java.util.Date date) {
		SimpleDateFormat sFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		if (date == null) {
			return "";
		}
		return sFormat2.format(date);
	}
	/**
	 * 取得时间差
	 * 
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return 日期2-日期1的毫秒时间差
	 */
	public static long getDateDifference(java.util.Date date1, java.util.Date date2) {
		Calendar cld1Work = Calendar.getInstance();
		Calendar cld2Work = Calendar.getInstance();
		Calendar cld1 = Calendar.getInstance();
		Calendar cld2 = Calendar.getInstance();
		long lTime1;
		long lTime2;

		cld1Work.setTime(date1);
		cld2Work.setTime(date2);
		cld1.clear();
		cld2.clear();
		cld1.set(cld1Work.get(Calendar.YEAR), cld1Work.get(Calendar.MONTH), cld1Work.get(Calendar.DATE));
		cld2.set(cld2Work.get(Calendar.YEAR), cld2Work.get(Calendar.MONTH), cld2Work.get(Calendar.DATE));
		lTime1 = (cld1.getTime()).getTime();
		lTime2 = (cld2.getTime()).getTime();

		return (lTime2 - lTime1) / (1000 * 60 * 60 * 24);
	}
	/**
	 * 取得指定日期几天后的日期
	 * 
	 * @param sDate 日期
	 * @param afterDays 天数
	 * @return 日期
	 */
	public static String getAfterDay(String sDate, int afterDays) {
		java.util.Date date = null;
		try {
			date = convertDate(sDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		date = getAfterDay(date, afterDays);
		return formatDate(date);
	}
	/**
	 * 取得指定日期几天后的日期
	 * 
	 * @param date 日期
	 * @param afterDays 天数
	 * @return 日期
	 */
	public static java.util.Date getAfterDay(java.util.Date date, int afterDays) {
		GregorianCalendar cal = new GregorianCalendar();
		if (date == null) {
			cal.setTime(new Date());
		} else {
			cal.setTime(date);
		}
		cal.add(java.util.Calendar.DATE, afterDays);
		return cal.getTime();
	}
	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * @return String
	 * XUEST
	 */ 
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
	/**
	 * @Description:获取当前月的天数
	 * @param		YYYYMM
	 * @return		返回值
	 * @author      薛松坛
	 * @date 2013-7-31 下午2:16:00 
	 * @version V1.0
	 */
	public static int getMonthDays(String strDate){
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM"); 
	    Calendar calendar = new GregorianCalendar();   
	    Date date;
		try {
			date = sdf.parse(strDate);
			calendar.setTime(date);   
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	   int day   =  calendar.getActualMaximum(Calendar.DAY_OF_MONTH);   
	   return day;
	}
	
	
	/*****************************jeesite datautils***************************************************************/
	private static String[] parsePatterns = {
        "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
        "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
        "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }
    
    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }
    
    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }
    
    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }
    
    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
     *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null){
            return null;
        }
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(24*60*60*1000);
    }

    /**
     * 获取过去的小时
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(60*60*1000);
    }
    
    /**
     * 获取过去的分钟
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(60*1000);
    }
    
    /**
     * 转换为时间（天,时:分:秒.毫秒）
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis){
        long day = timeMillis/(24*60*60*1000);
        long hour = (timeMillis/(60*60*1000)-day*24);
        long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
        long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
        long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
        return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
    
    /**
     * 获取两个日期之间的天数
     * 
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }
    
    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
//      System.out.println(formatDate(parseDate("2010/3/6")));
//      System.out.println(getDate("yyyy年MM月dd日 E"));
//      long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//      System.out.println(time/(24*60*60*1000));
    }
}

