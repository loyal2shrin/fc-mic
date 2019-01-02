package com.fc.base.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * <Date工具类>
 */
public final class Dates {

	private static final int EIGHT_HOURS_MILLISECOND = 8 * 60 * 60 * 1000;
	
	private static String[] parsePatterns = {
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	// yyyy-MM-dd HH:mm:ss日期格式
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final long MAX_TIME = 2112710399000l;

	private Dates() {

	}

	public static String getGMTTime() {
		return DateFormatUtils.formatUTC( System.currentTimeMillis(), "yyyy-MM-dd HHmmss" );
	}

	public static String getCurTime() {
		return DateFormatUtils.format( System.currentTimeMillis(), "yyyy-MM-dd HHmmss" );
	}

	/**
	 * <获取系统前一天的日期字符串>
	 * @param dateFormat
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getPreviousDate( String dateFormat ) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( new Date() );
		calendar.add( Calendar.DAY_OF_YEAR, -1 );

		return formatDate( calendar.getTime(), dateFormat );
	}

	/**
	 * <获取系统前一天的日期字符串>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getPreviousDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( new Date() );
		calendar.add( Calendar.DAY_OF_YEAR, -1 );

		return formatDate( calendar.getTime(), "yyyy-MM-dd HHmmss" );
	}

	/**
	 * <日期转化成字符串>
	 * @param date
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String formatDate( Date date, String dateFormat ) {
		DateFormat df = new SimpleDateFormat( dateFormat );
		return df.format( date );
	}

	public static String getGMTTime( Timestamp time ) {
		return DateFormatUtils.formatUTC( time.getTime(), DEFAULT_DATE_FORMAT );
	}

	public static String formatTime( Timestamp time ) {
		return formatTime( time, "yyyy-MM-dd HH:mm:ss" );
	}

	public static String formatTime( Timestamp time, String format ) {
		SimpleDateFormat sdf = new SimpleDateFormat( format );
		return sdf.format( new Date( time.getTime() ) );
	}

	public static Timestamp getTimestamp( String time ) {
		SimpleDateFormat sf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Date date = null;
		try {
			date = sf.parse( time );
		} catch( ParseException e ) {
			// throw new NSPException( ResultCode.INVALID_PARAMETER, "invalid date format : " + time );
		}

		return new Timestamp( date.getTime() );
	}

	public static Date parse( String dateStr ) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		return sf.parse( dateStr );
	}
	
	public static Date parseDate(Object dateStr) throws ParseException {
		if (dateStr == null){
			return null;
		}
		return DateUtils.parseDate(dateStr.toString(), parsePatterns);
	}

	public static long getTimeMillis( String time ) {
		return getTimestamp( time ).getTime();
	}

	public static long getTimeSeconds( String time ) {
		return getTimestamp( time ).getTime() / 1000;
	}

	/**
	 * 默认减去八个小时
	 * @return Timestamp
	 */
	public static Timestamp timeNowOfUTC() {
		return new Timestamp( System.currentTimeMillis() - EIGHT_HOURS_MILLISECOND );
	}

	/**
	 * mysql 对于小于等于1970-1-1 0：0:0:0 会报错
	 * @param time
	 * @return
	 */
	public static Timestamp getSafeTimeStamp( String time ) {
		if( StringUtils.isEmpty( time ) ) {
			return Dates.timeNowOfUTC();
		}
		try {
			Timestamp timestamp = Dates.getTimestamp( time );
			// 有些客户端会传这种时间 0000-00-00 -1:-1:-1 ，如果大于2037年也会报错
			long timeLong = timestamp.getTime();
			if( timeLong <= 0 || timeLong > MAX_TIME ) {
				timestamp = Dates.timeNowOfUTC();
			}
			return timestamp;
		} catch( Exception e ) {
			return Dates.timeNowOfUTC();
		}
	}
}
