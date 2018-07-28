var prefix = CTX_URL +"/sys/menu"
$(function() {
    validateRule();
    //打开图标列表
    $("#ico-btn").click(function(){
        layer.open({
            type: 2,
            title:'图标列表',
            content: CTX_URL +'/html/icons.html',
            area: ['480px', '90%'],
            success: function(layero, index){

            }
        });
    });

});
$.validator.setDefaults({
    submitHandler : function() {
        update();
    }
});
function update() {
    $.ajax({
        cache : true,
        type : "POST",
        url : prefix + "/edit",
        data : $('#editForm').serialize(),
        async : false,
        error : function(request) {
            layer.alert("Connection error");
        },
        success : function(data) {
            if (data.retCode === '000000') {
                parent.layer.msg("保存成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            }
            else {
                layer.msg(data.retDesc);
            }

        }
    });

}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#editForm").validate({
        rules : {
            menuName : {
                required : true
            },
            menuType : {
                required : true
            }
        },
        messages : {
            menuName : {
                required : icon + "请输入菜单名"
            },
            menuType : {
                required : icon + "请选择菜单类型"
            }
        }
    })
}