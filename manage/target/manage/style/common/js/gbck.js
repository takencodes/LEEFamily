function setSessionCookie(name, value) {
    document.cookie = name + "=" + value + ";path=/";
}
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else {
        return null;
    }
}
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}
$(function () {
    $('form').each(function () {
        $(this).validate({
            onfocusout: function (element) {
                $(element).valid();
            },
            showErrors: function (map, list) {
                if (list.length > 0) {
                    layer.tips(list[0].message, list[0].element, {tips: 1, time: 2000, tipsMore: false});
                }
            }
        });
    });
    $("a.history-back").click(function (e) {
        e.preventDefault();
        window.history.back();
    });
    $('.chosen-select').chosen({allow_single_deselect: true, search_contains: true, disable_search_threshold: 10});

    $('.show-details-btn').on('click', function (e) {
        e.preventDefault();
        $(this).closest('tr').next().toggleClass('open');
        $(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
    });

    $('.one-li-cur').on('click', function (e) {
        e.preventDefault();
        var $this = $(this);
        var $parent = $this.parent("li");
        var one = $this.data("one-menu");
        console.log("one is " + one);
        setSessionCookie("activeMenuNew", one + "_0_0");
        var $sub = $("#sub_one_" + one);
        $sub.siblings("ul").hide();
        $sub.show();
        $parent.siblings("li").removeClass("active");
        $parent.addClass("active");
    });

    $('.navable-a-tag').on('click', function (e) {
        e.preventDefault();
        var $this = $(this);
        setSessionCookie("activeMenu", $this.data("main-menu") + "_" + $this.data("sub-menu"));

        var activeMenuNew = $this.data("one-menu") + "_" + $this.data("main-menu") + "_" + $this.data("sub-menu");

        console.log(activeMenuNew);

        setSessionCookie("activeMenuNew",activeMenuNew);

        if ($this.data("target") == '_blank') {
            window.open($this.data('href'));
        } else {
            window.location.href = $this.data('href');
        }
    });

    $("body").on("click", "table thead input.select-all-toggle", function () {
        var $this = $(this);
        var targetFunction = $(this).data("select-all-toggle-callback");
        var $col = $this.closest("th").get(0) || $this.closest("td").get(0);
        var checked = this.checked;
        $this.closest("table").find("tbody tr").each(function () {
            var targetCheckbox = $(this).find("td:eq(" + $col.cellIndex + ") input[type='checkbox']").get(0);
            if (targetCheckbox) {
                targetCheckbox.checked = checked;
                window[targetFunction](targetCheckbox);
            }
        });
    });

    initMenu();
});

/**
 * cookie失效默认展示第一个大类的菜单
 */
function initMenu() {
    var cur_cookie = getCookie("activeMenuNew");
    console.log(cur_cookie);
    if (!cur_cookie) {
        console.log("无可用cookie");
        $("#one_nav li").removeClass("active");
        $("#one_nav li").eq(0).addClass("active");
        $(".one-nav-one").hide();
        $(".one-nav-one").eq(0).show();
    } else {
        console.log("cookie is " + cur_cookie);
        var menu=cur_cookie.split("_");
        if(menu[0]&&parseInt(menu[0])>$("#one_nav > li").length){
            $("#one_nav li").eq(0).addClass("active");
            $(".one-nav-one").hide();
            $(".one-nav-one").eq(0).show();
        }
    }
}

$.validator.addMethod("regExp", function (value, element) {
    var pattern = $(element).attr("pattern");
    return pattern && new RegExp(pattern).test(value);
});
//手机号码
$.validator.addMethod("isMobile", function(value, element){
    var reg = /^1\d{10}$/; //^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
    return this.optional(element) || (reg.test(value));
}, "请输入正确的手机号码");
$.validator.addClassRules({
    regExp: {
        regExp: "Just do it"
    }
});
jQuery.validator.messages.regExp = '格式不正确';
(function ($) {
    $.extend($.validator.messages, {
        required: "必填字段",
        remote: "请修正该字段",
        email: "请输入正确格式的电子邮件",
        url: "请输入合法的网址",
        date: "请输入合法的日期",
        dateISO: "请输入合法的日期 (ISO).",
        number: "请输入合法的数字",
        digits: "只能输入正整数",
        creditcard: "请输入合法的信用卡号",
        equalTo: "请再次输入相同的值",
        accept: "请输入拥有合法后缀名的字符串",
        maxlength: $.validator.format("请输入一个长度最多是 {0} 的字符串"),
        minlength: $.validator.format("请输入一个长度最少是 {0} 的字符串"),
        rangelength: $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
        range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
        max: $.validator.format("请输入一个最大为 {0} 的值"),
        min: $.validator.format("请输入一个最小为 {0} 的值")
    });
}(jQuery));