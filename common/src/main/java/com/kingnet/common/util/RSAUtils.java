package com.kingnet.common.util;

import android.util.Base64;

import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 *
 * Created by livvy on 16-12-29.
 */

public class RSAUtils {

    private static final String RSA = "RSA";// 非对称加密密钥算法
    private static final String ECB_PKCS1_PADDING = "RSA/ECB/NoPadding";//加密填充方式

//    private static final String  PUBLIC_KEY  =
//              "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw6k9KWWqCt5/IXF6/DkY" + "\r"
//            + "9QxBByiHKSAoLxVvkDbcc2FWjmHNjG5j0elqbXGzSFWC8zNfhQfmsV80Q/pL5OsI" + "\r"
//            + "Uopu+nSgENL8fy3hRL4JJ+4cz30+Fp8ayiElthsnntNE1rx7laQG2IwYyIweKWZA" + "\r"
//            + "ALDXhMjCsfGvnS3RzqTNMYUvzmu3eBSoKf66n31uFXs9cUFy8KlGbh1IImN20C0h" + "\r"
//            + "pwXfIeTzibZygEnhIq6zXeNgCbIDL+2I0hYCDoKnM40KPKRiLj179mJgjXSuwhOi" + "\r"
//            + "Yh76TLezjVcskneOfWFQqnuC4cuhh693ucK8vqu2KoV6i0jaAiB74A7UOupYuC5C" + "\r"
//            + "TwIDAQAB";

    private static final String  PUBLIC_KEY  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw6k9KWWqCt5/IXF6/DkY\n" +
                    "9QxBByiHKSAoLxVvkDbcc2FWjmHNjG5j0elqbXGzSFWC8zNfhQfmsV80Q/pL5OsI\n" +
                    "Uopu+nSgENL8fy3hRL4JJ+4cz30+Fp8ayiElthsnntNE1rx7laQG2IwYyIweKWZA\n" +
                    "ALDXhMjCsfGvnS3RzqTNMYUvzmu3eBSoKf66n31uFXs9cUFy8KlGbh1IImN20C0h\n" +
                    "pwXfIeTzibZygEnhIq6zXeNgCbIDL+2I0hYCDoKnM40KPKRiLj179mJgjXSuwhOi\n" +
                    "Yh76TLezjVcskneOfWFQqnuC4cuhh693ucK8vqu2KoV6i0jaAiB74A7UOupYuC5C\n" +
                    "TwIDAQAB";


    private static RSAPublicKey publicKey = null;


    public static void init(){
        loadPublicKey(PUBLIC_KEY);
    }

    /**
     * 从字符串中加载公钥,从服务端获取
     *
     * 加载公钥时产生的异常
     */
    private static void loadPublicKey(String pubKey) {
        try {
            byte[] buffer = Base64.decode(pubKey, Base64.DEFAULT);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公钥加密过程
     *
     * @param plainData
     *            明文数据
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encryptWithRSA(String plainData) throws Exception {
        if (publicKey == null) {
            throw new NullPointerException("encrypt PublicKey is null !");
        }

        Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);// 此处如果写成"RSA"加密出来的信息JAVA服务器无法解析 RSA/ECB/PKCS1Padding

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] output = cipher.doFinal(plainData.getBytes(Charset.forName("UTF-8")));
        // 必须先encode成 byte[]，再转成encodeToString，否则服务器解密会失败
        byte[] encode = Base64.encode(output, Base64.DEFAULT);
        return Base64.encodeToString(encode, Base64.DEFAULT);
    }


}
