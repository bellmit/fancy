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
用于手动加载Fancyserver项目的启动并注册FancyServer接口抛出hessian接口
FancyServer依赖FancyRsb
@description:Remote interface到Service 连接不到抛Hession to Interface cn.telling.XXXInterfae之类的错


本项目中的interface和service是模块化的，可以有多个

Fancy可以独立运行，只是不能掉用FancyInterface接口使用
Fancy基于SpringMVC+Spring Hessian+Maven+Shiro+Memecached+Freemaker+JSP+JqueryUI的可扩展的模块化项目



web-app下的active_edit.jsp 用于测试图片上传功能  不经过action控制器 直接访问jsp

web-app下的ExceptionDemo.jsp测试异常页面的跳转和处理测试  
ajaxFileUpload是一个异步上传文件的jQuery插件。

本项目使用的hessian 版本3.0.37

maven安装方法:
mvn install:install-file -DgroupId=com.danga -DartifactId=ojdbc14 -Dversion=1.6 -Dpackaging=jar -Dfile=F:/.m2/repository/com/danga/memcached_client/1.6/java_memcached-release_1.6.jar

Fancy可以单独运行，但是连接不到hessian服务

加lib包是扫描，但不运行
部署是发布和运行项目

spring intercept配置
<mvc:interceptors>
		<bean class="cn.telling.common.PurviewIntercept" />
	</mvc:interceptors>



log4j level: TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF



Spring支持5种作用域：
   Singleton：单例模式。在整个SpringIoC容器中，使用singleton定义的Bean将只有一个实例。
   Prototype：原型模式。每次通过容器的getBean方法获取prototype定义的Bean时，都将产生一个新的Bean实例。
   request：对于每次HTTP请求，使用request定义的Bean都将产生一个新的实例，即每次HTTP请求都会产生不同的Bean实例。当然只有在WEB应用中使用Spring时，该作用域才真正有效。
session：对于每次HTTPSession，使用session定义的Bean都将产生一个新的实例时，即每次HTTP Session都将产生不同的Bean实例。同HTTP一样，只有在WEB应用才会有效。
global session：每个全局的HTTPSession对应一个Bean实例。仅在portlet Context的时候才有效。

    比较常用的singleton和prototype。如果一个Bean实例被设置为singleton，那么每次请求该Bean时都会获得相同的实例。容器负责跟踪Bean实例的状态，负责维护Bean实例的生命周期行为。如果一个Bean实例被设置为prototype，那么每次请求该di的Bean，Spring都会创建一个新的Bean实例返回给程序，在这种情况下，Spring容器仅仅使用new关键字创建Bean实例，一旦创建成功，容器将不会再跟踪实例，也不会维护Bean实例的状态。
如果我们不指定Bean的作用域，则Spring会默认使用singleton作用域。
Java在创建Java实例时，需要进行内存申请。销毁实例时，需要完成垃圾回收。这些工作都会导致系统开销的增加。因此，prototype作用域Bean的创建、销毁代价会比较大。而singleton作用域的Bean实例一旦创建成功，可以重复使用。因此，除非必要，否则尽量避免将Bean的作用域设置为prototype。



Fancy的shiro记住密码注意：
		1.shiro的加密使用javax.crypto.Cipher.getInstance获得实例，不支持64bit的jdk
		2.遇到这个错误不用改环境变量，可以通过配置运行时的jre解决
		   a)tomcat：window->perferences->server->runtime Env--Edit Server--修改JRE为x86版本
		   b)jetty:Debug As--Run Configurations---JRE修改为x86版本
		（此问题为电脑上同时安装了x64、x86版本jdk的伙伴记录）
		结论：需要使用32位jdk和32位tomcat,仅Fancy项目