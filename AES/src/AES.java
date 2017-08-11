import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	public static void main(String[] args) {
		String content = "秋天来临了天空像一块覆盖大地的蓝宝石。村外那个小池塘睁着碧澄澄的眼睛，凝望着这美好的天色。一对小白鹅侧着脑袋欣赏自己映在水里的影子。山谷里枫树的叶子，不知是否喝了过量的酒，红的像一团火似的。村前村后的稻子，低着头弯着腰，在秋风中默默地等待着人们去收割，半空中，排着“人”字形的雁群，高兴的唱着歌，告别人们，向天边慢慢飞去……";  
		String password = "12345678";  
		System.out.println("加密内容：" + content); 
		
		//加密  
		Calendar startencrypt = Calendar.getInstance();
		long startencryptMills = startencrypt.getTimeInMillis();
		byte[] encryptResult = encrypt(content, password);  
		Calendar endencrypt = Calendar.getInstance();
		long endencryptMills = endencrypt.getTimeInMillis();
		//System.out.println("加密后：" + new String(encryptResult));
		System.out.println("AES算法加密时间:" + (endencryptMills - startencryptMills)+"ms");
		
		//解密  
		Calendar startdecrypt = Calendar.getInstance();
		long startdecryptMills = startdecrypt.getTimeInMillis();
		byte[] decryptResult = decrypt(encryptResult,password);  
		//System.out.println("解密后：" + new String(decryptResult)); 
		Calendar enddecrypt = Calendar.getInstance();
		long enddecryptMills = enddecrypt.getTimeInMillis();
		//System.out.println("AES算法解密时间:" + (enddecryptMills - startdecryptMills)+"ms");
		
	}
	public static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kgen.init(128, random);
	
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
