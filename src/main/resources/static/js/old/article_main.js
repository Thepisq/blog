/**
 * Created by Administrator on 18-12-17.
 */
$(document).ready(function () {
    $("#toLogin").click(function(){
        window.location = "/login&page='/a/"+$("#aid").text()+"'";
    });
});