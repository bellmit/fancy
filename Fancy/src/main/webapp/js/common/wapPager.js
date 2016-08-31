/**
 * 分页js
 * @author hunj
 * @version 1.0
 * @since 2014年12月4日
 */

var currPage = 0; //当前页
var endPage = 0; //最后一页
var pageSize = 10;//每页条数
var totalCount = 0; //总条数
var pagerUrl;//请求的Url
var pagerContext; //分页列表父元素
var pagerParams; //分页需要的参数
var pagerCallback;//分页成功后的回调函数

/**
 * 分页
 * @param url 请求的url
 * @param context jquery对象，（存放分页html的容器）
 * @param params 分页的参数，可以为空
 * @param pageNum 当前页
 * @param callback 回调函数
 */
function loadPageList(url, context, params, pageNum, callback) {
    pagerUrl = url;//请求的Url
    pagerContext = context; //分页列表父元素
    pagerParams = params; //分页需要的参数
    if (pageNum == undefined)
        pageNum = 1;
    pagerCallback = callback;
    /*var getUrl;
    if (url.indexOf("?") > 0)
        getUrl = ctx + url + "&&pageNow=" + pageNum + "&&messageForPage=" + pageSize;
    else
        getUrl = ctx + url + "?pageNow=" + pageNum + "&&messageForPage=" + pageSize;*/
    params["pageNow"]=pageNum;
    var resultHtml = ajaxPost(url, params);
    if (pageNum == 1) {
        context.empty();
    }
    if (resultHtml) {
        $(resultHtml).each(function () {
            if ($(this).is("input")) {
                var name = $(this).attr("name");
                if (name == "java_pager_totalCount")
                    totalCount = $(this).val();
                if (name == "java_pager_pageNow")
                    currPage = parseInt($(this).val());
                if (name == "java_pager_endPage")
                    endPage = parseInt($(this).val());
            }
        })
        if (totalCount && totalCount > 0) {
            context.append(resultHtml);
            ///如果传递函数 则调用
            if (typeof pagerCallback != 'undefined' && pagerCallback instanceof Function) {
                pagerCallback();
            }
        } else {
            if (pageNum == 1) {
                context.append("<div style='text-align:center;width: 100%;text-align: center'><img src='"+ctx+"/skin/mobile/images/nodata.png'/> </div>");
            }
        }
    }
    
}

/**
 *滚动加载
 */
$(window).scroll(function () {
    var scrollTop = $(this).scrollTop();
    var scrollHeight = $(document).height();
    var windowHeight = $(this).height();
    if (scrollTop + windowHeight == scrollHeight) {
        var nextPage = parseInt(currPage) + 1;
        if (nextPage <= endPage) {
            loadPageList(pagerUrl, pagerContext, pagerParams, nextPage, pagerCallback);
        }
    }
});