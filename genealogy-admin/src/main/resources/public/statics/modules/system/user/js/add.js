var prefix = CTX_URL +"/sys/user";
$().ready(function() {
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        save();
    }
});

function save() {
    $.ajax({
        cache : true,
        type : "POST",
        url : prefix + "/save",
        data : $('#addForm').serialize(),
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
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
    $("#addForm").validate({
        rules : {
            showName : {
                required : true
            },
            loginName : {
                required : true,
                minlength : 2,
                remote : {
                    url : prefix + "/exit",
                    type : "post",
                    dataType : "json",
                    data : {
                        loginName : function() {
                            return $("#loginName").val();
                        }
                    }
                }
            },
            password : {
                required : true,
                minlength : 8
            }
        },
        messages : {
            showName : {
                required : icon + "请输入姓名"
            },
            loginName : {
                required : icon + "请输入您的用户名",
                minlength : icon + "用户名必须两个字符以上",
                remote : icon + "用户名已经存在"
            },
            password : {
                required : icon + "请输入您的密码",
                minlength : icon + "密码必须8个字符以上"
            }
        }
    })
}
