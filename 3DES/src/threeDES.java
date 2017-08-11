import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class threeDES {
	private static final String Algorithm = "DESede";
	private static final String PASSWORD_CRYPT_KEY = "2012PinganVitality075522628888ForShenZhenBelter075561869839";

	public static void main(String[] args) {
		String content = "秋天来临了天空像一块覆盖大地的蓝宝石。村外那个小池塘睁着碧澄澄的眼睛，凝望着这美好的天色。一对小白鹅侧着脑袋欣赏自己映在水里的影子。山谷里枫树的叶子，不知是否喝了过量的酒，红的像一团火似的。村前村后的稻子，低着头弯着腰，在秋风中默默地等待着人们去收割，半空中，排着“人”字形的雁群，高兴的唱着歌，告别人们，向天边慢慢飞去……";
		System.out.println("加密内容:" + content);

		// 加密
		Calendar startencrypt = Calendar.getInstance();
		long startencryptMills = startencrypt.getTimeInMillis();
		byte[] secretArr = threeDES.encryptMode(content.getBytes());
		Calendar endencrypt = Calendar.getInstance();
		long endencryptMills = endencrypt.getTimeInMillis();
		//System.out.println("加密后：" + new String(secretArr));
		System.out.println("3DES算法加密时间:" + (endencryptMills - startencryptMills)+"ms");
		
		
		// 解密
		Calendar startdecrypt = Calendar.getInstance();
		long startdecryptMills = startdecrypt.getTimeInMillis();
		byte[] myMsgArr = threeDES.decryptMode(secretArr);
		//System.out.println("解密后:" + new String(myMsgArr)); 
		Calendar enddecrypt = Calendar.getInstance();
		long enddecryptMills = enddecrypt.getTimeInMillis();
		//System.out.println("3DES算法解密时间:" + (enddecryptMills - startdecryptMills)+"ms");
		

	}

	public static byte[] encryptMode(byte[] src) {
		try {
			SecretKey deskey = new SecretKeySpec(
					build3DesKey(PASSWORD_CRYPT_KEY), Algorithm); // 生成密钥
			Cipher c1 = Cipher.getInstance(Algorithm); // 实例化负责加密/解密的Cipher工具类
			c1.init(Cipher.ENCRYPT_MODE, deskey); // 初始化为加密模式
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static byte[] decryptMode(byte[] src) {
		try {
			SecretKey deskey = new SecretKeySpec(
					build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey); // 初始化为解密模式
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static byte[] build3DesKey(String keyStr)
			throws UnsupportedEncodingException {
		byte[] key = new byte[24]; // 声明一个24位的字节数组，默认里面都是0
		byte[] temp = keyStr.getBytes("UTF-8"); // 将字符串转成字节数组

		if (key.length > temp.length) {
			// 如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, temp.length);
		} else {
			// 如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, key.length);
		}
		return key;
	}
}
