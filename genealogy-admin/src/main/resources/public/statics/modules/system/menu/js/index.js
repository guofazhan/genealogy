var prefix = CTX_URL +"/sys/menu"
$(document).ready(function () {
    load();
});
var load = function () {
    $('#exampleTable').bootstrapTreeTable(
            {
                id: 'menuId',
                code: 'menuId',
                parentCode: 'parentId',
                type: "POST", // 请求数据的ajax类型
                url: prefix + '/list', // 请求数据的ajax的url
                ajaxParams: {sort:'order_num'}, // 请求数据的ajax的data属性
                expandColumn: '1',// 在哪一列上面显示展开按钮
                striped: true, // 是否各行渐变色
                bordered: true, // 是否显示边框
                expandAll: false, // 是否全部展开
                columns: [
                    {
                        title: '编号',
                        field: 'menuId',
                        visible: false,
                        align: 'center',
                        valign: 'center',
                        width: '5%'
                    },
                    {
                        title: '名称',
                        field: 'menuName',
                        valign: 'center',
                        width: '20%'
                    },

                    {
                        title: '图标',
                        field: 'menuIcon',
                        align: 'center',
                        valign: 'center',
                        width : '5%',
                        formatter: function (item, index) {
                            return item.menuIcon == null ? ''
                                : '<i class="' + item.menuIcon
                                + ' fa-lg"></i>';
                        }
                    },
                    {
                        title: '类型',
                        field: 'menuType',
                        align: 'center',
                        valign: 'center',
                        width : '10%',
                        formatter: function (item, index) {
                            if (item.menuType === 0) {
                                return '<span class="label label-primary">目录</span>';
                            }
                            if (item.menuType === 1) {
                                return '<span class="label label-success">菜单</span>';
                            }
                            if (item.menuType === 2) {
                                return '<span class="label label-warning">按钮</span>';
                            }
                        }
                    },
                    {
                        title: '地址',
                        field: 'menuUrl',
                        valign: 'center',
                        width : '20%',
                    },
                    {
                        title: '权限标识',
                        field: 'code',
                        valign: 'center',
                        width : '20%',
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        valign: 'center',
                        formatter: function (item, index) {
                            var e = '<a class="btn btn-primary btn-sm '
                                + s_edit_h
                                + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + item.menuId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var p = '<a class="btn btn-primary btn-sm '
                                + s_add_h
                                + '" href="#" mce_href="#" title="添加下级" onclick="add(\''
                                + item.menuId
                                + '\')"><i class="fa fa-plus"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm '
                                + s_remove_h
                                + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + item.menuId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return e + d + p;
                            return "";
                        }
                    }]
            });
}

function reLoad() {
    load();
}

/**
 * 添加事件
 * @param pId
 */
function add(pId) {
    layer.open({
        type: 2,
        title: '增加菜单',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add/' + pId
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的菜单(包含子菜单)？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (data) {
                if (data.retCode === '000000') {
                    layer.msg("删除成功");
                    reLoad();
                }
                else {
                    layer.msg(data.retDesc);
                }
            }
        });
    })
}

function edit(id) {
    layer.open({
        type: 2,
        title: '菜单修改',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id
    });
}