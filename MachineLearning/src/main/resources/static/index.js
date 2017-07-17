/**
 * Created by fourtwo42 on 2017/7/14.
 */
'use strict';
(function(){
    var input = new Vue({
        el:"#input-area",
        data: {
            wordNum: 0,
        },
        methods: {
            clearNotice: function () {
                document.getElementById('clear-notice').style.visibility = "visible";
            },
            removeClearNotice: function () {
                document.getElementById('clear-notice').style.visibility = "hidden";
            },
            clearInput: function () {
                document.getElementById('input').value = "";
                this.wordNum = 0;
            },
            countWordNum: function () {
                this.wordNum = document.getElementById('input').value.length;
            }
        },
    })
})();