var productName;
var account;
var wname;
layui.use(['table', 'form','upload'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var form = layui.form
        , layer = layui.layer
        , layedit = layui.layedit
        , laydate = layui.laydate;

    var uploadInst = layui.upload.render({
        elem: '#test1' //绑定元素
        ,url: '/sale/uploadInfo' //上传接口
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


    $("#downloadGoods").click(function(){
        var form=$("<form>");
        form.attr("style","display:none");
        form.attr("target","");
        form.attr("method","post");//提交方式为post
        form.attr("action","/out/downloadFile?fileName=in.xlsx");//定义action
        $("body").append(form);
        form.submit();
    })
    var uploadInst = layui.upload.render({
        elem: '#test3' //绑定元素
        ,url: '/online/upload1' //上传接口
        ,accept:"file",
        acceptMime:"file/*",
        size:"1024*30",
        exts:"csv"
        ,done: function(res){
            layer.msg(res.msg)
        }
        ,error: function(res){
            layer.error(res.msg);
        }
    });



    var uploadInst = layui.upload.render({
        elem: '#test2' //绑定元素
        ,url: '/online/uploadAdd' //上传接口
        ,accept:"file",
        acceptMime:"file/*",
        size:"1024*30",
        exts:"xlsx|xls"
        ,done: function(res){
            layer.msg(res.msg)
        }
        ,error: function(res){
            layer.error(res.msg);
        }
    });

    $("#downloadFba").click(function(){
        var form=$("<form>");
        form.attr("style","display:none");
        form.attr("target","");
        form.attr("method","post");//提交方式为post
        form.attr("action","/sku/downloadFile?fileName=fba.xlsx");//定义action
        $("body").append(form);
        form.submit();
    })

    form.render();


    init();

    form.on('submit(serchBtn)', function (data) {
        table.reload('taskId', {
            method: 'get',
            where: {
                fnsku: data.field.fnsku,
                account: data.field.account,
                wname: data.field.wname,
                productName: data.field.productName,
                asin: data.field.asin
            },
            page: {
                curr: 1
            }
        });
        wname=data.field.wname;
        account= data.field.account;
        productName =data.field.productName;
        init();
        $("#fnsku").val(data.field.fnsku);
        $("#asin").val(data.field.asin);

        layui.form.render("select");
        return false
    })


    userTable = table.render({
        elem: '#goods_table',
        id: 'taskId',
        url: '/fba/fbaStock/fbaList',
        method: 'get',
        height: 'full-80',
        toolbar: '#toolbarDemo',
        cols: [[
            {type: 'checkbox'},
            {field: 'wname', title: '仓库', sort: true},
            {
                field: 'productImage',height:200, title: '图片', align: 'center',
                templet: function (data) {
                    var url = data.productImage;
                    return "<img   src='" + url + "' style='height:35px;width=35px'>";
                }
            },
            {field: 'asin', title: 'ASIN', sort: true, align: 'center'},
            {field: 'msku', title: 'MSKU', sort: true, align: 'center'},
            {field: 'fnsku', title: 'FNSKU', sort: true, align: 'center'},
            {field: 'productName', title: '品名', sort: true, align: 'center'},
            {field: 'sku', title: 'SKU', sort: true, align: 'center'},
            // {field: 'okDay', title: '负责人', sort: true, align: 'center'},
            {field: 'kykc', title: '可用库存', sort: true, align: 'center',
                templet: function (data) {

                    return data.afnFulfillableQuantity+data.reservedFcTransfers+data.reservedFcProcessing;
                }},
            {field: 'afnFulfillableQuantity', title: '可售', sort: true, align: 'center'},
            {field: 'reservedFcTransfers', title: '待调仓', sort: true, align: 'center'},
            {field: 'reservedFcProcessing', title: '调仓中', sort: true, align: 'center'},
            // {fixed: 'right', width: 180, align: 'center', toolbar: '#barDemo'}
        ]],
        page: true,
        limits: [100, 1000],
        limit: 100,
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
            var tt =  $("#shippingPostalCode").val();
            $.ajax({
                url: '/wuliu/update',
                dataType: 'json',
                type: 'get',
                data: {ids: data.wuliuId},
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
            layer.confirm('真的删除ID为：'+data.historyId+"的数据吗?", function(index){
                //console.log(data.p_id);
                //向服务端发送删除指令
                $.ajax({
                    url:'/info/del',
                    type:'get',
                    data:{'id':data.historyId},//向服务端发送删除的id
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
        url: '/seller/getAllWname',
        dataType: 'json',
        type: 'get',
        success: function (data) {
            $('#wname').append(new Option('全部', ''));
            let f =false;
            console.log(wname)
            $.each(data.data, function (index, item) {
                if (item===wname){
                    f=true
                }
                $('#wname').append(new Option(item, item, false, f));// 下拉菜单里添加元素
                f = false;
            });
            layui.form.render("select");
        }
    })


    $.ajax({
        url: '/fbaStore/getAll',
        dataType: 'json',
        type: 'get',
        success: function (data) {
            $('#productName').append(new Option('全部', ''));
            let f =false;
            $.each(data.data, function (index, item) {
                if (item===productName){
                    f=true
                }
                $('#productName').append(new Option(item, item, false, f));// 下拉菜单里添加元素
                f = false;
            });
            layui.form.render("select");
        }
    })

    $.ajax({
        url: '/account/getAll',
        dataType: 'json',
        type: 'get',
        success: function (data) {
            $('#account').append(new Option('全部', ''));
            let f =false;
            $.each(data.data, function (index, item) {
                if (item===account){
                    f=true
                }
                $('#account').append(new Option(item, item, false, f));// 下拉菜单里添加元素
                f = false;
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
        area: ['300px', '300px'],
        skin: 'layui-layer-nobg',
        shadeClose: true,
        content: "<img src='" + url + "'>"
    });
}
