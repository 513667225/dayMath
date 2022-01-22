var goodsGrallyList = {};
goodsGrallyList.title = "xxx";
goodsGrallyList.id = "xxx";
goodsGrallyList.start = 0;
var dataArray = new Array();
var specificationPicFilePath;
var picUrlFilePathFilePath;


layui.extend({tinymce: '/js/tinymce/tinymce'}).use(['jquery', 'form', 'util', 'layer', 'tinymce', 'upload'], function () {
    var $ = layui.jquery;
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        upload = layui.upload,
        laydate = layui.laydate;
    var util = layui.util;
    var tinymce = layui.tinymce;





//表单渲染
    form.render();
    form.on('submit(doSubmit)', function (data) {
        $.ajax({
            url: '/user/register',
            dataType: 'json',
            type: 'get',
            data:data.field,
            success: function (data) {
                if (data.code === 0) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                    layer.msg("success");
                }else {
                    layer.msg(data.msg);
                }
            }
        })
        return false;
    });


    //初始化
    init();


    //加载商品分类下拉框
    function init() {
        $.ajax({
            url: '/user/listType',
            dataType: 'json',
            type: 'get',
            success: function (data) {
                $.each(data.data, function (k, v) {

                    $('#remark').append(new Option(k, v));

                });
                layui.form.render("select");
            }
        })
    }



});

