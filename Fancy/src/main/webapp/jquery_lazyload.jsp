<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>启用插件 - jQuery延迟加载插件(懒加载) - jquery.lazyload.js</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="author" content="http://www.zjgsq.com/">
    <meta name="viewport" content="width=device-width" />
    <link rel="stylesheet" href="http://www.w3cways.com/demo/LazyLoad/css/style.css" />
    <!--[if IE]>
	<script src="js/html5.js"></script>
	<![endif]-->
</head>

<body>
    <section class="container">
          <div class="content-wrap">
            <h2>启用插件</h2>
            <p>
                不在可视范围内的图片不会加载。滚动时按需加载。请清空缓存重新测试一下。</p>
             <p>可以如下demo页面作比较:
                <a href="disabled.html">禁用插件</a>，<a href="enabled_fadein.html">淡入效果</a>，<a href="enabled_wide.html">水平滚动</a>，<a href="enabled_wide_container.html">容器内水平滚动</a>。

            </p>

            <pre class="prettyprint">$("img.lazy").lazyload();</pre>
            <pre class="prettyprint">&lt;img class="lazy" data-original="img/example.jpg" width="765" height="574"&gt;</pre>

            <div id="container">
                <img class="lazy img-responsive" data-original="http://www.w3cways.com/demo/LazyLoad/img/bmw_m1_hood.jpg" width="765" height="574" alt="BMW M1 Hood">
                <br/>
                <img class="lazy img-responsive" data-original="http://www.w3cways.com/demo/LazyLoad/img/bmw_m1_side.jpg" width="765" height="574" alt="BMW M1 Side">
                <br/>
                <img class="lazy img-responsive" data-original="http://www.w3cways.com/demo/LazyLoad/img/viper_1.jpg" width="765" height="574" alt="Viper 1">
                <br/>
                <img class="lazy img-responsive" data-original="http://www.w3cways.com/demo/LazyLoad/img/viper_corner.jpg" width="765" height="574" alt="Viper Corner">
                <br/>
                <img class="lazy img-responsive" data-original="http://www.w3cways.com/demo/LazyLoad/img/bmw_m3_gt.jpg" width="765" height="574" alt="BMW M3 GT">
                <br/>
                <img class="lazy img-responsive" data-original="http://www.w3cways.com/demo/LazyLoad/img/corvette_pitstop.jpg" width="765" height="574" alt="Corvette Pitstop">
                <br/>
            </div>
        </div>
    </section>

    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>s
    <script src="Plug/lazyload/jquery.lazyload.js"></script>
    <script type="text/javascript">
        $(function () {
        	$("img.lazy").lazyload({
        		  placeholder : "https://www.baidu.com/img/bd_logo1.png", //用图片提前占位
        		    // placeholder,值为某一图片路径.此图片用来占据将要加载的图片的位置,待图片加载时,占位图则会隐藏
        		  effect: "fadeIn", // 载入使用何种效果
        		    // effect(特效),值有show(直接显示),fadeIn(淡入),slideDown(下拉)等,常用fadeIn
        		  threshold: 0, // 提前开始加载
        		    // threshold,值为数字,代表页面高度.如设置为200,表示滚动条在离目标位置还有200的高度时就开始加载图片,可以做到不让用户察觉
        		  event: 'click',  // 事件触发时才加载
        		    // event,值有click(点击),mouseover(鼠标划过),sporty(运动的),foobar(…).可以实现鼠标莫过或点击图片才开始加载,后两个值未测试…
        		  container: $("#container"),  // 对某容器中的图片实现效果
        		    // container,值为某容器.lazyload默认在拉动浏览器滚动条时生效,这个参数可以让你在拉动某DIV的滚动条时依次加载其中的图片
        		  failurelimit : 10 // 图片排序混乱时
        		     // failurelimit,值为数字.lazyload默认在找到第一张不在可见区域里的图片时则不再继续加载,但当HTML容器混乱的时候可能出现可见区域内图片并没加载出来的情况,failurelimit意在加载N张可见区域外的图片,以避免出现这个问题.
        		});
        });
    </script>
</body>

</html>