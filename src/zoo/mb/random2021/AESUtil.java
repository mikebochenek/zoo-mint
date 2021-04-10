package zoo.mb.random2021;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    private static final String ENCRYPTION_IV = "SHCUOkfd89ut7777";
    private static final String ENCRYPTION_KEY = "Simpleji4todnkfL";

    public static byte[] encrypt(byte[] bArr) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, makeKey(), makeIv());
            return instance.doFinal(bArr);
        } catch (Exception bArr2) {
            throw new RuntimeException(bArr2);
        }
    }

    public static byte[] decrypt(byte[] bArr) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, makeKey(), makeIv());
            return instance.doFinal(bArr);
        } catch (Exception bArr2) {
            throw new RuntimeException(bArr2);
        }
    }

    static AlgorithmParameterSpec makeIv() {
        try {
            return new IvParameterSpec(ENCRYPTION_IV.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Key makeKey() {
        try {
            return new SecretKeySpec(MessageDigest.getInstance("SHA-256").digest(ENCRYPTION_KEY.getBytes("UTF-8")), "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static byte[] exs = new byte[]{(byte) -28, (byte) 73, (byte) 79, (byte) 78, (byte) 113, (byte) 73, (byte) 101, (byte) 98, (byte) 115, (byte) 6, (byte) 27, (byte) -35, (byte) 111, (byte) -55, (byte) -114, (byte) -11, (byte) -29, (byte) 0, (byte) -73, (byte) 91, (byte) 115, (byte) -24, (byte) -4, (byte) -94, (byte) -59, (byte) 43, (byte) -57, (byte) 112, (byte) 11, (byte) -54, (byte) -115, (byte) 2};

	public static void main(String[] args) {
		System.out.println(new String(decrypt(exs)));

	}
}
