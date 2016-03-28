# fancy
  // @Resource注解
	// @Resource注解和@Autowired一样，也可以标注在字段或属性的setter方法上
	// @Resource默认按名称装配，名称可以通过name属性指定。当找不到与名称匹配的bean时，才会按类型装配
	// 若注解标注在字段上且未指定name属性，则默认取字段名作为bean名称寻找依赖对象
	// 若注解标注在setter上且未指定name属性，则默认取属性名作为bean名称寻找依赖对象
	// 如果没有指定name属性，并且按照默认的名称仍找不到依赖对象时，它就会按类型匹配
	// 但只要指定了name属性，就只能按名称装配了
	//
	// @Autowired注解
	// @Autowired默认是按类型装配对象的，默认情况下它要求依赖对象必须存在
	// 如果允许null值，可以设置它的required属性为FALSE，如@Autowired(required=false)
	// 若想要按名称装配，可以结合@Qualifier注解一起使用，如@Autowired(required=false)
	推荐使用：@Resource注解在字段上，这样就不用写setter方法了，并且这个注解是属于J2EE的，减少了与spring的耦合。这样代码看起就比较优雅。
	
	FancyRSB
	@description:Remote interface到Service 连接不到抛Hession to Interface cn.telling.XXXInterfae之类的错
	
	
	本项目中的interface和service是模块化的，可以有多个
	
	Fancy基于SpringMVC+Spring Hessian+Maven+Shiro+Memecached+Freemaker+JSP+JqueryUI的可扩展的模块化项目
	
	
	
	web-app下的active_edit.jsp 用于测试图片上传功能  不经过action控制器 直接访问jsp
	
	web-app下的ExceptionDemo.jsp测试异常页面的跳转和处理测试  
	ajaxFileUpload是一个异步上传文件的jQuery插件。
	
	hessian 版本3.0.37
