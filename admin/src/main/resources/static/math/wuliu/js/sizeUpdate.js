


layui.extend({tinymce: '/js/tinymce/tinymce'}).use(['jquery', 'form', 'util', 'layer', 'tinymce', 'upload'], function () {
    var $ = layui.jquery;
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        upload = layui.upload,
        laydate = layui.laydate;
    var util = layui.util;
    var tinymce = layui.tinymce;

    laydate.render({
        elem: '#purTime'
    });


    $.ajax({
        url: '/size/getById',
        dataType: 'json',
        type: 'get',
        data:{id:GetQueryString("id")},
        success: function (data) {
            $.each(data.data, function (k, v) {
                $("input[name='" + k + "']").val(v);
                if (k==="purStatus"){
                    $("select[name='" + k + "']").val(v);
                }
            })
            form.render();
        }
    })


    $("#linkDiv").click(function () {
        window.open("http://"+$("#amzLink").val())

    })

//表单渲染
    form.render();
    form.on('submit(doSubmit)', function (data) {
        data.field.id = GetQueryString("id");
        $.ajax({
            url: '/size/update',
            dataType: 'json',
            type: 'get',
            data:data.field,
            success: function (data) {
                if (data.code === 0) {
                    layer.msg("success");
                     window.parent.userTable.reload(null,true);
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                }else {
                    layer.msg(data.msg);
                }
            }
        })
        return false;
    });




});

