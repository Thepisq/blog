var isLogin = $(".user-login").val() == "true";
isCurrentUserFollowing(isLogin)
$(document).ready(function () {

    $(".blog-likes").click(function () {
        if (!isLogin) {
            window.location.href = "/login"
            return false;
        }

        var blogId = $(".blog-id").text()
        $.ajax({
            type: 'POST',
            url: '/blog/' + blogId + '/likes',
            beforeSend: function (xhr) {
            },
            success: function (data) {
                if (data.status == "success") {
                    $(".blog-likes svg").toggleClass("text-danger")
                } else if (data.status == "fail") {
                    $(".blog-likes svg").toggleClass("text-danger")
                    if (data.statusCode == "403") {
                        alert("权限不足,点击跳转充值权限")
                        window.location.href = "/user/upgrade";
                    }
                }
            },
            error: function (data) {
                alert("unknow error")
                console.log(data)
            }
        })
    })
    $(".following-btn").click(function () {
        if (!isLogin) {
            window.location.href = "/login"
            return false;
        }

        var authorId = $(".blog-author-id").text()
        $.ajax({
            type: 'POST',
            url: '/user/following/' + authorId,
            beforeSend: function (xhr) {
            },
            success: function (data) {
                if (data.status == "success") {
                    if (data.statusCode == 1) {
                        $(".following-btn").toggleClass("btn-danger btn-primary").text("已关注")
                    } else if (data.statusCode == -1) {
                        $(".following-btn").toggleClass("btn-outline-danger btn-primary").text("关注")
                    }
                } else if (data.status == "fail") {
                    if (data.statusCode == 0) {
                        alert(data.msg)
                    } else {
                        alert("code: " + data.statusCode + "\n" + data.msg)
                    }
                }
            },
            error: function () {
                alert("unknow error")
                console.log(data)
            }
        })
    })

})

function isCurrentUserFollowing(isLogin) {
    if (!isLogin) {
        return false;
    }

    var authorId = $(".blog-author-id").text()
    $.ajax({
        type: 'GET',
        url: '/user/following/is=' + authorId,
        beforeSend: function (xhr) {
        },
        success: function (data) {
            if (data.status == "success") {
                $(".following-btn").toggleClass("btn-outline-danger btn-danger").text("取消关注")
            }
        },
        error: function () {
            alert("unknow error in isCurrentUserFollowing")
            console.log(data)
        }
    })
}