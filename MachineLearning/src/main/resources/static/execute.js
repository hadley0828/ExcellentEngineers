/**
 * Created by Administrator on 2017/7/17.
 */
function translateexecute() {
    var text = $("#input").val()
    var option = $("#s option:selected")
    if(option.text() == "英文转中文") {
        $.post('/etoc/translate',{input: text}, function (data) {
            document.getElementById('output').value = data
        })
    }else{
        $.post('/ctoe/translate',{input: text}, function (data) {
            document.getElementById('output').value = data
        })
    }
}
function comment(){
    var text = $("#eval-input").val()
    $.post('/comment/score',{evalinput: text}, function (data) {
        if(data == 1) {
            document.getElementById('eval-output').innerHTML = '<img src="../static/img/1.png">'
        }else if(data == 2){
            document.getElementById('eval-output').innerHTML = '<img src="../static/img/2.png">'
        }else if(data == 3){
            document.getElementById('eval-output').innerHTML = '<img src="../static/img/3.png">'
        }else if(data == 4){
            document.getElementById('eval-output').innerHTML = '<img src="../static/img/4.png">'
        }else{
            document.getElementById('eval-output').innerHTML = '<img src="../static/img/5.png">'
        }
    })
}