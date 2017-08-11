import java.util.Calendar;

public class CyptUtil {
	// 密钥关联的key
	private static final String key = "lscfXe5XSC0oX8x";
	// BlowFish实现
	private static final Blowfish blowfish = new Blowfish(key);

	
	
	public static void main(String[] args) {
		String content = "秋天来临了天空像一块覆盖大地的蓝宝石。村外那个小池塘睁着碧澄澄的眼睛，凝望着这美好的天色。一对小白鹅侧着脑袋欣赏自己映在水里的影子。山谷里枫树的叶子，不知是否喝了过量的酒，红的像一团火似的。村前村后的稻子，低着头弯着腰，在秋风中默默地等待着人们去收割，半空中，排着“人”字形的雁群，高兴的唱着歌，告别人们，向天边慢慢飞去……";  
		System.out.println("加密内容：" + content); 
		
		//加密  
		Calendar startencrypt = Calendar.getInstance();
		long startencryptMills = startencrypt.getTimeInMillis();
		String encryptResult = encrypt(content);  
		Calendar endencrypt = Calendar.getInstance();
		long endencryptMills = endencrypt.getTimeInMillis();
		//System.out.println("加密后：" +encryptResult);
		System.out.println("BlowFish算法加密时间:" + (endencryptMills - startencryptMills)+"ms");
		
		//解密  
		Calendar startdecrypt = Calendar.getInstance();
		long startdecryptMills = startdecrypt.getTimeInMillis();
		String decryptResult = decrypt(encryptResult);  
		//System.out.println("解密后：" +decryptResult); 
		Calendar enddecrypt = Calendar.getInstance();
		long enddecryptMills = enddecrypt.getTimeInMillis();
		//System.out.println("BlowFish算法解密时间:" + (enddecryptMills - startdecryptMills)+"ms");
	}
	
	public static String encrypt(String plainText) {
		return blowfish.encryptString(plainText);
	}

	public static String decrypt(String cryptText) {
		return blowfish.decryptString(cryptText);
	}
}
