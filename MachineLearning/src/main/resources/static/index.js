/**
 * Created by fourtwo42 on 2017/7/14.
 */
'use strict';
(function(){
    var func = new Vue({
        el:"#function",
        methods:{
            transHover: function(){
                document.getElementById('trans').src = "img/translate_black.png";
            },
            transLeave: function(){
                document.getElementById('trans').src = "img/translate_white.png";
            },
            evalHover: function(){
                document.getElementById('eval').src = "img/star_black.png";
            },
            evalLeave: function(){
                document.getElementById('eval').src = "img/star_white.png";
            }
        }
    });

    var transPage = new Vue({
        el: "#trans-page",
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
                document.getElementById('trans-clear-notice').style.visibility = "visible";
            },
            removeClearNotice: function () {
                document.getElementById('trans-clear-notice').style.visibility = "hidden";
            },
            clearInput: function () {
                document.getElementById('trans-input').value = "";
                this.wordNum = 0;
            },
            countWordNum: function () {
                this.wordNum = document.getElementById('trans-input').value.length;
                if(this.wordNum == 0){
                    this.selectedLanguage = 'auto';
                }
                else {
                        var content = document.getElementById('trans-input').value.substr(0, 1);
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
    });

    var evalPage = new Vue({
        el: "#eval-page",
        data: {
            wordNum: 0,
        },
        methods: {
            clearNotice: function () {
                document.getElementById('eval-clear-notice').style.visibility = "visible";
            },
            removeClearNotice: function () {
                document.getElementById('eval-clear-notice').style.visibility = "hidden";
            },
            clearInput: function () {
                document.getElementById('eval-input').value = "";
                this.wordNum = 0;
            },
            countWordNum: function () {
                this.wordNum = document.getElementById('eval-input').value.length;
            }
        },
    });
})();