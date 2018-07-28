var prefix = CTX_URL +"/sys/role";

var menuIds;
$(function() {
    getMenuTreeData();
    validateRule();
});
$.validator.setDefaults({
    submitHandler : function() {
        getAllSelectNodes();
        update();
    }
});
function loadMenuTree(menuTree) {
    $('#menuTree').jstree({
        "plugins" : [ "wholerow", "checkbox" ],
        'core' : {
            'data' : menuTree
        },
        "checkbox" : {
        }
    });
    $('#menuTree').jstree('open_all');
}
function getAllSelectNodes() {
    // 获得整个树
    var ref = $('#menuTree').jstree(true);
    // 获得所有选中节点的，返回值为数组
    menuIds = ref.get_selected();
    $("#menuTree").find(".jstree-undetermined").each(function(i, element) {
        menuIds.push($(element).closest('.jstree-node').attr("id"));
    });
    console.log(menuIds);
}

function getMenuTreeData() {
    var roleId = $('#roleId').val();
    $.ajax({
        type : "POST",
        url : CTX_URL +"/sys/menu/tree/" + roleId,
        success : function(data) {
            if (data.retCode === '000000') {
                loadMenuTree(data.rspBody);
            }
            else {
                layer.msg(data.retDesc);
            }
        }
    });
}
function update() {
    $('#menuIds').val(menuIds);
    $.ajax({
        cache : true,
        type : "POST",
        url : prefix +"/edit",
        data :  $('#editForm').serialize(),
        async : false,
        error : function(request) {
            layer.alert("Connection error");
        },
        success : function(data) {
            if (data.retCode === '000000') {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
            else {
                parent.layer.msg(data.retDesc);
            }
        }
    });
}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#editForm").validate({
        rules : {
            roleName : {
                required : true
            }
        },
        messages : {
            roleName : {
                required : icon + "请输入角色名"
            }
        }
    });
}