const _VALID = 1  /*有效 */
const _INVALID = 0 /*无效 */
const _CANCEL = -1 /*取消 */
const _MIN_USERNAME_LENGTH = 6 /*最低用户名长度 */
const _MIN_PASSWORD_LENGTH = 8 /*最低密码长度 */

// //获取CSRF token,防止ajax请求被视为跨域请求
// var header = $("meta[name='_csrf_header']").attr("content")
// var token = $("meta[name='_csrf']").attr("content")

var input_username = $("#username")
var valid_username = $("#valid_username")

var input_password = $("#password")
var valid_password = $("#valid_password")

var input_recheck = $("#recheck")
var valid_recheck = $("#valid_recheck")

var subBtn = $("#submitBtn")
var patrn = /[@#\$%\^&\*]+/g

function checkForExists() {
    input_username.val(input_username.val().trim())
    if (input_username.val().trim().length < 1) {
        validCheck(input_username, valid_username, _CANCEL, "")
    } else if (patrn.test(input_username.val())) {
        validCheck(input_username, valid_username, _INVALID, "用户名不能有符号")
    } else if (input_username.val().trim().length >= _MIN_USERNAME_LENGTH) {
        $.ajax({
            type: 'POST',
            url: '/checkForUserExists',
            dataType: 'json',
            data: 'username=' + input_username.val().trim(),
            beforeSend: function (xhr) {
                // xhr.setRequestHeader(header, token)
            },
            success: function (data) {
                //数据库查不到数据返回 0 ，否则返回1
                if (data === 0) {
                    validCheck(input_username, valid_username, _VALID, "用户名可以使用")
                } else if (data === 1) {
                    validCheck(input_username, valid_username, _INVALID, "用户名已存在")
                }

            }
        })
    } else {
        validCheck(input_username, valid_username, _CANCEL, "")
    }
}

function validCheck(input, feedback, isValid, feedbackText) {
    if (isValid === _VALID) {
        input.removeClass("is-invalid").addClass("is-valid");
        feedback.removeClass("invalid-feedback").addClass("valid-feedback").text(feedbackText)
    } else if (isValid === _INVALID) {
        input.removeClass("is-valid").addClass("is-invalid");
        feedback.removeClass("valid-feedback").addClass("invalid-feedback").text(feedbackText)
    } else if (isValid === _CANCEL) {
        input.removeClass("is-valid is-invalid");
        feedback.removeClass("valid-feedback invalid-feedback").text(feedbackText)
    }
    checkForSubmit()
}

function disable_submitBtn(disable) {
    subBtn.prop("disabled", disable)
}

function checkForPass() {
    input_password.val(input_password.val().trim())
    if (input_password.val().trim().length >= _MIN_PASSWORD_LENGTH) {
        validCheck(input_password, valid_password, _VALID, "")
    } else if (input_password.val().trim().length > 0) {
        validCheck(input_password, valid_password, _INVALID, "密码长度最少为" + _MIN_PASSWORD_LENGTH + "个字符")
    } else {
        validCheck(input_password, valid_password, _CANCEL, "")
    }
    if (input_recheck.val().trim() > 0) {
        checkForRecheck()
    }
}

function checkForRecheck() {
    input_recheck.val(input_recheck.val().trim())
    if (input_recheck.val().trim() != input_password.val().trim()) {
        validCheck(input_recheck, valid_recheck, _INVALID, "请输入一致的密码")
    } else {
        validCheck(input_recheck, valid_recheck, _VALID, "")
    }
}

function checkForSubmit() {
    if (input_username.hasClass("is-valid") && input_password.hasClass("is-valid") && input_recheck.hasClass("is-valid")) {
        disable_submitBtn(false)
    } else {
        disable_submitBtn(true)
    }
}

function register() {
    disable_submitBtn(true)
    $.ajax({
        type: 'POST',
        url: '/register',
        dataType: 'json',
        data: {
            "username": input_username.val().trim(),
            "password": input_password.val().trim(),
            "validateCode": $("#validateCode").val().trim()
        },
        beforeSend: function (xhr) {
            //xhr.setRequestHeader(header, token);
            subBtn.toggleClass("progress-bar progress-bar-striped progress-bar-animated")
        },
        success: function (data) {
            if (data == 1) {
                subBtn.toggleClass("progress-bar progress-bar-striped progress-bar-animated bg-success");
                $("#register_result").val("注册成功");
                window.setTimeout(function () {
                    window.location = "/login";
                }, 1500);
            } else if (data == 0) {
                disable_submitBtn(false)
                subBtn.toggleClass("progress-bar progress-bar-striped progress-bar-animated bg-warning");
                $("#register_result").val("发生错误，请稍后重试");
            } else {
                alert("警告，发生错误")
                window.location = "/register"
            }
        },
        fail: function () {
            disable_submitBtn(false)
            subBtn.toggleClass("progress-bar progress-bar-striped progress-bar-animated bg-warning");
            $("#register_result").val("发生错误，请稍后重试");
        }
    });
}

$(document).ready(function () {
    disable_submitBtn(true);
    input_username.focusout(function () {
        checkForExists()
    });
    input_password.bind('input propertychange', function () {
        checkForPass()
    });
    input_recheck.bind('input propertychange', function () {
        checkForRecheck()
    });
    subBtn.click(function () {
        register()
    })
    $("#codeImg").click(function () {
        $("#codeImg").attr("src", "/vCode/image?t=" + new Date().getTime());
    })
});