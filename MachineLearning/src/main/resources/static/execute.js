/**
 * Created by Administrator on 2017/7/17.
 */
function translateexecute() {
    var text = document.getElementById("input").textContent
    if(document.getElementById("s").value == "2") {
        // $.ajax({
        //     type: 'POST',
        //     url: '/etoc/translate',
        //     dateType: 'json',
        //     data: text,
        //     success: function (data) {
        //         $("#output").text = JSON.parse(data)[0]
        //     },
        //     error: function (data) {
        //         alert("error")
        //     }
        // })
        $.post('/etoc/translate', function (data) {
            console.log(data)
        })
    }else{
        $.post('/ctoe/translate', function (data) {
            console.log(data)
        })
    }
}