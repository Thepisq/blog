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
//获取CSRF token,防止ajax请求被视为跨域请求
const header = $("meta[name='_csrf_header']").attr("content")
const token = $("meta[name='_csrf']").attr("content")
$(document).ready(function () {
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
                xhr.setRequestHeader(header, token)
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
})