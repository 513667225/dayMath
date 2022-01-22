var signAudit;
var orderCode;
var fnsku;
layui.use(['table', 'form','upload','laydate'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var form = layui.form
        , layer = layui.layer
        , layedit = layui.layedit
        , laydate = layui.laydate;

    var uploadInst = layui.upload.render({
        elem: '#test1' //绑定元素
        ,url: '/out/uploadInfo' //上传接口
        ,accept:"file",
        acceptMime:"file/*",
        size:"1024*30",
        exts:"xls|xlsx"
        ,done: function(res){
            layer.msg(res.msg)
        }
        ,error: function(res){
            layer.error(res.msg);
        }
    });


    laydate.render({
        elem: '#times'
        // trigger: 'click'
    });

    $("#downloadFba").click(function(){
        var form=$("<form>");
        form.attr("style","display:none");
        form.attr("target","");
        form.attr("method","post");//提交方式为post
        form.attr("action","/sku/downloadFile?fileName=out.xlsx");//定义action
        $("body").append(form);
        form.submit();
    })

    form.render();


    init();

    form.on('submit(serchBtn)', function (data) {
        var times = data.field.times;
        var asin = data.field.asin;
        var user = data.field.user;
        var country = data.field.country;
        table.reload('taskId', {
            method: 'get',
            where: {
                asin: data.field.asin,
                times: data.field.times,
                user: data.field.user,
                country: data.field.country
            },
            page: {
                curr: 1
            }
        });
        // $("#times").val(times);
        $("#asin").val(asin);
        $("#user").val(user);
        $("#country").val(country);
        layui.form.render("select");
        // form.render();
        return false;
    })



    userTable = table.render({
        elem: '#goods_table',
        id: 'taskId',
        url: '/out/list',
        method: 'get',
        toolbar: '#toolbarDemo',
        cols: [[
            {type: 'checkbox'},
            {field: 'outId', title: 'ID'},
            {field: 'times', title: '时间', sort: true,templet:function (data){
                    return formatDay(data.times);
                }},
            {field: 'asin', title: 'asin', sort: true},
            {field: 'parentasin', title: 'PARENTASIN', sort: true, align: 'center'},
            {field: 'msku', title: 'MSKU', sort: true, align: 'center'},
            {field: 'sku', title: 'SKU', sort: true, align: 'center'},
            {field: 'title', title: '标题', sort: true, align: 'center'},
            {field: 'name', title: '品名', sort: true, align: 'center'},
            {field: 'user', title: '负责人', sort: true, align: 'center'},
            {field: 'type', title: '分类', sort: true, align: 'center'},
            {field: 'lable', title: '标签', sort: true, align: 'center'},
            {field: 'shop', title: '店铺', sort: true, align: 'center'},
            {field: 'country', title: '国家', sort: true, align: 'center'},
            {field: 'fba', title: 'fba可售', sort: true, align: 'center'},
            {field: 'saleVal', title: '销量', sort: true, align: 'center'},
            {field: 'orderCount', title: '订单量', sort: true, align: 'center'},
            // {fixed: 'right', width: 180, align: 'center', toolbar: '#barDemo'}
        ]],
        page: true,
        limits: [10, 1000],
        limit: 10,
        loading: true,
        count: 1000,
        done: function (res) {
            tableIds = res.data.map(function (value) {
                return value.wuliuId;
            });
            // 设置当前页选中项
            $.each(res.data, function (idx, val) {
                if (ids.indexOf(val.wuliuId) > -1) {
                    val["LAY_CHECKED"] = 'true';
                    //找到对应数据改变勾选样式，呈现出选中效果
                    let index = val['LAY_TABLE_INDEX'];
                    $('tr[data-index=' + index + '] input[type="checkbox"]').click();
                    form.render('checkbox'); //刷新checkbox选择框渲染
                }
            });
            // 获取表格勾选状态，全选中时设置全选框选中
            let checkStatus = table.checkStatus('test');
            if (checkStatus.isAll) {
                $('.layui-table-header th[data-field="0"] input[type="checkbox"]').prop('checked', true);
                form.render('checkbox'); //刷新checkbox选择框渲染
            }
        },
        parseData: function (res) {
            return {
                "code": res.code,
                "msg": res.msg,
                "count": res.total,
                "data": res.data
            };
        },
        request: {
            pageName: 'page',
            limitName: 'limit'
        },
    });

    // 设置全局变量以保存选中行信息
    let ids = new Array();
    // 保存当前页全部数据id，点击全选时使用
    let tableIds = new Array();


    table.on('toolbar(goods_table)', function (obj) {
        var data = obj.data;
        var tt = $("#shippingPostalCode").val();

        if (obj.event === 'add') {
            let strArr = "";
            $(ids).each(function (i, o) {
                strArr += o + ",";
            });
            $.ajax({
                url: '/wuliu/update',
                dataType: 'json',
                type: 'get',
                data: {ids: strArr},
                success: function (data) {
                    if (data.code === 0) {
                        layer.msg("操作成功");
                        userTable.reload(null, true);
                        $("#shippingPostalCode").val(tt);
                    } else {
                        layer.msg(data.msg);
                    }
                }
            })
            // layer.full(index);
        } else if (obj.event === 'money') {
            var index = layer.open({
                type: 2,
                title: '上传数据',
                area: ['1000px', '800px'],
                shadeClose: true,
                content: "myTaskDetail.html"
            });
        }
    });


    table.on('checkbox(goods_table)', function (obj) {
        if (obj.checked == true) {
            if (obj.type == 'one') {
                ids.push(obj.data.wuliuId);
            } else {
                for (let i = 0; i < tableIds.length; i++) {
                    //当全选之前选中了部分行进行判断，避免重复
                    if (ids.indexOf(tableIds[i]) == -1) {
                        ids.push(tableIds[i]);
                    }
                }
            }
        } else {
            if (obj.type == 'one') {
                let i = ids.length;
                while (i--) {
                    if (ids[i] == obj.data.wuliuId) {
                        ids.splice(i, 1);
                    }
                }
            } else {
                let i = ids.length;
                while (i--) {
                    if (tableIds.indexOf(ids[i]) != -1) {
                        ids.splice(i, 1);
                    }
                }
            }
        }
    });

    function table() {

        return userTable;
    }


    table.on('tool(goods_table)', function (obj) {
        var data = obj.data;
        if (obj.event === 'look') {
            var index = layer.open({
                type: 2,
                title: '修改订单',
                area: ['1000px', '800px'],
                shadeClose: true,
                content: "orderUpdate.html?id=" + data.shipId
            });
        } else if (obj.event === 'audit') {
            $.ajax({
                url: '/fba/audit',
                dataType: 'json',
                type: 'get',
                data: {fbaId: data.fbaId},
                success: function (data) {
                    if (data.code === 0) {
                        layer.msg("操作成功");
                        userTable.reload(null, true);
                    } else {
                        layer.msg(data.msg);
                    }
                }
            })
        } else if (obj.event === 'del') {
            layer.confirm('真的删除ID为：'+data.shipId+"的数据吗?", function(index){
                //console.log(data.p_id);
                //向服务端发送删除指令
                $.ajax({
                    url:'/ship/del',
                    type:'get',
                    data:{'id':data.shipId},//向服务端发送删除的id
                    success:function(suc){
                        if(suc.code===0){
                            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            layer.close(index);
                            layer.msg("删除成功",{icon:1});
                        }
                        else{
                            layer.msg("删除失败",{icon:5});
                        }
                    }
                });
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            var index = layer.open({
                type: 2,
                title: '特别佣金',
                area: ['1000px', '800px'],
                shadeClose: true,
                content: "updateFba.html?id=" + data.fbaId
            });
        }
    });
})


function init() {
    $.ajax({
        url: '/country/list',
        dataType: 'json',
        type: 'get',
        success: function (data) {
            $('#country').append(new Option('全部', ''));
            $.each(data.data, function (index, item) {
                let f = false;
                if (item.countryId == countryName) {
                    f = true;
                }
                $('#country').append(new Option(item.countryName, item.countryId, false, f));// 下拉菜单里添加元素
            });
            layui.form.render("select");
        }
    })

    $.ajax({
        url: '/type/list',
        dataType: 'json',
        type: 'get',
        success: function (data) {
            $('#type').append(new Option('全部', ''));
            $.each(data.data, function (index, item) {
                let f = false;
                if (item.typeId == typeName) {
                    f = true;
                }
                $('#type').append(new Option(item.typeName, item.typeId, false, f));// 下拉菜单里添加元素
            });
            layui.form.render("select");
        }
    })
}

function showImg(url) {
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: ['1000px', '800px'],
        skin: 'layui-layer-nobg',
        shadeClose: true,
        content: "<img src='" + url + "'>"
    });
}
