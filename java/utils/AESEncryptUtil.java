package com.sogou.reventon.util;


import net.sf.json.JSONObject;
import org.apache.xerces.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * AES 加密 、解密
 * @author litaoos2862
 * @date 2017.05.17
 */
public class AESEncryptUtil {

    public static final String KEY_ALGORITHM = "AES";
    private static final String charset = "utf-8";
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";


    /**
     * 生成密钥
     *
     * @param len 密钥长度 ； 支持128、192、256三种长度。 标准JDK安装包不支持192、256两种长度。
     *            要支持的话，需要上网下载下面的两个jar包，替换JDK安装目录下相同的jar包
     *            用local_policy.jar、US_export_policy
     *            .jar替换掉原来安装目录%JAVA_HOME%\jre6\lib\security 下的两个jar包
     * @return String 密钥
     * @throws Exception
     */
    public static byte[] initkey(Integer len) throws Exception {
        if (len == null) {
            len = 128;
        }
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM, "BC");
        kg.init(len);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * 生成原始key 原始长度128
     *
     * @param key
     * @return
     */
    public static Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    /**
     * 根据指定的字符串生成密钥
     *
     * @param len 密钥长度 ； 支持128、192、256三种长度。
     * @return String 密钥
     */
    public static byte[] getKey(String key, int len) throws Exception {
        if (len != 128 && len != 192 && len != 256)
            return null;
        key = org.apache.commons.codec.digest.DigestUtils.md5Hex(key);
        key = key.substring(0, len / 8);
        byte[] kb = key.getBytes(charset);
        return kb;
    }

    public static String getKeyString(String key, int len) throws Exception {
        if (len != 128 && len != 192 && len != 256)
            return null;
        key = org.apache.commons.codec.digest.DigestUtils.md5Hex(key);
        key = key.substring(0, len / 8);
        byte[] kb = key.getBytes(charset);
        key = Base64.encode(kb);
        return key;
    }


    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, k, getAppIv());
        return cipher.doFinal(data);
    }

    public static String encrypt(String data, String key) throws Exception {
        Key k = toKey(Base64.decode(key));
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, k, getAppIv());

        String content = Base64.encode(cipher.doFinal(data.getBytes(charset)));
        return content;
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k, getAppIv());
        return cipher.doFinal(data);
    }

    public static String decrypt(String data, String key) throws Exception {
        Key k = toKey(Base64.decode(key));
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k, getAppIv());

        String content = new String(cipher.doFinal(Base64.decode(data)), charset);

        return content;
    }

    public static JSONObject encodeForJson(String jsonData,String key) throws Exception {
        String key2 = getKeyString(key, 128);
        jsonData = decrypt(jsonData, key2);
        return JSONObject.fromObject(jsonData);
    }

    public static IvParameterSpec getAppIv() {
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        IvParameterSpec ivParameterSpec;
        ivParameterSpec = new IvParameterSpec(iv);
        return ivParameterSpec;
    }

}