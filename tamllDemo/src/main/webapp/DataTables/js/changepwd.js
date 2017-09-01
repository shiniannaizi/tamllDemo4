function checkFrom() {
    if (checkpwd() && ensurpwd()){
        return true;
    }else {
        return false;
    }
}

function checkpwd() {
    var newpwd = document.getElementById("newpassword1").value.trim();
    var passRegex = /^[0-9A-Za-z_]\w{7,15}$/;
    var newpwdspan = document.getElementById("npwspanid1");
    if (newpwd.length > 0){
        if (passRegex.test(newpwd)){
            newpwdspan.innerHTML = "密码格式正确".fontsize(2).fontcolor("green");
            return true;
        } else if(newpwd.length > 16 || newpwd.length < 8){
            newpwdspan.innerHTML = "密码长度为8-16".fontsize(2).fontcolor("red");
            return false;
        } else {
            newpwdspan.innerHTML = "密码格式不正确".fontsize(2).fontcolor("red");
            return false;
        }
    }else {
        newpwdspan.innerHTML = "密码不能为空".fontsize(2).fontcolor("red");
        return false;
    }
}

function ensurpwd() {
    var newpwd1 = document.getElementById("newpassword1").value.trim();
    var newpwd2 = document.getElementById("newpassword2").value.trim();
    var newpwdspan2 = document.getElementById("npwspanid2");
    if (newpwd2.length > 0){
        if (newpwd1.valueOf() == newpwd2.valueOf()){
            newpwdspan2.innerHTML = "确认密码正确".fontsize(2).fontcolor("green");
            return true;
        }else {
            newpwdspan2.innerHTML = "两次密码不一致".fontsize(2).fontcolor("red");
            return false;
        }
    }else {
        newpwdspan2.innerHTML = "确认密码不能为空".fontsize(2).fontcolor("red");
        return false;
    }
}