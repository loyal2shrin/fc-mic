package com.fc.base.utils.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.fc.base.utils.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 支持HMAC-SHA1消息签名 及 DES/AES对称加密的工具类.
 * <p>
 * 支持Hex与Base64两种编码方式.
 */
public class Cryptos {

    private static final String AES = "AES";
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";
    private static final String HMACSHA1 = "HmacSHA1";
    private static final String DEFAULTIV = "1234567812345678";

    private static final int DEFAULT_HMACSHA1_KEYSIZE = 160; // RFC2401
    private static final int DEFAULT_AES_KEYSIZE = 128;
    private static final int DEFAULT_IVSIZE = 16;

    private static SecureRandom random = new SecureRandom();

    private static final Logger LOGGER = LoggerFactory.getLogger(Cryptos.class);

    // -- HMAC-SHA1 funciton --//

    /**
     * 使用HMAC-SHA1进行消息签名, 返回字节数组,长度为20字节.
     *
     * @param input 原始输入字符数组
     * @param key   HMAC-SHA1密钥
     */
    public static byte[] hmacSha1(byte[] input, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, HMACSHA1);
            Mac mac = Mac.getInstance(HMACSHA1);
            mac.init(secretKey);
            return mac.doFinal(input);
        } catch (GeneralSecurityException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 校验HMAC-SHA1签名是否正确.
     *
     * @param expected 已存在的签名
     * @param input    原始输入字符串
     * @param key      密钥
     */
    public static boolean isMacValid(byte[] expected, byte[] input, byte[] key) {
        byte[] actual = hmacSha1(input, key);
        return Arrays.equals(expected, actual);
    }

    /**
     * 生成HMAC-SHA1密钥,返回字节数组,长度为160位(20字节).
     * HMAC-SHA1算法对密钥无特殊要求, RFC2401建议最少长度为160位(20字节).
     */
    public static byte[] generateHmacSha1Key() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(HMACSHA1);
            keyGenerator.init(DEFAULT_HMACSHA1_KEYSIZE);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (GeneralSecurityException e) {
            throw Exceptions.unchecked(e);
        }
    }

    // -- AES funciton --//

    /**
     * 使用AES加密原始字符串.
     *
     * @param input 原始输入字符数组
     * @param key   符合AES要求的密钥
     */
    public static String aesEncrypt(String input, String key) {
        return new sun.misc.BASE64Encoder().encode(aes(input.getBytes(), key, DEFAULTIV, Cipher.ENCRYPT_MODE));
    }

    /**
     * 使用AES加密原始字符串.
     *
     * @param input 原始输入字符数组
     * @param key   符合AES要求的密钥
     * @param iv    初始向量
     */
    public static String aesEncrypt(String input, String key, String iv) {
        return new sun.misc.BASE64Encoder().encode(aes(input.getBytes(), key, iv, Cipher.ENCRYPT_MODE));
    }

    /**
     * 使用AES解密字符串, 返回原始字符串.
     *
     * @param input Hex编码的加密字符串
     * @param key   符合AES要求的密钥
     */
    public static String aesDecrypt(String input, String key) {
        byte[] decryptResult = new byte[0];
        try {
            decryptResult = aes(new sun.misc.BASE64Decoder().decodeBuffer(input), key, DEFAULTIV, Cipher.DECRYPT_MODE);
        } catch (IOException e) {
            LOGGER.error("decode catch an excdeption", e);
        }
        return new String(decryptResult).trim();
    }

    /**
     * 使用AES解密字符串, 返回原始字符串.
     *
     * @param input Hex编码的加密字符串
     * @param key   符合AES要求的密钥
     * @param iv    初始向量
     */
    public static String aesDecrypt(String input, String key, String iv) {
        byte[] decryptResult = new byte[0];
        try {
            decryptResult = aes(new sun.misc.BASE64Decoder().decodeBuffer(input), key, iv, Cipher.DECRYPT_MODE);
        } catch (IOException e) {
            LOGGER.error("decode catch an excdeption", e);
        }
        return new String(decryptResult).trim();
    }

//	public static String aesEncrypt(String data, String key) throws Exception {
//		try {
//			String iv = "1234567812345678";
//			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//			int blockSize = cipher.getBlockSize();
//
//			byte[] dataBytes = data.getBytes();
//			int plaintextLength = dataBytes.length;
//			if (plaintextLength % blockSize != 0) {
//				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
//			}
//
//			byte[] plaintext = new byte[plaintextLength];
//			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
//
//			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
//			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
//
//			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
//			byte[] encrypted = cipher.doFinal(plaintext);
//
//			return new sun.misc.BASE64Encoder().encode(encrypted);
//		} catch (Exception e) {
//			//LOGGER.error("aes encrypt catch an exception", e);
//			return "";
//		}
//	}
//
//	private static String decrypt(String data, String key) throws Exception {
//		try {
//			String iv = "1234567812345678";
//			byte[] encrypted1 = new sun.misc.BASE64Decoder().decodeBuffer(data);
//
//			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
//			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
//
//			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
//
//			byte[] original = cipher.doFinal(encrypted1);
//			String originalString = new String(original).trim();
//			return originalString;
//		} catch (Exception e) {
//			//LOGGER.error("aes descrypt catch an exception", e);
//			return "";
//		}
//	}

    /**
     * 使用AES加密或解密无编码的原始字节数组, 返回无编码的字节数组结果.
     *
     * @param input 原始字节数组
     * @param key   符合AES要求的密钥
     * @param mode  Cipher.ENCRYPT_MODE 或 Cipher.DECRYPT_MODE
     */
    private static byte[] aes(byte[] input, String key, int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), AES);
            Cipher cipher = Cipher.getInstance(AES_CBC);
            cipher.init(mode, secretKey);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 使用AES加密或解密无编码的原始字节数组, 返回无编码的字节数组结果.
     *
     * @param input 原始字节数组
     * @param key   符合AES要求的密钥
     * @param iv    初始向量
     * @param mode  Cipher.ENCRYPT_MODE 或 Cipher.DECRYPT_MODE
     */
    private static byte[] aes(byte[] input, String key, String iv, int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), AES);
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
            Cipher cipher = Cipher.getInstance(AES_CBC);
            cipher.init(mode, secretKey, ivSpec);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 生成AES密钥,返回字节数组, 默认长度为128位(16字节).
     */
    public static byte[] generateAesKey() {
        return generateAesKey(DEFAULT_AES_KEYSIZE);
    }

    /**
     * 生成AES密钥,可选长度为128,192,256位.
     */
    public static byte[] generateAesKey(int keysize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
            keyGenerator.init(keysize);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (GeneralSecurityException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 生成随机向量,默认大小为cipher.getBlockSize(), 16字节.
     */
    public static byte[] generateIV() {
        byte[] bytes = new byte[DEFAULT_IVSIZE];
        random.nextBytes(bytes);
        return bytes;
    }

    public static byte[] getRawKey(byte[] seed) {
        byte[] rawKey = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(AES);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed);
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            rawKey = secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw Exceptions.unchecked(e);
        }
        return rawKey;
    }
}