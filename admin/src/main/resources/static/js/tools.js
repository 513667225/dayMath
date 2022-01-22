var $ = layui.jquery;


function formatTime(v) {
    let date = new Date(v);
    let y = date.getFullYear();
    let m = date.getMonth() + 1;
    m = m < 10 ? '0' + m : m;
    let d = date.getDate();
    d = d < 10 ? ("0" + d) : d;
    let h = date.getHours() ;
    h = h < 10 ? ("0" + h) : h;
    let M = date.getMinutes();
    M = M < 10 ? ("0" + M) : M;
    let str = y + "-" + m + "-" + d + " " + h + ":" + M;
    return str;
}

function formatDay(v) {
    let date = new Date(v);
    let y = date.getFullYear();
    let m = date.getMonth() + 1;
    m = m < 10 ? '0' + m : m;
    let d = date.getDate();
    d = d < 10 ? ("0" + d) : d;
    let h = date.getHours();
    h = h < 10 ? ("0" + h) : h;
    let M = date.getMinutes();
    M = M < 10 ? ("0" + M) : M;
    let str = y + "-" + m + "-" + d ;
    return str;
}

function GetNumberOfDays(date1,date2){//获得天数
    //date1：开始日期，date2结束日期
    var a1 = new Date(date1);
    var a2 = new Date(date2);
    var day = (a1.getTime() - a2.getTime())/(24 * 60 * 60 * 1000); ;//核心：时间戳相减，然后除以天数
    return parseInt(day)
};



// 窗口自适应
$(window).on('resize', function () {
    AdminInit();
    // iframe窗口自适应
    var $content = $('#nav_xbs_tab .layui-tab-content');
    $content.height($(this).height() - 125);
    $content.find('iframe').each(function () {
        $(this).height($content.height());
    });
}).resize();

function AdminInit() {
    //layui-fluid 为外层div
    $('.layui-fluid').height($(window).height());
    $('body').height($(window).height());
}


function GetQueryString(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    let r = window.location.search.substr(1).match(reg); //获取url中"?"符后的字符串并正则匹配
    let context = "";
    if (r != null)
        context = r[2];
    reg = null;
    r = null;
    return context == null || context == "" || context == "undefined" ? "" : decodeURIComponent(context);
}


layui.use("jquery",function () {
    let _$ = layui.$
    _$(function () {
        // _$.ajax({
        //     url: '/user/getLevel',
        //     type: 'get',
        //     dataType: 'json',
        //     success: function (suc) {
        //         _$('[level]').each(function () {
        //             let _this = this;
        //             let str = _$(_this).attr("level")
        //             let level = suc.data;
        //             if (str.indexOf(level) == -1) {
        //                 _$(_this).remove();
        //             }

        //         })
        //     }
        // })
    })




})




