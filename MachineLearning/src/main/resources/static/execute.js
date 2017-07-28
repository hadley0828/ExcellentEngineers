/**
 * Created by Administrator on 2017/7/17.
 */
function translateexecute() {
    var text = $("#trans-input").val()
    var option = $("#s option:selected")
    if(option.text() == "英文转中文") {
        $.post('/etoc/translate',{input: text}, function (data) {
            document.getElementById('trans-output').value = data
        })
    }else{
        $.post('/ctoe/translate',{input: text}, function (data) {
            document.getElementById('trans-output').value = data
        })
    }
}
function comment(){
    var text = $("#eval-input").val()
    $.post('/comment/score',{evalinput: text}, function (data) {
        console.log(data)
        if(data == 1) {
            document.getElementById('eval-output').innerHTML = '<img src="/img/1.png" height="140" width="140" />'
        }else if(data == 2){
            document.getElementById('eval-output').innerHTML = '<img src="/img/2.png" height="140" width="140" />'
        }else if(data == 3){
            document.getElementById('eval-output').innerHTML = '<img src="/img/3.png" height="140" width="140" />'
        }else if(data == 4){
            document.getElementById('eval-output').innerHTML = '<img src="/img/4.png" height="140" width="140" />'
        }else{
            document.getElementById('eval-output').innerHTML = '<img src="/img/5.png" height="140" width="140" />'
        }
    })
}