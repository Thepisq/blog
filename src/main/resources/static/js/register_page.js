const _VALID = 1  /*有效 */
const _INVALID = 2 /*无效 */
const _CANCEL = -1 /*取消 */
const _MIN_USERNAME_LENGTH = 3; /*最低用户名长度 */
const _MIN_PASSWORD_LENGTH = 8; /*最低密码长度 */

const input_username = $("#username");
const valid_username = $("#valid_username");

const input_password = $("#password");
const valid_password = $("#valid_password");

const patrn = /[@#\$%\^&\*]+/g;

function checkForExists() {
    input_username.val(input_username.val().trim())
    if (input_username.val().trim().length > _MIN_USERNAME_LENGTH) {
        var header = $("meta[name='_csrf_header']").attr("content")
        var token = $("meta[name='_csrf']").attr("content")
        $.ajax({
            type: 'POST',
            url: '/checkForUserExists',
            dataType: 'json',
            data: "username=" + input_username.val().trim(),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token)
            },
            success: function (data) {
                if (data === 0) {
                    validCheck(input_username, valid_username, _VALID, "用户名可以使用")
                } else if (data === 1) {
                    validCheck(input_username, valid_username, _INVALID, "用户名已存在")
                }

            }
        });
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
        input.removeClass("is-valid id-invalid")
        feedback.removeClass("valid-feedback invalid-feedback").text(feedbackText)
    }
    checkForSubmit()
}

function disable_submitBtn(disable) {
    let subBtn = $("#submitBtn")
    subBtn.prop("disabled", disable)
}

function checkForPass() {
    input_password.val(input_password.val().trim())
    if (input_password.val().trim().length > _MIN_PASSWORD_LENGTH) {
        validCheck(input_password, valid_password, _VALID, "密码长度有效")
    } else {
        validCheck(input_password, valid_password, _CANCEL, "")
    }
}

function checkForSubmit() {
    if (input_username.hasClass("is-valid") && input_password.hasClass("is-valid")) {
        disable_submitBtn(false)
    } else {
        disable_submitBtn(true)
    }
}

$(document).ready(function () {
    disable_submitBtn(true)
})