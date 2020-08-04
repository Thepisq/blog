// //获取CSRF token,防止ajax请求被视为跨域请求
// let header = $("meta[name='_csrf_header']").attr("content");
// let token = $("meta[name='_csrf']").attr("content");

let subBtn = $("#submitBtn");
let loginInfo = $("#login_info");

let username = $("#username")
let password = $("#password")

function login() {
    if (username.val().trim().length < 1 || password.val().trim().length < 1) {
        return false;
    }
    subBtn.prop("disabled", true)
    subBtn.toggleClass("progress-bar progress-bar-striped progress-bar-animated");
    $.ajax({
        type: 'POST',
        url: '/login',
        dataType: 'json',
        data: {
            "username": username.val(),
            "password": password.val(),
            "remeber-me": $("#remember-me").val(),
            "validateCode": $("#validateCode").val()
        },
        beforeSend: function (xhr) {
            // xhr.setRequestHeader(header, token)
        },
        success: function (data) {
            if (data.status != null) {
                var status = data.status;
                if (status == "fail") {
                    if (data.msg == "Bad credentials") data.msg = "用户名或密码错误"
                    subBtn.prop("disabled", false)
                    subBtn.toggleClass("progress-bar progress-bar-striped progress-bar-animated")
                    loginInfo.html("<p style=\"text-align: center\" class=\"text-danger\">" + data.msg + "</p>")
                    resetCode()
                } else if (status == "success") {
                    subBtn.toggleClass("progress-bar progress-bar-striped progress-bar-animated bg-success")
                    loginInfo.html("<p style=\"text-align: center\" class=\"text-primary\">登录成功</p>")
                    window.setTimeout(function () {
                        window.location = "/";
                    }, 1000);
                }
            } else {
                subBtn.prop("disabled", false)
                subBtn.toggleClass("progress-bar progress-bar-striped bg=danger")
                subBtn.val("/ / /Warning/ / /")
                alert("发生奇怪的事情了呢\n请刷新或是查看控制台日志")
                console.log(data)
            }
        },
        error: function () {
            subBtn.prop("disabled", false)
            subBtn.toggleClass("progress-bar progress-bar-striped progress-bar-animated")
        }
    });
}

function resetCode() {
    $("#codeImg").attr("src", "/vCode/image?t=" + new Date().getTime());
}

$(document).ready(function () {
    subBtn.click(function () {
        login()
    });
    $("#codeImg").click(function () {
        resetCode()
    })
});