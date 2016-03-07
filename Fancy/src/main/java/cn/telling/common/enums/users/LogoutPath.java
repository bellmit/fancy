package cn.telling.common.enums.users;

/**
 * @Title: LogoutPath.java
 * @Package com.Common.Enums.Users
 * @Description: TODO(描述该文件做什么)
 * @author 张海斌
 * @date 2013-4-10 下午4:23:13
 * @version V1.0
 */
public enum LogoutPath {
	AdministratorPath(1, "redirect:/Login.html"), PlatformAdminPath(2,
			"redirect:/Login.html"), SupplyPath(3,
			"redirect:index.html"), BuyerPath(4,
			"redirect:index.html"), Staffpath(5,
			"redirect:/Login.html"), unicompath(6, "redirect:/Login.html"), OtherPath(
			-1, "/"), commonBuyerPath(7, "redirect:index.html");

	private int _value;
	private String _text;

	private LogoutPath(int value, String text) {
		_value = value;
		_text = text;
	}

	public int value() {
		return _value;
	}

	public String text() {
		return _text;
	}

	public static LogoutPath valueOf(int v) {
		switch (v) {
		case 1:
			return AdministratorPath;
		case 2:
			return PlatformAdminPath;
		case 3:
			return SupplyPath;
		case 4:
			return BuyerPath;
		case 5:
			return Staffpath;
		case 6:
			return unicompath;
		case 7:
			return commonBuyerPath;
		default:
			return OtherPath;
		}
	}
}
