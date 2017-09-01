$(function () {
    initComplexArea('seachprov', 'seachcity', 'seachdistrict', area_array, sub_array, '51', '0', '0');
});

function checkForm() {
    if (checkName() && checkPhone()){
        return true;
    }else {
        return false;
    }
}

/*
验证收货人
 */
function checkName() {
    var name = document.getElementById("user_recive_name").value.trim();
    var namespan = document.getElementById("namespan");
    if (name.length <= 0){
        namespan.innerHTML = "收货人不能为空".fontcolor("red").fontsize(2);
        return false;
    }else {
        return true;
    }
}

/*
验证手机号
 */
function checkPhone() {
    var phone = document.getElementById("user_recive_phone").value.trim();
    var phonespan = document.getElementById("phonespan");
    var regex = /^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\d{8}$/;
    if (phone.length > 0){
        if (regex.test(phone)){
            phonespan.innerHTML = "手机号正确".fontcolor("green").fontsize(2);
            return true;
        }else {
            phonespan.innerHTML = "手机号格式错误".fontsize(2).fontcolor("red");
        }
    }else {
        phonespan.innerHTML = "手机号不能为空".fontsize(2).fontcolor("red");
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
    document.getElementById("user_recive_address").value = areaName;
}