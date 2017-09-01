$(function () {
    var errMsg = document.getElementById("err").value.trim();
    var errspan = document.getElementById("errspan");
    if (errMsg == "null"){
        document.getElementById("trerr").style.display = "none";
    }else {
        errspan.innerHTML = errMsg;
    }
})

function a() {
    var userId = document.getElementById("userId").value.trim();
    var relist = document.getElementById("relist");
    var falg = false;
    $.ajax({
        type:'POST',
        url:'/checkrecive',
        data:"userId="+userId,
        success:function (data) {
            if (data == "false"){
                relist.innerHTML = "收货地址最多5个".fontcolor("red").fontsize(2);
            }else {
                falg = true;
                window.location.href = "http://localhost:8080/addrecive";
            }
        }
    });
    return falg;
}