var prefix = CTX_URL +"/sys/area"
$(document).ready(function () {
    load();
});
var load = function () {
    $('#exampleTable').bootstrapTreeTable(
        {
            id: 'areaId',
            code: 'areaId',
            parentCode: 'parentId',
            type: "POST",
            url: prefix + '/list', // 请求数据的ajax的url
            ajaxParams: {sort:'sort'},
            expandColumn: '1',// 在哪一列上面显示展开按钮
            striped: true, // 是否各行渐变色
            bordered: true, // 是否显示边框
            expandAll: false, // 是否全部展开
            columns: [
                {
                    title: '编号',
                    field: 'areaId',
                    visible: false,
                    align: 'center',
                    valign: 'center',
                    width: '5%'
                },
                {
                    title: '地区名称',
                    field: 'areaName',
                    valign: 'center',
                    width: '20%'
                },

                {
                    title: '描述',
                    field: 'remark',
                    align: 'center',
                    valign: 'center',
                    width : '20%'
                },
                {
                    title: '排序',
                    field: 'sort',
                    align: 'center',
                    valign: 'center',
                    width : '5%'
                },
                {
                    title: '创建人',
                    field: 'createBy',
                    valign: 'center',
                    width : '10%',
                },
                {
                    title: '创建时间',
                    field: 'createTime',
                    valign: 'center',
                    width : '15%',
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
                            + item.areaId
                            + '\')"><i class="fa fa-edit"></i></a> ';
                        var p = '<a class="btn btn-primary btn-sm '
                            + s_add_h
                            + '" href="#" mce_href="#" title="添加下级" onclick="add(\''
                            + item.areaId
                            + '\')"><i class="fa fa-plus"></i></a> ';
                        var d = '<a class="btn btn-warning btn-sm '
                            + s_remove_h
                            + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                            + item.areaId
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
        title: '增加地区',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '420px'],
        content: prefix + '/add/' + pId
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的地区(包含子地区)？', {
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
        title: '地区修改',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '420px'],
        content: prefix + '/edit/' + id
    });
}

function refresh() {
    reLoad();
}