//初始化地址，将数据库中已有的信息显示出来
$(function () {
    var address = document.getElementById("address").value.trim();
    var addr = new Array();
    addr = address.split("-");
    var prov;
    var city;
    var stri;
    var proid = 0;
    var citid = 0;
    if (addr.length == 3){
        prov = addr[0];
        city = addr[1];
        stri = addr[2];
        for (var i=0;i<area_array.length;i++){
            if (prov==area_array[i]){
                proid = i;
            }
        }
        for (var i=0;i<sub_array.length;i++){
            for (var j=proid*100;j<sub_array[proid].length;j++)
            {
                if (city==sub_array[proid][j]){
                    citid = j;
                }
            }
        }
        initComplexArea('seachprov', 'seachcity', 'seachdistrict', area_array, sub_array, proid, citid, '0');
        changeCity(citid,'seachdistrict',
            'seachdistrict');
        $("#seachdistrict option").each(function () {
            if ($(this).text() == stri){
                $(this).attr("selected","selected");
            }
        })
    }

    if (addr.length == 2){
        prov = addr[0];
        city = addr[1];
        for (var i=0;i<area_array.length;i++){
            if (prov==area_array[i]){
                proid = i;
            }
        }
        for (var i=0;i<sub_array.length;i++){
            if (city==sub_array[proid][i]){
                citid = i;
            }
        }
        initComplexArea('seachprov', 'seachcity', 'seachdistrict', area_array, sub_array, proid, citid, '0');
        changeComplexProvince(proid,sub_array,'seachcity','seachdistrict');
        $("#seachcity option").each(function () {
            if ($(this).text() == city){
                $(this).attr("selected","selected");
            }
        })
    }

    if (addr.length == 1){
        prov = addr[0];
        for (var i=0;i<area_array.length;i++){
            if (prov==area_array[i]){
                proid = i;
            }
        }
        citid = 0;
        initComplexArea('seachprov', 'seachcity', 'seachdistrict', area_array, sub_array, proid, citid, '0');
    }

    if (addr.length == 0){
        proid = 51;
        citid = 0;
        initComplexArea('seachprov', 'seachcity', 'seachdistrict', area_array, sub_array, proid, citid, '0');
    }

    checkEmail();

});

var flag2 = false;
function checkEmail() {
    var email = document.getElementById("email").value.trim();
    var userid = document.getElementById("userId").value.trim();
    var emailspan = document.getElementById("emailid");
    var emailRegex = /^[a-z0-9]\w+@[a-z0-9]{2,3}(\.[a-z]{2,3}){1,2}$/i;  // .com .com.cn
    if(email.length > 0){
        if(emailRegex.test(email)){
            $.ajax({
                type:'POST',
                url:'/checkeamil',
                data:"email="+email+"userId"+userid,
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

function getUserAreaNamebyID(){
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
    document.getElementById("address").value = areaName;
}