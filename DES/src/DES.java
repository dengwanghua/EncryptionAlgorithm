import java.security.SecureRandom;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String content = "秋天来临了天空像一块覆盖大地的蓝宝石。村外那个小池塘睁着碧澄澄的眼睛，凝望着这美好的天色。一对小白鹅侧着脑袋欣赏自己映在水里的影子。山谷里枫树的叶子，不知是否喝了过量的酒，红的像一团火似的。村前村后的稻子，低着头弯着腰，在秋风中默默地等待着人们去收割，半空中，排着“人”字形的雁群，高兴的唱着歌，告别人们，向天边慢慢飞去……";  
		String password = "12345678";
		System.out.println("加密内容：" + content);
		
		//加密
		Calendar startencrypt = Calendar.getInstance();
		long startencryptMills = startencrypt.getTimeInMillis();
		byte[] result = desCrypto(content.getBytes(), password);
		Calendar endencrypt = Calendar.getInstance();
		long endencryptMills = endencrypt.getTimeInMillis();
		//System.out.println("加密后：" + new String(result));
		System.out.println("DES算法加密时间:" + (endencryptMills - startencryptMills)+"ms");

		//解密
		Calendar startdecrypt = Calendar.getInstance();
		long startdecryptMills = startdecrypt.getTimeInMillis();
		byte[] decryResult = decrypt(result, password);
		//System.out.println("解密后：" + new String(decryResult)); 
		Calendar enddecrypt = Calendar.getInstance();
		long enddecryptMills = enddecrypt.getTimeInMillis();
		//System.out.println("DES算法解密时间:" + (enddecryptMills - startdecryptMills)+"ms");
		
	}

	public static byte[] desCrypto(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}
}
