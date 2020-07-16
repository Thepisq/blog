/**
 * Created by Administrator on 18-12-10.
 */
var page = $("#page").text();
$("#logBtn").click(function(){

    //检测'用户名'不为空且合法
    if( $('#log_user').val().length>=5 && RegexUser($('#log_user').val()) ){

        //检测'密码'不为空且合法
        if( $('#log_pass').val().length>=6 ){
            $.ajax({
                type:'POST',
                url:'/login',
                dataType:'json',
                data:$("#logform").serialize(),
                beforeSend: function () {
                    showprog();
                },
                success:function(data){
                    endprog();
                    cleanInputLog();
                    var msg = data.msg;
                    if(msg == 'T'){
                        $("#logAlert1").removeClass("hide").addClass("in");
                        window.setTimeout(function(){
                            window.location = page;
                        },1500);
                    }else{
                        $("#logAlert2").removeClass("hide").addClass("in");
                        //一定时间后消失
                        window.setTimeout(function(){
                            $("#logAlert2").removeClass("in").addClass("hide");
                        },5000);
                    }

                }
            });
        }else{
            //'密码'一栏警示
            checkPassForLog();
        }
    }else{
        //'用户名'一栏警示
        checkUserForLog();
    }

    //return false;
});
$("#regBtn").click(function(){

    //检测'用户名'不为空且合法
    if( $('#reg_user').val().length>=5 && RegexUser($('#reg_user').val()) ){

        //检测'密码'不为空且合法
        if( $('#reg_pass').val().length>=6 ){

            //检测'确认密码'不为空且合法
            if ($('#reg_pass2').val().length>=6 ){

                //检测'密码'等于'确认密码'
                if($('#reg_pass').val()==$('#reg_pass2').val()){

                    $.ajax({
                        type: 'POST',
                        url: '/register',
                        dataType: 'json',
                        data: $("#regform").serialize(),
                        beforeSend: function () {
                            showprog();
                        },
                        success: function (data) {
                            endprog();
                            var msg = data.msg;
                            console.log("msg: "+msg);
                            if (msg == 'T') {
                                cleanInputReg();
                                $("#regAlert1").removeClass("hide").addClass("in");
                                window.setTimeout(function () {
                                    window.location = "/";
                                }, 1500);
                            }

                        }
                    });
                }else{
                    //清空输入框
                    $("#reg_pass").val("");
                    $("#reg_pass2").val("");
                    checkPassForReg();
                    //显示警示框
                    $("#regAlert3").removeClass("hide").addClass("in");
                    //一定时间后消失
                    window.setTimeout(function () {
                        $("#regAlert3").removeClass("in").addClass("hide");
                    }, 5000);
                }
            } else {
                    //'确认密码'一栏警示
                    checkPass2ForReg();
            }
        }else{
            //'密码'一栏警示
            checkPassForReg();
        }
    }else{
        //'用户名'一栏警示
        checkUserForReg();
    }


    //return false;
});
function checkUserForReg(){
    var username = $("#reg_user").val();
    if( !RegexUser(username) || username.length < 5 || username.length > 16 ){
        $("#d_reguser").removeClass("has-success").addClass("has-error");
        $("#regAlert2").removeClass("in").addClass("hide");
        //$("#enableForReg").removeClass("in").addClass("hide");
        $("#inputState_ru").removeClass("in").addClass("hide");
    } else if (username.length >= 5 &&username.length <= 16){
        $.ajax({
            type: 'POST',
            url: '/checkUserExist',
            dataType: 'json',
            data: $("#regform").serialize(),
            success: function (data) {
                var isexist = data.msg;
                if (isexist == 'T') {
                    //用户名存在，不能注册
                    $("#regAlert2").removeClass("hide").addClass("in");
                    //$("#enableForReg").removeClass("in").addClass("hide");
                    $("#inputState_ru").removeClass("in").addClass("hide");
                    //console.log(username+" 不能注册");
                }else if(isexist == 'F'){
                    //用户名不存在，可以注册
                    $("#regAlert2").removeClass("in").addClass("hide");
                    $("#d_reguser").removeClass("has-error").addClass("has-success");
                    //$("#enableForReg").removeClass("hide").addClass("in");
                    $("#inputState_ru").removeClass("hide").addClass("in");
                    //console.log(username+" 可以注册");
                }

            }
        });
    }
}

function checkPassForReg(){
    var pass = $("#reg_pass").val();
    if(pass.length < 6){
        //密码不OK
        $("#d_regpass").removeClass("has-success").addClass("has-error");
        $("#inputState_rp").removeClass("in").addClass("hide");
        //isok = false;
    }else{
        //密码OK
        $("#d_regpass").removeClass("has-error").addClass("has-success");
        $("#inputState_rp").removeClass("hide").addClass("in");
    }
    if($("#reg_pass2").val().length > 0 ){
        checkPass2ForReg();
    }
}

function checkPass2ForReg(){
    if($("#reg_pass").val().length > 0 ){
        var pass = $("#reg_pass").val();
        var pass2 = $("#reg_pass2").val();
        if(pass == pass2){
            //确认密码OK
            $("#d_regpass2").removeClass("has-error").addClass("has-success");
            $("#inputState_rp2").removeClass("hide").addClass("in");
        }else{
            //确认密码不OK
            $("#d_regpass2").removeClass("has-success").addClass("has-error");
            $("#inputState_rp2").removeClass("in").addClass("hide");
        }
    }
}

function checkUserForLog(){
    var username = $("#log_user").val();
    if( username.length >= 5 && RegexUser(username)){
        //用户名合法且不为空
        $("#d_loguser").removeClass("has-error");
    }else{
        //用户名非法或为空
        $("#d_loguser").addClass("has-error");
    }
}

function checkPassForLog(){
    var pass = $("#log_pass").val();
    if(pass.length >= 6){
        //密码不为空
        $("#d_logpass").removeClass("has-error");
    }else{
        //密码为空
        $("#d_logpass").addClass("has-error");
    }
}

function RegexUser(username){
    return /^[0-9a-zA-Z]+$/.test(username);
}
function cleanInputReg(){
    $("#reg_user").val("");
    $("#reg_pass").val("");
    $("#reg_pass2").val("");
}
function cleanInputLog(){
    $("#log_user").val("");
    $("#log_pass").val("");
}

