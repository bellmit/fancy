package cn.telling.action.main;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.telling.constant.MessageCode;
import cn.telling.exception.BusinessException;
import cn.telling.exception.SystemException;

@Controller
public class WelcomeController {

	/**
	 * jump into the index page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index1.html")
	public String indexPage() {
		return "/ExceptionDemo";
	}

	/**
	 * get the json Exception
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/josonException.html")
	public @ResponseBody Map<String, Object> getjson() throws BusinessException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("content", "123");
			map.put("result", true);
			map.put("account", 1);
			throw new Exception();
		} catch (Exception e) {
			throw new BusinessException(BusinessException.TYPE_BACK,MessageCode.MSG_ERROR);
		}
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/SystemException.html")
	public void TestSystemException() {
		throw new SystemException("this is system error ");
	}

	/**
	 * @throws cn.telling.exception.BusinessException
	 * 
	 */
	@RequestMapping(value = "/BusinessException.html")
	public void TestBusinessException() throws BusinessException {
		throw new BusinessException(BusinessException.TYPE_BACK,"this is Business error ");
	}
}
