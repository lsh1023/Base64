package com.lsh.day38base64;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by LSH on 2016/7/22.
 */
public class RSAUtil {
    //模
    private static final String MODULUS = "100631058000714094813874361191853577129731636346684218206605779824931626830750623070803100189781211343851763275329364056640619755337779928985272486091431384128027213365372009648233171894708338213168824861061809490615593530405056055952622249066180336803996949444124622212096805545953751253607916170340397933039";
    //公钥
    private static final String PUB_KEY = "65537";
    //私钥
    private static final String PRI_KEY = "26900155715313643087786516528374548998821559381075740707715132776187148793016466508650068087107695523642202737697714709374658856733792614490943874205956727606674634563665154616758939576547663715234643273055658829482813503959459653708062875625210008961239643775661357655599312857249418610810177817213648575161";

    //RSA加密秘钥的长度是128位及其倍数
    //产生秘钥对的方法，length  范围：512---2048
    public static KeyPair generaterKeyPaor(int length) {

        try {
            //得到一个秘钥对产生器
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            //初始化秘钥的长度
            generator.initialize(length);
            //产生一个秘钥对:包含公钥和秘钥
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    //加密方法
    public static byte[] encrypt(byte[] data) {

        try {
            //得到RSA加密算法
            Cipher cipher = Cipher.getInstance("RSA");
            //秘钥生成工厂
            KeyFactory factory = KeyFactory.getInstance("RSA");
            //生成一个RSA公钥
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(new BigInteger(MODULUS), new BigInteger(PUB_KEY));
            //生成公钥
            PublicKey publicKey = factory.generatePublic(keySpec);
            //加密
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            return cipher.doFinal(data);


        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密方式
    public static byte[] decrypt(byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            KeyFactory factory = KeyFactory.getInstance("RSA");
            //生成私钥
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(new BigInteger(MODULUS), new BigInteger(PRI_KEY));
            PrivateKey privateKey = factory.generatePrivate(keySpec);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
