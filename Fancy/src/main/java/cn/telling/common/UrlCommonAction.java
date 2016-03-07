package cn.telling.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("UrlCommon")
public class UrlCommonAction {
	
	@RequestMapping(value="/Top",method={ RequestMethod.GET,RequestMethod.POST })
	public String Top(){
		return "Controls/Top";
	}
	
	@RequestMapping(value="/Bottom",method={ RequestMethod.GET,RequestMethod.POST })
	public String Bottom(){
		return "Controls/Bottom";
	}
	
	@RequestMapping(value="/Left",method={ RequestMethod.GET,RequestMethod.POST })
	public String Left(){
		return "Controls/Left";
	}
}
