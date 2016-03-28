package cn.telling.common.uitl;

import java.util.UUID;

public class UUIDUtil {
	/**
	 * 获取当前UUID，用做数据关联
	 * 新增表结构请使用此ID替换seq
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String uuidStr = (uuid.toString()).replaceAll("-", "");
		return uuidStr;
	}
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
