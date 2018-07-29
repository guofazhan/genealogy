var prefix = CTX_URL +"/sys/area";
$(function() {
    validateRule();
});
$.validator.setDefaults({
    submitHandler : function() {
        submit01();
    }
});
function submit01() {
    $.ajax({
        cache : true,
        type : "POST",
        url : prefix + "/save",
        data : $('#addForm').serialize(),
        async : false,
        error : function(request) {
            layer.alert("Connection error");
        },
        success : function(data) {
            if (data.retCode === '000000') {
                parent.layer.msg("保存成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name);
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
    $("#addForm").validate({
        rules : {
            areaName : {
                required : true
            },
            areaCode : {
                required : true
            }
        },
        messages : {
            areaName : {
                required : icon + "请输入地区名"
            },
            areaCode : {
                required : icon + "请输入地区编码"
            }
        }
    })
}