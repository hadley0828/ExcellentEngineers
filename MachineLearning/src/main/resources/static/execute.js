/**
 * Created by Administrator on 2017/7/17.
 */
function translate() {
    var text = document.getElementById("input").textContent
    if($("#option1").textContent == 英文转中文) {
        $.ajax({
            type: 'POST',
            url: 'etoc/translate',
            dateType: 'json',
            data: text,
            success: function (data) {
                $("#output").text = JSON.parse(data)[0]
            },
            error: function (data) {
                alert("error")
            }
        })
    }else{
        $.ajax({
            type: 'POST',
            url: 'ctoe/translate',
            dateType: 'json',
            data: text,
            success: function (data) {
                $("#output").text = JSON.parse(data)[0]
            },
            error: function (data) {
                alert("error")
            }
        })
    }
}