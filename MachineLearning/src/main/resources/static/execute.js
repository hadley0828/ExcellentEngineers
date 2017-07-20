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