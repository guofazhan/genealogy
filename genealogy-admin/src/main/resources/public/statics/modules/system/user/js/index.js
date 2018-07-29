var prefix = CTX_URL +"/sys/user";
$(function() {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'post',
                url : prefix + "/page",
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                // 设置为true会有隔行变色效果
                striped : true,
                // 服务器返回的数据类型
                dataType : "json",
                // 设置为true会在底部显示分页条
                pagination : true,
                // queryParamsType : "limit",
                // 设置为true将禁止多选
                singleSelect : false,
                // 如果设置了分页，每页数据条数
                pageSize : 10,
                // 如果设置了分布，首页页码
                pageNumber : 1,
                // 是否显示内容下拉框（选择显示的列）
                showColumns : false,
                // 设置在哪里进行分页，可选值为"client" 或者
                sidePagination : "server",
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,
                        limit : params.limit,
                        offset : params.offset,
                        showName : $('#searchName').val()
                    };
                },
                columns : [
                    {
                        checkbox : false
                    },
                    {
                        field : 'userId',
                        title : '序号'
                    },
                    {
                        field : 'showName',
                        title : '姓名'
                    },
                    {
                        field : 'loginName',
                        title : '用户名'
                    },
                    {
                        field : 'email',
                        title : '邮箱'
                    },
                    {
                        field : 'isLock',
                        title : '状态',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '1') {
                                return '<span class="label label-danger">禁用</span>';
                            } else if (value == '0') {
                                return '<span class="label label-primary">正常</span>';
                            }
                        }
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.userId
                                + '\')"><i class="fa fa-edit "></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.userId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm ' + s_resetPwd_h + '" href="#" title="重置密码"  mce_href="#" onclick="resetPwd(\''
                                + row.userId
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d + f;
                        }
                    } ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    layer.open({
        type : 2,
        title : '增加用户',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add'
    });
}
function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : "/sys/user/remove",
            type : "post",
            data : {
                'id' : id
            },
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}
function edit(id) {
    layer.open({
        type : 2,
        title : '用户修改',
        maxmin : true,
        shadeClose : false,
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + id // iframe的url
    });
}
function resetPwd(id) {
    layer.open({
        type : 2,
        title : '重置密码',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '400px', '260px' ],
        content : prefix + '/resetPwd/' + id // iframe的url
    });
}

function refresh() {
    reLoad();
}