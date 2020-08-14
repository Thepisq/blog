var toolbarOptions = [
    ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
    ['blockquote', 'code-block'],

    [{'header': 1}, {'header': 2}],               // custom button values
    [{'list': 'ordered'}, {'list': 'bullet'}],
    [{'script': 'sub'}, {'script': 'super'}],      // superscript/subscript
    [{'indent': '-1'}, {'indent': '+1'}],          // outdent/indent
    [{'direction': 'rtl'}],                         // text direction

    [{'size': ['small', false, 'large', 'huge']}],  // custom dropdown
    [{'header': [1, 2, 3, 4, 5, 6, false]}],

    [{'color': []}, {'background': []}],          // dropdown with defaults from theme
    [{'font': []}],
    [{'align': []}],

    ['clean']                                         // remove formatting button
];

var quillEditor = new Quill('#editor', {
    modules: {
        toolbar: toolbarOptions,
    },
    placeholder: "愉快地写下内容吧",
    theme: 'snow'
})
// //获取CSRF token,防止ajax请求被视为跨域请求
// const header = $("meta[name='_csrf_header']").attr("content")
// const token = $("meta[name='_csrf']").attr("content")
$(document).ready(function () {
    $(".backspace-div").hide()
    $("#blog_title").bind('input propertychange', function () {
        var title = $("#blog_title").val()
        if (title.length >= 30) {
            $("#blog_title").val(title.substring(0, 30))
        }
    })
    $("#submit_blog_btn").click(function () {
        if (quillEditor.getLength() === 1) {
            return false
        }
        var content = quillEditor.root.innerHTML
        var blogData = $("#editor_form").serialize()
        blogData = blogData + "&" + "content=" + encodeURIComponent(content)
        console.log("发送之前:\n" + blogData)
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '/blog/add',
            data: blogData,
            beforeSend: function (xhr) {
                // xhr.setRequestHeader(header, token)
            },
            success: function (data) {
                $("#submit_blog_btn").prop("disabled", true)
                if (data.status == "success") {
                    $("#submit_result").val("发布成功")
                    $("#submit_blog_btn").toggleClass("progress-bar progress-bar-striped progress-bar-animated bg-success")
                    window.setTimeout(function () {
                        window.location = "/";
                    }, 1000);
                } else if (data.status == "fail") {
                    $("#submit_result").val("发布成功")

                }
            },
            error: function () {
                alert("发生奇怪的事情了呢\n请重试或是查看控制台日志")
                console.log(data)
                $("#submit_blog_btn").prop("disabled", false)
            }
        })
        return false
    })
    $("#blog_tag_modal_show").one('click', function () {
        $.ajax({
            type: 'GET',
            dataType: 'json',
            url: '/topic/getHotTopic',
            data: {
                "page": 1
            },
            beforeSend: function (xhr) {
                // xhr.setRequestHeader(header, token)
            },
            success: function (data) {
                if (data != null) {
                    addTagRow(eval(data))
                }
            },
            error: function () {
                alert("发生奇怪的事情了呢\n请重试或是查看控制台日志")
                console.log(data)
            }
        })
    })
    //一开始不存在的组件的绑定事件
    $(document).on('click', ".tp-btn", function () {
        addTagSpan($(this))
    })
    $(document).on("click", ".backspace-div", function () {
        if (!$(this).hasClass('hidden')) {
            $("#blog_tag").val(null)
            $(".tag-span").remove()
            $(this).remove()
        }
    })
})

//添加表单tag内容
function addTagSpan(thisDom) {
    var tpDiv = thisDom.prev("span")
    var tag_span = "<span class=\"tag-span\">\n" + "</span>"
    var backspace = $(".backspace-div").clone().show()

    $("#blog_tag_modal_show").parent().prepend(tag_span)
    $("#blog_tag_modal_show").prev().after(backspace)

    $(".tag-span").html(tpDiv.html().split("-")[1])
    $("#blog_tag").val(tpDiv.attr("tp"))
    $("#modal_Add_Tag").modal('hide')
}

//添加模态框tag列表
function addTagRow(data) {
    var htmlStr = ""
    $.each(data, function (index, topic) {
        htmlStr += "<div class=\"row\">\n" +
            "<span tp=\"" + topic.id + "\" class=\"col-10 align-self-center\">" + topic.branch + "-" + topic.topicName + "</span>\n" +
            "<button class=\"btn btn-sm my-2 btn-outline-warning col-2 tp-btn\">添加</button> \n" +
            "</div>"
    })
    $("#blog_tag_modal_content").html(htmlStr)
}


