$(document).ready(function () {
    $("#loginBtn").on('click', function () {
        $("#signupForm").submit();
    });
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        login();
    }
});

function login() {
    $.ajax({
        type: "POST",
        url: CTX_URL + "signin",
        data: $('#signupForm').serialize(),
        success: function (data) {
            if (data.retCode === '000000') {
                //0.1透明度的白色背景
                layer.load(1, {
                    shade: [0.1, '#fff']
                });
                location.href = CTX_URL + 'index';
            }
            else {
                layer.msg(data.retDesc);
            }
        },
    });
}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            loginName: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            loginName: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}

