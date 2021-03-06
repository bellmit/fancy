package cn.telling.action.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import cn.telling.action.BaseController;
import cn.telling.action.main.service.PagingService;
import cn.telling.common.StringUtils;
import cn.telling.common.UserUtils;
import cn.telling.common.constants.Constants;
import cn.telling.common.pager.PageVo;
import cn.telling.common.pager.Pager;
import cn.telling.common.security.shiro.session.SessionDAO;
import cn.telling.common.uitl.CacheUtils;
import cn.telling.common.uitl.CookieUtils;
import cn.telling.common.uitl.FreeMarkerUtil;
import cn.telling.common.uitl.IdGen;
import cn.telling.common.uitl.RedisListAPIUtil;
import cn.telling.common.uitl.RedisMapAPIUtil;
import cn.telling.common.uitl.StringUtil;
import cn.telling.common.uitl.TimestampTypeAdapter;
import cn.telling.config.Global;
import cn.telling.log.service.IUserLoginLogService;
import cn.telling.log.vo.Syslog;
import cn.telling.log.vo.UserLoginLog;
import cn.telling.menu.service.IMenuService;
import cn.telling.menu.vo.Menu;
import cn.telling.menu.vo.TreeNode;
import cn.telling.shirocontroller.FormAuthenticationFilter;
import cn.telling.shirocontroller.ShiroRealm.Principal;
import cn.telling.user.service.IUserService;
import cn.telling.user.vo.User;
import cn.telling.utils.LogType;
import cn.telling.utils.Paging;
import cn.telling.utils.StringHelperTools;
import cn.telling.utils.TCPIPUtil;
import cn.telling.web.AuthImg;
import cn.telling.web.MethodLog;

import com.alibaba.fastjson.JSON;
import com.danga.MemCached.MemCachedClient;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import freemarker.template.TemplateException;

/**
 * @Title: SysAction.java
 * @Package cn.telling.action.main
 * @Description: ????????????
 * @author ??????
 * @date 2015-12-7 ??????11:00:31
 * @version V1.0
 */
@Controller
public class SysAction extends BaseController{
    
	private AuthImg ai = null;
	@Resource
	public PagingService pagingService;

	@Resource
	private IUserService userService;

	@Resource
	private org.apache.shiro.cache.CacheManager shiroCacheManager;

	@Resource
	IUserLoginLogService userLoginLogService;

	@Resource
	IMenuService menuService;
  	
	@Resource
	SessionDAO sessionDao;
	/**
	 * ??????????????????
	 * @return
	 */
	public Collection<Session> getActiveSessions(){
		return sessionDao.getActiveSessions(false);
	}
	
  @Autowired
  private MemCachedClient memCachedClient;

  @RequestMapping(value="/viewLeftMenuJson")
	@MethodLog(remark = "????????????????????????")
	public void getLeftMenuJson(HttpServletResponse response, String index) {
		response.setContentType("text/html;charset=utf-8");
		// shiro????????????????????????????????????????????????????????????????????????
		User user =UserUtils.getUser();
		String userid = user.getId();
		List<Menu> listpm = new ArrayList<Menu>();
		try {
			listpm = menuService.findMenuByUserId(userid);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// ?????????????????????????????????list2???
		List<TreeNode> list = new ArrayList<TreeNode>();
		Map<Object, Object> map = new HashMap<Object, Object>();
		// ?????????????????????????????????list2???
		// Map pmap = new HashMap<String, TreeNode>();
		// ArrayList<PusMenu> list2 = (ArrayList<TDict>)this.find(hql);

		// ???listpm????????????????????????TreeNode???????????????Map?????????
		for (Menu pm : listpm) {
			TreeNode node = new TreeNode();
			node.setId(pm.getId());
			node.setText(pm.getName());

			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", pm.getPageUrl());

			node.setAttribute(attributes);
			// jdbc??????????????????????????????
			if (pm.getPm() != null) {
				node.setParentId(pm.getPm().getId());
			} else {
				pm.setPm(menuService.getById(pm.getMenupId()));
				if (pm.getPm() != null) {
					pm.setPm(menuService.getById(pm.getMenupId()));
				}
			}
			// node.setParentId(pm.getId());
			map.put(pm.getId(), node);
		}

		// ??????listpm?????????????????????????????????????????????????????????List
		for (Menu pm : listpm) {

			if (pm.getPm() != null) {

				if (pm.getPm().getId() == null) {
					list.add((TreeNode) map.get(pm.getId()));
				} else {

					BigDecimal pidInteger = pm.getPm().getId();
					TreeNode pnode = (TreeNode) map.get(pidInteger);
					TreeNode cnode = (TreeNode) map.get(pm.getId());
					// if (pnode != null) {
					// pnode.addChild(cnode);
					// }
					if (pnode == null) { // ????????????????????????????????????????????? ?????????????????? ?????? ??????????????????
											// ????????????????????????
						pnode = new TreeNode();
						pnode.setId(new BigDecimal(0));
						pnode.addChild(cnode);
						list.add((TreeNode) map.get(pm.getId()));
					} else {
						pnode.addChild(cnode);
					}

				}
			} else {
				list.add((TreeNode) map.get(pm.getId()));
			}
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class, new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String indexis = StringUtil.stringIsNull(index);

		// ?????????????????????????????????????????????
		TreeNode dj = new TreeNode();
		dj.setId(new BigDecimal(0));
		dj.setText("????????????");
		if (!indexis.equals("true")) {
			list.add(dj);
		}
		String json = gson.toJson(list);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
	}

	@RequestMapping("validate")
	public void validate(HttpServletRequest request, HttpServletResponse response) {
		ai = new AuthImg();
		ai.service(request, response);
	}

	   
	/****
	 * ????????????
	 * 
	 * @param
	 * @author ??????
	 * @date 2015-4-27 ??????3:35:32
	 * @version V1.0
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		// ????????????????????????????????????????????????????????????
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))) {
			CookieUtils.setCookie(response, "LOGINED", "false");
		}
		// ?????????????????????????????????????????????
		if (principal != null && !principal.isMobileLogin()) {
			return new ModelAndView("redirect:" + adminPath);
		}
        model.addAttribute("online", sessionDao.getActiveSessions(false).size());
		return new ModelAndView("login");
		
	}
	/**
	 * ????????????
	 * ???ShiroRealm.doGetAuthenticationInfo??????????????????????????????
	 * @date 2015-4-27 ??????3:35:32
	 * @version V1.0
	 */
	@MethodLog(remark = "????????????", operType = LogType.LOGIN)
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String userLogin(User user, ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		// if (userName != null && !"".equals(userName) && passWord != null &&
		// !"".equals(passWord)) {
		// String uId = String.valueOf(userService.getUserId(userName));
		// if (uId != null) {
		// String pwd = MD5Tool.encryptionPwd(passWord, Long.parseLong(uId));
		// Users users = userService.userLogin(userName, pwd);
		// if (users != null) {
		// String validate = (String)
		// request.getSession().getAttribute("validate");
		// String vali = request.getParameter("validate");
		// if (validate != null && vali != null &&
		// vali.equalsIgnoreCase(validate)) {
		// request.getSession().setAttribute("userId", users.getId());
		// request.getSession().setAttribute("userName", users.getUsername());
		// request.getSession().setAttribute("lastTime", users.getLastTime());
		// request.getSession().setAttribute("email", users.getEmail());
		// return "redirect:index.html";
		// }
		// }
		// }
		// model.put("msg", "????????????");
		// }
//		 PasswordService svc = new DefaultPasswordService();
//		 String encrypted = svc.encryptPassword(users.getPassword());
	    Principal principal = UserUtils.getPrincipal();
        
        // ?????????????????????????????????????????????
        if(principal != null)
            return "redirect:"+adminPath;
		String validate = (String) request.getSession().getAttribute("validate");
		String vali = request.getParameter("validate");
		if (StringHelperTools.nvl(user.getUsername()).equals("")) {
			model.addAttribute("message", "??????????????????!");
			return "login";
		}
		else if (StringHelperTools.nvl(user.getPassword()).equals("")) {
			model.addAttribute("message", "??????????????????!");
			return "login";
		}
		else if (StringHelperTools.nvl(vali).equals("")) {
			model.addAttribute("message", "?????????????????????!");
			return "login";
		}
		// ??????
		else if (StringHelperTools.nvl(validate).equals("")) {
			model.addAttribute("message", "???????????????,???????????????!");
			return "login";
		}
		else if (!validate.equalsIgnoreCase(vali)) {
			 //throw new IncorrectCaptchaException("??????????????????");
			model.addAttribute("message", "???????????????!");
			return "login";
		}
		
		String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
        boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
        boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
        String exception = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
        
        if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")){
            message = "?????????????????????, ?????????.";
        }
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
        model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
        model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, mobile);
        model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
        model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
        
        if (logger.isDebugEnabled()){
			logger.debug("login fail, active session size: {}, message: {}, exception: {}", 
					sessionDao.getActiveSessions(false).size(), message, exception);
		}
        
        // ?????????????????????????????????????????????1???
//        if (!UnauthorizedException.class.getName().equals(exception)){
//            model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
//        }
        
        // ???????????????????????????
        request.getSession().setAttribute(AuthImg.VALIDATE_CODE, IdGen.uuid());
        
        // ?????????????????????????????????JSON?????????
        if (mobile){
            return renderString(response, model);
        }
		return "login";
	}

	@RequestMapping(value = "/getout", method = RequestMethod.GET)
	public ModelAndView getout(Model model) {
		ModelAndView mav = new ModelAndView("login");
		User psu = new User();
		mav.addObject("login", psu);
		model.addAttribute("message", "?????????????????????????????????!");
		return mav;
	}
	
	/**
     * ?????????????????????????????????
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "${adminPath}")
    public String index(HttpServletRequest request, HttpServletResponse response,Model model) {
    	Principal principal = UserUtils.getPrincipal();
		// ??????????????????????????????????????????
		isValidateCodeLogin(principal.getLoginName(), false, true);
		// ????????????????????????????????????????????????????????????
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			String logined = CookieUtils.getCookie(request, "LOGINED");
			if (StringUtils.isBlank(logined) || "false".equals(logined)){
				CookieUtils.setCookie(response, "LOGINED", "true");
			}else if (StringUtils.equals(logined, "true")){
				UserUtils.getSubject().logout();
				return "redirect:" + adminPath + "/login";
			}
		}
		Collection<Session> sessions =  sessionDao.getActiveSessions();
        model.addAttribute("sessions", sessions);
		model.addAttribute("sessionCount",sessionDao.getActiveSessions(false).size());
		// ?????????????????????????????????JSON?????????
		if (principal.isMobileLogin()){
			if (request.getParameter("login") != null){
				return renderString(response, principal);
			}
			if (request.getParameter("index") != null){
				return "index";
			}
			return "redirect:" + adminPath + "/login";
		}
    	return "index";
    }
    
	/**
     * ????????????????????????
     * 
     * @param useruame
     *            ?????????
     * @param isFail
     *            ?????????1
     * @param clean
     *            ????????????
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean isValidateCodeLogin(String useruame, boolean isFail,
            boolean clean) {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils
                .get("loginFailMap");
        if (loginFailMap == null) {
            loginFailMap = Maps.newHashMap();
            CacheUtils.put("loginFailMap", loginFailMap);
        }
        Integer loginFailNum = loginFailMap.get(useruame);
        if (loginFailNum == null) {
            loginFailNum = 0;
        }
        if (isFail) {
            loginFailNum++;
            loginFailMap.put(useruame, loginFailNum);
        }
        if (clean) {
            loginFailMap.remove(useruame);
        }
        return loginFailNum >= 3;
    }
	@RequestMapping(value = "/noAuth", method = RequestMethod.GET)
	public ModelAndView noAuthAction() {
		ModelAndView mav = new ModelAndView("/NoCompetence");

		return mav;
	}
	
    @RequestMapping("/i")
    public String testLogin(ModelMap map, HttpServletRequest req, Paging pa) {
        /*******????????????(?????????)??????****/
        /**** ???????????????????????????1,????????????1?????? ***/
//        if (pa.getNowPage() < 1) {
//            pa.setNowPage(1);
//        }

        /** ????????? **/

//        pa.setTotal(pagingService.getPageCount());

        /** ????????? ?????????????????????????????? **/

//        pa.setCountPage(pa.getTotal() % pa.getPageSize() == 0 ? pa.getTotal() / pa.getPageSize()
//                        : pa.getTotal() / pa.getPageSize() + 1);
//        if (pa.getNowPage() > pa.getCountPage()) {
//            pa.setNowPage(pa.getCountPage());
//        }
//        if (pa.getNowPage() == 0) {
//            pa.setStartIndex(pa.getNowPage() * pa.getPageSize());
//            pa.setEndIndex(pa.getPageSize());
//        } else {
//            pa.setStartIndex((pa.getNowPage() - 1) * pa.getPageSize());
//            pa.setEndIndex(pa.getPageSize());
//        }
//        List<Users> data = pagingService.getPageData(pa);
//        map.put("data", data);
//        map.put("page", pa);
        /*******????????????(?????????)??????****/
        String pageNow = StringHelperTools.nvl(req.getParameter("page"));
        PageVo pageVo = Pager.getPager();
        if ("".equals(pageNow)) {
            pageVo.setPageNow(1);
        } else {
            pageVo.setPageNow(Integer.parseInt(pageNow));
        }
        pageVo.setMessageForPage(10);
        pageVo.setUrl(req.getContextPath() + "/i.html");
        List<User> userLs = pagingService.getPageData(pageVo);
        map = Pager.setPageerToPage(map, pageVo);
        map.put("userLs", userLs);
        map.put("page", pageNow);
        return "/user/paging";
    }

	@RequestMapping(value = "/logout")
	@MethodLog(remark = "????????????")
	public String userlogout(HttpServletRequest request, HttpServletResponse httpresponse) {
		Subject subject = SecurityUtils.getSubject();
		User user =UserUtils.getUser();
		if (user != null) {
			Cookie cookie = new Cookie(user.getUsername(), null);
			cookie.setMaxAge(0); // ???????????????0????????????????????????
			httpresponse.addCookie(cookie);
		}
		if (subject != null)
			subject.logout();
		// request.getSession().invalidate();
		// ??????????????????
		shiroCacheManager.getCache(Constants.authorizationCacheName).clear();

		// org.apache.shiro.web.filter.authc.LogoutFilter
		// ????????????
		UserLoginLog ulog = new UserLoginLog();
		ulog.setType(new Integer(1));
		ulog.setUseraccount(user.getUsername());
		ulog.setIp(TCPIPUtil.getIpAddr(request));
		userLoginLogService.saveLog(ulog);
		return "redirect:login.html";
	}


	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	@RequestMapping("/menus")
	public String getMenus()
	{
        return "/sys/listMenu";
	    
	}
	
	@RequestMapping("/viewAllMenuJson")
    @MethodLog(remark = "????????????????????????")
    public void getViewAllMenuJson(@RequestParam(required = false, defaultValue = "") String name, HttpServletResponse response) {
	  memCachedClient.add("testjava", "hhaha");
	  System.out.println("==========================??????memcached---"+memCachedClient.get("testjava"));
	  CacheUtils.put("testjava", "fasdfasdfasdf");
	  System.out.println("ehcache?????????=================???"+CacheUtils.get("testjava"));
       response.setContentType("text/html;charset=utf-8");

        // shiro????????????????????????????????????????????????????????????????????????
//        Users user = (Users) SecurityUtils.getSubject().getPrincipal();


        List<Menu> listpm = menuService.findMenus(name);
        // ?????????????????????????????????list2???

        List<Menu> list = new ArrayList<Menu>();
        Map<BigDecimal, Menu> map = new HashMap<BigDecimal, Menu>();
        // ?????????????????????????????????list2???
//        HashMap<String, Menu> pmap = new HashMap<String, Menu>();
        // ArrayList<PusMenu> list2 = (ArrayList<TDict>)this.find(hql);

        // ???listpm????????????????????????TreeNode???????????????Map?????????
        for (Menu pm : listpm) {
            Menu node = new Menu();
            node.setId(pm.getId());
            node.setName(pm.getName());
            node.setCreateTime(pm.getCreateTime());
            node.setDescription(pm.getDescription());
            node.setMenupId(pm.getMenupId());
            node.setPageUrl(pm.getPageUrl());
            node.setState(pm.getState());
            node.setType(pm.getType());

            // Map<String, String> attributes = new HashMap<String, String>();
            // attributes.put("url", pm.getPageurl());

            // node.setAttribute(attributes);
            // if (pm.getPm() != null) {
            // node.setParentId(pm.getPm().getId());
            // }

            map.put(pm.getId(), node);
            if (pm.getPm()==null) {
                pm.setPm(menuService.getById(pm.getMenupId()));
            }
        }

        // ??????listpm?????????????????????????????????????????????????????????List

        for (Menu pm : listpm) {

            if (pm.getPm() != null) {

                if (pm.getPm().getId() == null) {
                    list.add((Menu) map.get(pm.getId()));
                } else {

                    BigDecimal pidInteger = pm.getPm().getId();
                    Menu pnode = (Menu) map.get(pidInteger);
                    Menu cnode = (Menu) map.get(pm.getId());

                    if (pnode == null) { // ????????????????????????????????????????????? ?????????????????? ?????? ??????????????????
                                            // ????????????????????????
                        pnode = new Menu();
                        pnode.setId(new BigDecimal(0));
                        pnode.addChild(cnode);
                        list.add((Menu) map.get(pm.getId()));
                    } else {
                        pnode.addChild(cnode);
                    }
                    // if (cnode != null) {
                    //
                    // list.add((PusMenuTreegridBean) map.get(pm.getId()));
                    // }
                    // if(pnode==null){
                    // pnode=new PusMenuTreegridBean();
                    // pnode.setId(0);
                    // }
                    // pnode.addChild(cnode);
                    // if (pnode != null) {
                    // pnode.addChild(cnode);
                    // }
                    // if (!StringUtil.stringIsNull(name).equals("")) {
                    // list.add((PusMenuTreegridBean) map.get(pm.getId()));
                    // }
                }

            } else {
                list.add((Menu) map.get(pm.getId()));

            }

        }

        // Map mapend = new HashMap<Integer, PusMenuTreegridBean>();
        //
        // for (int i = 0; i < list.size(); i++) {
        // int id = list.get(i).getId();
        // // PusMenuTreegridBean pb=
        // mapend.put(id, list.get(i));
        // }
        //
        // List<PusMenuTreegridBean> listend = new
        // ArrayList<PusMenuTreegridBean>();
        // Set set = mapend.keySet();
        //
        // for (Iterator iter = set.iterator(); iter.hasNext();) {
        // Integer key = (Integer) iter.next();
        // PusMenuTreegridBean value = (PusMenuTreegridBean) map.get(key);
        // listend.add(value);
        // // System.out.println(key+"===="+value);
        // }

        // List<PusMenuTreegridBean> listend=new
        // ArrayList<PusMenuTreegridBean>();
        // for (Object value : mapend.values()) {
        // listend.add(value.)
        // }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
        gsonBuilder.registerTypeAdapter(Timestamp.class, new TimestampTypeAdapter());
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(list);
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write(json);
        out.flush();
    }
	
	
	@RequestMapping(value = "userIpMap")
    public ModelAndView userIpMap(HttpServletRequest request)
    {
        ModelAndView view = new ModelAndView("highchats/userIPMapView");
        List<String> countrys = RedisListAPIUtil.queryListData("IpMapCountry");
        Map<String, Long> userIpMapData = new HashMap<String, Long>();
        for (String country : countrys)
        {
            List<String> citys = RedisListAPIUtil.queryListData("IpMapCity" + country);
            for (String city : citys)
            {
                // ????????????????????????
                List<String> last15Days = getLast15Days("yyyyMMdd");
                Long count = 0L;
                for (String last15Day : last15Days)
                {
                    Map<String, String> cityIpCount = RedisMapAPIUtil.hgetAll(last15Day + "IpMapCityData" + city);
                    Entry<String, String> entry = null;
                    for (Iterator<Entry<String, String>> iterator = cityIpCount.entrySet().iterator(); iterator
                            .hasNext();)
                    {
                        entry = iterator.next();
                        count += Long.valueOf(entry.getValue());
                    }
                }
                if (city.contains("_"))
                {
                    userIpMapData.put(city.split("_")[1], count);
                } else
                {
                    userIpMapData.put(city, count);
                }
            }
        }
        view.addObject("userIpMapData", JSON.toJSONString(userIpMapData));
        return view;
    }
	/**
     * @description:??????????????????????????????15?????????
     * @author: Wind-spg
     * @param pattern
     *            ????????????
     * @return
     */
    protected List<String> getLast15Days(String pattern)
    {
        List<String> result = new ArrayList<String>();
        DateFormat format = new SimpleDateFormat(pattern);
        for (int i = 14; i >= 0; i--)
        {
            result.add(format.format(new Date(System.currentTimeMillis() - (i * (24 * 60 * 60 * 1000)))));
        }
        return result;
    }
    
    @Autowired  
    private RequestMappingHandlerMapping requestMappingHandlerMapping;  
  
    @RequestMapping(value = "/admin/util/url2controller")  
    @ResponseBody  
    public void list(HttpServletResponse response) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("URL").append("--").append("Class").append("--").append("Function").append('\n');  
  
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();  
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {  
            RequestMappingInfo info = m.getKey();  
            HandlerMethod method = m.getValue();  
            sb.append(info.getPatternsCondition()).append("--");  
            sb.append(method.getMethod().getDeclaringClass()).append("--");  
            sb.append(method.getMethod().getName()).append('\n');  
        }  
  
        PrintWriter writer = null;  
        try {  
            writer = response.getWriter();  
            writer.print(sb.toString());  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            writer.close();  
        }  
  
    }  
    
    @RequestMapping(value = "/testftl")  
    public String testftl() {  
    	 Collection<Session> sessions =  sessionDao.getActiveSessions();
    	 for (Session session : sessions) {
			System.out.println(principal(session));
		}
    	return "/ftl/mytest";
    }
    
    @RequestMapping(value = "/testft")  
    public String testft() {
    	return "/ftl/ttt";
    }
    
    public static String principal(Session session) {
        PrincipalCollection principalCollection =
                (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);

        return ((Principal) principalCollection.getPrimaryPrincipal()).getName();
    }
    @Autowired
	private FreeMarkerConfig freeMarkerConfig;//??????FreemarkerConfig?????????
    
    
    @RequestMapping("/ttt")
	public String ttt(HttpServletRequest request,HttpServletResponse response,ModelMap mv) throws IOException, TemplateException, ServletException{
		String fileName ="ftl/ttt";
		String FREEMARKER_PATH="WEB-INF/webPage/";
		Boolean flag =(Boolean)FreeMarkerUtil.htmlFileHasExist(request, FREEMARKER_PATH, fileName+".ftl").get("exist");
		if(!flag){//??????????????????????????????????????????
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("user", "xiaowang??????");//?????????????????????????????????
			mv.addAllAttributes(map);
	        FreeMarkerUtil.createHtml(freeMarkerConfig, "demo.ftl", request, map, FREEMARKER_PATH, fileName+".ftl");//??????????????????????????????
		}
		return fileName;//?????????????????????HTML??????
	}
    
    @RequestMapping(value = "/sysloglist", method = RequestMethod.GET)
	public ModelAndView userloginlogAction() {
		ModelAndView mav = new ModelAndView("sys/log/listSysLog");
		return mav;
	}
    
    @RequestMapping(value = "/sysloglist", method = RequestMethod.POST)
	@MethodLog(remark = "??????????????????")
	public ModelAndView getUserloginlogJson(
			@RequestParam(required = false, defaultValue = "") String account,
			@RequestParam(required = false, defaultValue = "") int page,
			@RequestParam(required = false, defaultValue = "") int rows,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> mapjson = new HashMap<String, Object>();
		PageVo pages = new PageVo();
		pages.setPageNow(page);
		pages.setMessageForPage(rows);

		mapjson.put("total", userLoginLogService
				.querySysLogPagesByAccount(account, pages).getTotalCount());

		List<?> list = userLoginLogService.querySysLogPagesByAccount(account, pages).getUserLs();
		List<Syslog> endlist = new ArrayList<Syslog>();

		for (int i = 0; i < list.size(); i++) {
			Syslog endsyslog = (Syslog) list.get(i);
			String[] d=endsyslog.getMethodName().toString().split("\\.");
			String v=d[d.length-2]+"."+d[d.length-1];
			endsyslog.setMethodName(v);
			endlist.add(endsyslog);
		}

		mapjson.put("rows", endlist);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(mapjson);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(json);
		out.flush();
		return null;
	}
    @RequestMapping(value="sysmonitor",method=RequestMethod.GET)
    public ModelAndView sysmonitor(){
    	ModelAndView mv=new ModelAndView("sys/sysmonitor");
		return mv;
    	
    }
}
