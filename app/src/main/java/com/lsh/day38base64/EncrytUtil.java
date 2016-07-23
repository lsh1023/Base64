package com.lsh.day38base64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by LSH on 2016/7/22.
 */
public class EncrytUtil {

    private static  final String AES_FLAG="AES/ECB/PKCS5Padding";

    /*
      DES：密码的长度必须是8个字节64位
      3DES:密码的长度必须是24个字节192位--->算法名称DESede
      AES:密码的长度必须是16个字节或者16的整数倍，最低是128位
     */
    public static byte[] encrypt(int mode,String key,byte[] data)
    {
        byte[] result=null;

        try {
            //得到AES加密算法
            Cipher cipher=Cipher.getInstance(AES_FLAG);
            //生成秘钥
            SecretKeySpec secret=new SecretKeySpec(key.getBytes(),"AES");
            //初始化秘钥
            cipher.init(mode,secret);
            //进行真正的加密
            result=cipher.doFinal(data);
            return result;

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return result;
    }

}
