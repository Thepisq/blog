/**
 * Created by Administrator on 18-12-13.
 */

$(document).ready(function () {
    console.log("进入页面");
    toPage(1);
});
function toPage(pageNum){
    if(pageNum <= 0) pageNum = 1;
    $.ajax({
        type:'GET',
        url:'/a/page='+pageNum,
        data:'page',
        beforeSend: function () {

        },
        success:function(data){
            var arts = "";
            var awudata = data.data.list;
            console.log("第"+data.data.pageNum+"页,当前页有"+data.data.pageSize+"条数据");
            $.each(awudata,function(index,item){
                console.log("获取文章中,当前第:"+(index+1)+"条");
                arts+="<div class='artsBlock'>" +

                getArticleInfo(item) +

                "<div class='aBcontext'>" + strSimplify(item.context) + "</div>" +

                "</div>"
                ;
            });
            console.log("添加文章主体内容,总长度为 "+arts.length);
            $('#arts').html(arts);

        }
    });
}

function getArticleInfo(item){
     return "<div class='aBarticleInfo'>" +
            "<tr>" +
            "<table class='table'>" +
            "<tr>" +
            "<td class='aBcomments'>" + item.comments + "</td>" +
            "<td class='aBtitle'>" +
            "<a href='/a/" + item.id + "'>" + item.title + "</a>" +
            "</td>" +
                getAuthorInfo(item) +
            "</tr>" +
            "</table>" +
            "</div>"
}

function getAuthorInfo(item){
    return "<td class='aBauthorInfo'>" +
        "<div class='aBportrait'>" +
        "<img src='" + item.portrait + "' class='img-circle'>" +
        "</div>" +
        "<div class='aBnickname'>" + item.nickname + "</div>" +
        "<div class='aBlevel'>LV." + item.level + "</div>" +
        "</td>"
}

//将文章正文部分进行精简处理
function strSimplify(str){
    console.log("处理文章ing...");
    //正则表达式，用于匹配img标签
    var imgRegex = /<img[^>]+>/gi;
    //正则表达式，用于匹配除"<p></p>"和"<br>"以外的HTML标签
    var htmlRegex = /<(?!p|br|\/p|br\/)[^>]+>/gi;
    //匹配src
    //var srcRegex = /src="[^"]+"/gi;
    //临时变量，保存str中提取的img标签
    var imgTemp = "";
    //临时变量，保存img标签中提取的src内容
    //var srcTemp = "";
    //存放取出的图片
    var showimg = "";
    var i = 1;
    while( (imgTemp = imgRegex.exec(str)) != null ){
        //srcTemp = srcRegex.exec(imgTemp);
        //console.log("检测到图片,链接:"+srcTemp);
        if(i++<=3)showimg += "<div class='singleImg img-thumbnail'>"+imgTemp+"</div>";
    }
    console.log("展示的图片:"+showimg);
    str = str.replace(htmlRegex,"");
    var MaxContextShow = 50;
    if(str.length >= MaxContextShow){
        str = str.substring(0,MaxContextShow)+"...";
    }
    var ctxDivs = "<div class='aBContextText'>"+str+"</div>"
    var showimgDiv = "<div class='aBContextImg'>"+showimg+"</div>";
    ctxDivs += showimgDiv;
    console.log("处理完成");
    return  ctxDivs;
}
$('#indexLogin').click(function () {
    toLogin();
});

function checkLogin(){
    console.log("检测用户是否登录");
    $.ajax({
        type:'POST',
        datatype:'json',
        url:'/logincheck',
        asnyc:false,
        success:function(data){
            var result = data.msg;
            if( result == 'T'){
                islogin = true;
            }else{
                islogin = false;
            }
            console.log("登录状况:"+ islogin?"已登录":"未登录"+"["+islogin+"]");
        }
    });
}

function toLogin(){
    window.location = '/login';
}
function toRegister(){
    window.location = '/register';
}