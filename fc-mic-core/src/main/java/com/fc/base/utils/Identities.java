package com.fc.base.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Identities {

	private static SecureRandom random = new SecureRandom();

	private static AtomicInteger idSeq = new AtomicInteger(0);

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid2() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}

	public static String nextIdSeq() {
		if (idSeq.get() == 9999) {
			idSeq.set(0);
		}
		String str = String.format("%04d", idSeq.addAndGet(1));
		return str;
	}

	public static String getSystemId() {
		SimpleDateFormat format = new SimpleDateFormat("ssmmhhddMM");
		String ranStr = format.format(new Date());
		return ranStr + nextIdSeq();
	}
}
