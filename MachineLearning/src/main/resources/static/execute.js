/**
 * Created by Administrator on 2017/7/17.
 */
function translateexecute() {
    var text = document.getElementById("input").textContent
    if(document.getElementById("s").value == "2") {
        $.post('/etoc/translate', function (data) {
            console.log(data)
        })
    }else{
        $.post('/ctoe/translate', function (data) {
            console.log(data)
        })
    }
}