/**
 * Created by fourtwo42 on 2017/7/14.
 **/
'use strict';
(function(){
    var input = new Vue({
        el: "#content",
        data: {
            wordNum: 0,
            selectedLanguage: 'auto',
            languages: [
                {text: '自动检测', value: 'auto'},
                {text: '英文转中文', value: '1'},
                {text: '中文转英文', value: '2'},
            ]
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
                if(this.wordNum == 0){
                    this.selectedLanguage = 'auto';
                }
                else {
                        var content = document.getElementById('input').value.substr(0, 1);
                        var re = new RegExp("[\\u4E00-\\u9FFF]+", "g");
                        if (re.test(content)) {
                            this.selectedLanguage = '2';
                        }
                        else {
                            this.selectedLanguage = '1';
                        }
                }
            }
        },
    })
})();