function getTag() {
    $.ajax({
        url: "http://localhost:8080/phisoa/tag/getChildTags",
        dataType: "json",
        method: "post",
        data: {
            "tagId": $("#selectTag1").find("option:selected").val()
        },
        success: function (data) {
            $("#selectTag2").find("option").remove();
            $.each(data.msg, function (index, item) {
                $("#selectTag2").append("<option value='" + item.id + "'>" + item.name + "</option>");
            });
        },

        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("error");
        }
    });
}

function getTag2() {
    $.ajax({
        url: "http://localhost:8080/phisoa/tag/getChildTags",
        dataType: "json",
        method: "post",
        data: {
            "tagId": $("#selectTag2").find("option:selected").val()
        },
        success: function (data) {
            $("#selectTag3").find("option").remove();
            $.each(data.msg, function (index, item) {
                $("#selectTag3").append("<option value='" + item.id + "'>" + item.name + "</option>");
            });
        },

        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("error");
        }
    });
}