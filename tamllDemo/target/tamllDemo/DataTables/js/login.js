var flag = false;
function checkUserName() {
    var username = document.getElementById("username").value.trim();
    var userspan = document.getElementById("usernameid");
    if (username.length > 0){
        $.ajax({
            type:'POST',
            url:'/checkusername',
            data:"username="+username,
            success: function (data){
                if(data == "false"){
                    userspan.innerHTML = "";
                    flag = true;
                }else {
                    userspan.innerHTML = "用户名不存在".fontsize(2).fontcolor("red");
                }
            }
        });
        return flag;
    }else{
        userspan.innerHTML = "用户名不能为空".fontsize(2).fontcolor("red");
        return false;
    }
}