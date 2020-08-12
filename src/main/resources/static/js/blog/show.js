$(document).ready(function () {
    var isLogin = $(".user-login").val() == "true";
    $(".blog-likes").click(function () {
        if (isLogin) {
            var id = $(".blog-id").text()
            $.ajax({
                type: 'POST',
                url: '/blog/' + id + '/likes',
                beforeSend: function (xhr) {
                },
                success: function (data) {
                    if (data.status == "success") {
                        $(".blog-likes svg").toggleClass("text-danger")
                    } else if (data.status == "fail") {
                        $(".blog-likes svg").toggleClass("text-danger")
                    }
                },
                error: function () {
                    alert("unknow error")
                    console.log(data)
                }
            })
        } else {
            window.location.href = "/login";
        }
    })
})