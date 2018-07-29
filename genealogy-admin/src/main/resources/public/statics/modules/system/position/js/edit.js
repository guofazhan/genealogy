var prefix = CTX_URL +"/sys/position";
$(function() {
    validateRule();
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
            positionName : {
                required : true
            }
        },
        messages : {
            positionName : {
                required : icon + "请输入职位名"
            }
        }
    });
}