$(function () {
    initComplexArea('seachprov', 'seachcity', 'seachdistrict', area_array, sub_array, '51', '0', '0');
});

/*
注册验证
 */
function checkForm() {
    if (checkUserName() && checkPassWord() && checkEmail() && enSurePass() &&checkValistr()) {
        return true;
    } else {
        return false;
    }
}

var flag = false;
/*
验证用户名(为3~16个字符，且不能包含”@”和”#”字符)
 */
function checkUserName() {
    var username = document.getElementById("username").value.trim();
    var userspan = document.getElementById("usernameid");
    var nameRegex = /^[^@#]{3,16}$/;
    if (username.length > 0){
        if(nameRegex.test(username)){
            $.ajax({
                type:'POST',
                url:'/checkusername',
                data:"username="+username,
                success: function (data){
                    if(data == "false"){
                        userspan.innerHTML = "用户名已存在".fontsize(2).fontcolor("red");
                    }else {
                        userspan.innerHTML = "用户名可以使用".fontsize(2).fontcolor("green");
                        flag = true;
                    }
                }
            });
            return flag;
        }else if (username.length > 16 || username.length < 3){
            userspan.innerHTML = "用户名长度为3到16".fontsize(2).fontcolor("red");
            return false;
        }else {
            userspan.innerHTML = "用户名格式不正确".fontsize(2).fontcolor("red");
            return false;
        }
    }else {
        userspan.innerHTML = "用户名不能为空".fontsize(2).fontcolor("red");
        return false;
    }
}

/*
验证密码(密码长度在8个字符到16个字符，由字母、数字和"_"组成)
 */
function checkPassWord() {
    var password = document.getElementById("password").value.trim();
    var passspan = document.getElementById("passwordid");
    var passRegex = /^[0-9A-Za-z_]\w{7,15}$/;
    if(password.length > 0){
        if(passRegex.test(password)){
            passspan.innerHTML = "密码格式正确".fontsize(2).fontcolor("green");
            return true;
        }else if (password.length > 16 || password.length < 8){
            passspan.innerHTML = "密码长度为8到16".fontsize(2).fontcolor("red");
            return false;
        }else {
            passspan.innerHTML = "密码格式不正确".fontsize(2).fontcolor("red");
            return false;
        }
    }else {
        passspan.innerHTML = "密码不能为空".fontsize(2).fontcolor("red");
        return false;
    }
}

/*
验证两次输入的密码是否一致
 */
function enSurePass() {
    var password1 = document.getElementById("password").value.trim();
    var password2 = document.getElementById("password2").value.trim();
    var ensurespan = document.getElementById("ensureid");
    if (password2.length > 0){
        if (password1.valueOf() == password2.valueOf()){
            ensurespan.innerHTML = "确认密码正确".fontsize(2).fontcolor("green");
            return true;
        }else {
            ensurespan.innerHTML = "确认密码错误".fontsize(2).fontcolor("red");
            return false;
        }
    }else {
        ensurespan.innerHTML = "确认密码不能为空".fontsize(2).fontcolor("red");
        return false;
    }
}

/*
验证邮箱
 */
var flag2 = false;
function checkEmail() {
    var email = document.getElementById("email").value.trim();
    var emailspan = document.getElementById("emailid");
    var emailRegex = /^[a-z0-9]\w+@[a-z0-9]{2,3}(\.[a-z]{2,3}){1,2}$/i;  // .com .com.cn
    if(email.length > 0){
        if(emailRegex.test(email)){
            $.ajax({
                type:'POST',
                url:'/checkeamil',
                data:"email="+email,
                success: function (data){
                    if(data == "false"){
                        emailspan.innerHTML = "邮箱已注册".fontsize(2).fontcolor("red");
                    }else {
                        emailspan.innerHTML = "邮箱可以使用".fontsize(2).fontcolor("green");
                        flag2 = true;
                    }
                }
            });
            return flag2;
        }else {
            emailspan.innerHTML = "邮箱格式不正确".fontsize(2).fontcolor("red");
            return false;
        }
    }else {
        emailspan.innerHTML = "邮箱不能为空".fontsize(2).fontcolor("red");
        return false;
    }

}

/*
验证码验证
 */
function checkValistr() {
    var valistr = document.getElementById("valistr").value.trim();
    var valistrspan = document.getElementById("valistrid");
    if(valistr.length > 0){
        $.ajax({
            type:'GET',
            url:'/checkValiImage',
            data:"valistr="+valistr,
            success: function(data){
                if(data == "false"){
                    valistrspan.innerHTML = "验证码错误".fontsize(2).fontcolor("red");
                }else {
                    valistrspan.innerHTML = "验证码正确".fontsize(2).fontcolor("green");
                    flag = true;
                }
            }
        });
        return flag;
    }else {
        valistrspan.innerHTML = "验证码不能为空".fontsize(2).fontcolor("red");
        return false;
    }
}

/*
获取地区名
 */
function getAreaNamebyID(){
    var area = 0;
    if($("#seachdistrict").val() != "0"){
        area = $("#seachdistrict").val();
    }else if ($("#seachcity").val() != "0"){
        area = $("#seachcity").val();
    }else{
        area = $("#seachprov").val();
    }
    var areaName = "";
    if(area.length == 2){
        areaName = area_array[area];
    }else if(area.length == 4){
        var index1 = area.substring(0, 2);
        areaName = area_array[index1] + "-" + sub_array[index1][area];
    }else if(area.length == 6){
        var index1 = area.substring(0, 2);
        var index2 = area.substring(0, 4);
        areaName = area_array[index1] + "-" + sub_array[index1][index2] + "-" + sub_arr[index2][area];
    }
    document.getElementById("cvalue").value = areaName;
}