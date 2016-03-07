/****吕燕  2015.04.17  底部定位通用*****/
function gao_bottomFin(){
    var L_login =  $("#g_login").height();
    var body =  $("body").height();
    var main = $("#main").height();
    var g_mengCeng1 = $(".g_mengCeng1").height();
    var he = main + L_login;
    // $(".g_weiIndex").height(body);
    if (he > body){
        //$("#main").removeClass("gao_bottomFin2");
        $("#main").attr("class", "gao_bottomFin");
        var aa= $(".g_mask").css("height",he);

    }else{
        $("#main").addClass("gao_bottomFin2");
        var aa= $(".g_mask").css("height",g_mengCeng1);
        $(".g_weiIndex").height(body);
    }
}