package com.alipay.sign;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 /**
 * @ClassName: MD5Util 
 * @Description: 兴业银行支付宝使用此加密方式
 * @author zkq
 * @date 2017年3月25日
 *
  */
public class MD5Util {
 
    public static void main(String[] args) {
        MD5Util test = new MD5Util();
        System.out.println(test.encryption("12345678"));
    }
 
    /**
     * 兴业银行使用此加密方式
     * @param plainText
     *            明文
     * @return 32位密文
     */
    public static String encryption(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
 
            int i;
 
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
 
            re_md5 = buf.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }
}
