/**
 * Created by Administrator on 18-12-18.
 */
var E = window.wangEditor
var editor = new E('#commentsEditor')
// 自定义菜单配置
editor.customConfig.menus = [
    'link',  // 插入链接
    'quote',  // 引用
    'image',  // 插入图片
    'table',  // 表格
    'code',  // 插入代码
    'undo',  // 撤销
    //'redo'  // 重复
]
editor.customConfig.pasteIgnoreImg = true
editor.customConfig.pasteTextHandle = function (content) {
    // content 即粘贴过来的内容（html 或 纯文本），可进行自定义处理然后返回
    return content + '<br>'
}
//上传图片保存到服务器
editor.customConfig.uploadImgMaxSize = 4 * 1024 * 1024
editor.customConfig.uploadImgServer = '/upload'
editor.customConfig.uploadFileName = 'picture'
editor.customConfig.uploadImgHooks = {
    before: function (xhr, editor, files) {
        // 图片上传之前触发
        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件

        // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
        // return {
        //     prevent: true,
        //     msg: '放弃上传'
        // }
    },
    success: function (xhr, editor, result) {
        // 图片上传并返回结果，图片插入成功之后触发
        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
    },
    fail: function (xhr, editor, result) {
        // 图片上传并返回结果，但图片插入错误时触发
        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
    },
    error: function (xhr, editor) {
        // 图片上传出错时触发
        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
    },
    timeout: function (xhr, editor) {
        // 图片上传超时时触发
        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
    },

    // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
    // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
    customInsert: function (insertImg, result, editor) {
        // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
        // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

        // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
        var url = result.data;
        insertImg(url);

        // result 必须是一个 JSON 格式字符串！！！否则报错
    }
}
//打开Debug模式
editor.customConfig.debug = location.href.indexOf('wangeditor_debug_mode=1') > 0
editor.create()

$(document).ready(function () {
    getComments();
    $("#replyBtn").click(function(){
        var context = editor.txt.html();
        var aid = $("#aid").text();
        var replydata = "artid="+aid+ "&comment="+encodeURIComponent(context);
        //console.log(context);
        //console.log(encodeURIComponent(context));
        $.ajax({
            type:'POST',
            datatype:'json',
            url:"/c/add",
            data: replydata,
            beforeSend: function () {
                showprog();
            },
            success:function(data){
                endprog();
                alert("发布成功");
                location.reload();
            }
        });
        return false;
    });
});

function getComments(){
    $.ajax({
        type:'GET',
        datatype:'json',
        url:"/c/get="+$("#aid").text(),
        success:function(data){
            if(data.status == 0){
                var coms = "";
                $.each(data.data, function (index,item) {
                    coms+=dealComs(item);
                });
                $('#showcomments').html(coms);
            }
        }
    });
}

function dealComs(item){
    return  "<div class='commentsInfo'>" +
            "<div class='clearfloat'>" +
                "<div class='userinfo'>" +
                    "<div class='portrait'>" +
                    "<img src='" + item.portrait + "' class='img-circle'>" +
                    "</div>" +
                    "<div class='nickname'>" + item.nickname + "</div>" +
                    "<div class='level'>LV.<b>" + item.level + "</b></div>" +
                "</div>" +
            "</div>" +
            "<div class='comments'>" + item.comment + "</div>" +
            "</div>"
}