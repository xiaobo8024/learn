package com.txb.app.utils;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.encoders.Hex;

import java.security.Security;
import java.util.Arrays;




/**
 * @author WangJing
 * &#064;Description  SM3实现工具类
 */
public class Sm3Util {
    private static final String ENCODING = "UTF-8";
    private static final String MY_KEY = "E680C0F97C22699AE0532901050A5FC9";

    /**
     * 加密
     */
    public static String encryptByKey(String src) throws Exception {
        return ByteUtils.toHexString(getEncryptByKey(src, MY_KEY));
    }

    /**
     * SM3加密方式之： 根据自定义密钥进行加密，返回加密后长度为64位的16进制字符串
     */
    public static byte[] getEncryptByKey(String src, String key) throws Exception {
        byte[] srcByte = src.getBytes(ENCODING);
        byte[] keyByte = key.getBytes(ENCODING);
        KeyParameter keyParameter = new KeyParameter(keyByte);
        SM3Digest sm3 = new SM3Digest();
        HMac hMac = new HMac(sm3);
        hMac.init(keyParameter);
        hMac.update(srcByte, 0, srcByte.length);
        byte[] result = new byte[hMac.getMacSize()];
        hMac.doFinal(result, 0);
        return result;
    }

    /**
     * 利用源数据+密钥校验与密文是否一致
     */
    public boolean verifyByKey(String src, String sm3HexStr){
        try {
            byte[] sm3HashCode = ByteUtils.fromHexString(sm3HexStr);
            byte[] newHashCode = getEncryptByKey(src, MY_KEY);
            return Arrays.equals(newHashCode, sm3HashCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
