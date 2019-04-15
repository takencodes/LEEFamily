/**
 * Created by xing.s on 2018/1/25.
 * 图片预览
 * 需要引用layer
 */
$(function () {
    $(document).on("click","img",function(){
        var preview=$(this).attr("preview");
        if(preview==true||preview=='true'){
            var obj="<img src='"+$(this).attr("src")+"' style='max-width: 600px;max-height: 600px;' />";
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                area: ['600px', '600px'],
                shadeClose: true, //点击遮罩关闭
                content: obj
            });
        }
    });
});

//初始化面包屑js
 $(function () {
     var cur_cookie = getCookie("activeMenuNew");
     if (!cur_cookie)return;
     var marr = cur_cookie.split("_");

     //一级菜单名
     var oneLevel = $("#oneLevel_" + marr[0]).attr("data-container");

     //二级菜单名
     var twoLevel = $("#sub_one_" + marr[0]).children("li").eq(marr[1]).attr("data-container");

     //三级菜单名
     var thirdLevel = $("#sub_one_" + marr[0]).children("li").eq(marr[1]).children("ul").children("li").eq(marr[2]).children("a").eq(0).html();


     var breadDev = $("#breadcrumbs");
     var title = $("h3.header.smaller.lighter.blue");
     if (breadDev) {
         $(breadDev).html("<ul class='breadcrumb'>" +
                             "<li>" +
                                "<i class='ace-icon fa fa-home home-icon'></i><a href='welcome.do'>首页</a>" +
                             "</li>" +
                             "<li class='active'>" + oneLevel + "</li>" +
                             "<li class='active'>" + twoLevel + "</li>" +
                             "<li class='active'>" + thirdLevel + "</li>" +
                          "</ul>");
     }

});
