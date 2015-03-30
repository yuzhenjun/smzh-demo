package com.server.oauth2.credentials;

import java.security.MessageDigest;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.util.ByteSource;

import sun.misc.BASE64Encoder;

public class SimpleCredentialsMatcherWapper extends SimpleCredentialsMatcher {
	
	
	// 加密方式
	private String hashAlgorithm;

	public String getHashAlgorithmName() {
		return hashAlgorithm;
	}
	
    public void setHashAlgorithmName(String hashAlgorithmName) {
        this.hashAlgorithm = hashAlgorithmName;
    }
    
    protected Object getSalt(AuthenticationToken token) {
        return token.getPrincipal();
    }
    
    protected Object getCredentials(AuthenticationInfo info) {
        Object credentials = info.getCredentials();//数据库密码
        return credentials;
    }
    
    
    @SuppressWarnings("restriction")
	private String encrypt(AuthenticationToken token, AuthenticationInfo info) {
    	String result=null;
    	ByteSource salt = null;
        if (info instanceof SaltedAuthenticationInfo) {
             salt = ((SaltedAuthenticationInfo) info).getCredentialsSalt();
         }
        String password= String.valueOf(((UsernamePasswordToken)token).getPassword());
        String saltedPassword =new String(salt.getBytes())+ '-' + password;
         
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] b = md5.digest(saltedPassword.getBytes("UTF-8"));
			result= new BASE64Encoder().encode(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
    
    
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Object tokenHashedCredentials = encrypt(token, info);
        Object accountCredentials = getCredentials(info);
        return equals(tokenHashedCredentials, accountCredentials);
    }
}
