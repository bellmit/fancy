package cn.telling.utils;

import java.text.NumberFormat;

import beartool.MD5;

/**
 * MD5加密
 * 用途：
 * (1)用户登录密码， key 使用 t_user.id id放在密码的前面，生成MD5为大写。
 * 
 * @author aibozeng
 *
 */
public class MD5Tool
{

	private static int keyLen = 12;

	private static long MaxID = 999999999999L;

	private static MD5 md5 = new MD5();

	/**
	 * 返回MD5(大写)
	 * @param pwd
	 * @param id
	 * @return
	 */
	public static String encryptionPwd(String pwd, long id)
	{
		String str = md5.getMD5ofStr(formatKey(id) + pwd).toUpperCase();
		return str;
	}

	public static boolean validatePwd(String entryPwd, String pwdDB, long userId)
	{
		if (pwdDB == null) {
			return false;
		}
		if (pwdDB.length() == 32) {
			//32长度默认为是加过密的,把前台传入的进行加密
			String pwdMd5 = MD5Tool.encryptionPwd(entryPwd, userId);
			return pwdMd5.equals(pwdDB);
		} else {
			//没有加密的
			return pwdDB.equals(entryPwd);
		}
	}

	public static boolean validateUserId(String token, String key, long userId)
	{
		if (key == null) {
			return false;
		}
		String pwdMd5 = MD5Tool.encryptionPwd(key, userId);
		return pwdMd5.equals(token);
	}

	/**
	 * 转化为12位的，不足前面补零
	 * @param id
	 * @return
	 */
	public static String formatKey(long id)
	{
		if (id > MaxID) {
			return String.valueOf(id);
		}
		//得到一个NumberFormat的实例
		NumberFormat nf = NumberFormat.getInstance();
		//设置是否使用分组
		nf.setGroupingUsed(false);
		//设置最大整数位数
		nf.setMaximumIntegerDigits(keyLen);
		//设置最小整数位数    
		nf.setMinimumIntegerDigits(keyLen);
		return nf.format(id);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//System.out.println(MD5Tool.formatKey(1234567890123L));
		System.out.println(MD5Tool.encryptionPwd("wangxuelian", 1234L));
	}
}
