package com.smzh.security.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.smzh.security.entity.DefaultUser;

/**
 * 用户密码加密工具
 * @author zhenjun
 *
 */
public class PasswordUtils {

	
	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private static final String algorithmName = "md5";
	
	
	private static final int hashIterations = 2;

	
	/**
	 * 加密用户密码
	 * @param user
	 */
	public static void encryptPassword(DefaultUser user) {

		
        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(algorithmName,user.getPassword(),ByteSource.Util.bytes(user.getLoginName()+user.getSalt()),hashIterations).toHex();

        user.setPasswordEncrypted(newPassword);
    }
	
	
	public static boolean judgedPassword(DefaultUser user){
		 String encryptPassword = new SimpleHash(algorithmName,user.getPassword(),ByteSource.Util.bytes(user.getLoginName()+user.getSalt()),hashIterations).toHex();
		 if(StringUtils.equals(encryptPassword, user.getPasswordEncrypted()))
			 return true;
		 return false;
	}
	public PasswordUtils() {
		// TODO Auto-generated constructor stub
	}@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	public static void main(String[] args) {
		DefaultUser user=new DefaultUser();
		user.setLoginName("cztv");
		user.setPassword("1");
		String salt=randomNumberGenerator.nextBytes().toHex();
		System.out.println(salt);
		user.setSalt(salt);
		String newPassword = new SimpleHash(algorithmName,user.getPassword(),ByteSource.Util.bytes(user.getLoginName()+user.getSalt()),hashIterations).toHex();
		System.out.println(newPassword);
		 user.setPasswordEncrypted(newPassword);
	}
}
