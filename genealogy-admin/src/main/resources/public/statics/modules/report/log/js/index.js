var prefix = CTX_URL +"/report/log";
$(function() {
    //加载表格数据
    load();
});

/**
 * 表格数据重新加载
 */
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

/**
 * 刷新表格数据
 */
function refresh() {
    reLoad();
}

function load(){
    $('#exampleTable').bootstrapTable({
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
        queryParams : getQueryParams,
        columns : [
            {
                checkbox : false
            },
            {
                field : 'recordId',
                title : '序号'
            },
            {
                field : 'operation',
                title : '操作类型'
            },
            {
                field : 'createBy',
                title : '操作人'
            },
            {
                field : 'createTime',
                title : '操作时间'
            }
        ]
    });
}

/**
 * 查询参数
 * @param params
 * @returns {{limit: *, offset: *, showName: (*|jQuery)}}
 */
function getQueryParams(params){
    return {
        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,
        limit : params.limit,
        offset : params.offset,
        operation : $('#operation').val(),
        createBy : $('#createBy').val()
    };
}