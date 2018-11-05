//处理地区函数
function handleArea() {
    var province = $("#province").find("option:selected").data("code");
    var city = $("#city").find("option:selected").data("code");
    var district = $("#district").find("option:selected").data("code");
    $("#p").val(province);
    $("#c").val(city);
    $("#d").val(district);
}

// submit
function handleAreaOnSubmit() {
    handleArea();
    return true;
}