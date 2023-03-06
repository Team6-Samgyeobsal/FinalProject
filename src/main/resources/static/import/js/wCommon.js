// (DB에 Utf8로 저장할 수 없는) 이모지 포함 여부 체크
var isContainsEmoji = function (text) {
    return new RegExp(/[\uD800-\uDBFF\uDC00-\uDFFF]/).test(text);
}

// 이모지 포함 여부를 nodelist로 체크
function isContainsEmojiFromNodeList(param) {
    var elements = (typeof(param) == "string") ? document.querySelectorAll(param) : param;

    var emojiNodeCount = 0;
    elements.forEach(function (el) {
        if (isContainsEmoji(el.value)) emojiNodeCount++;
    });

    return (emojiNodeCount > 0);
}

function ajaxRequest(params, url, callBack, async) {
    async = async || true;
    $.ajax({
        type: "POST",
        url: url,
        data: params,
        async : async
    }).done(function(result){
        try {
            var jsonObj = $.parseJSON(result);
        } catch (error) {
            var jsonObj = result;
        }
        if (jsonObj.success == 'true')
            callBack(jsonObj);
        else
            failureFunc(jsonObj);

    });
}

function failureFunc(response) {	// 에러
    alert("데이타 처리를 실패하였습니다.");
}

/**
 *  숫자만 입력가능
 */
function numOnly() {
    $('.numOnly').css('imeMode','disabled').keypress(function(event) { //ie처리
        if(event.which && (event.which < 48 || event.which > 57) && event.which != 8 ) {
            if (event.preventDefault()) {
                event.preventDefault();
            } else {
                event.returnValue = false;
            }
        }
    }).keyup(function(){ //크롬, 파폭 처리
        var  $this = $(this)
        if( $this.val() != null && $this.val() != '' ) {
            $this.val( $this.val().replace(/[^0-9]/g, '') );
        }
    });
}

//휴대폰번호 체크
function checkMobileNum(num){
    var localNum = num.substring(0, 2);

    if(!(localNum==='01')){

        alert("정확한 휴대폰번호를 입력해주세요.");
        return false;
    }

    if(num.length<10){
        alert("정확한 휴대폰번호를 입력해주세요.");
        return false;
    }

    return true;
}

//return Internet Explorer version if not IE return 0
function ieVersion() {
    var ua = window.navigator.userAgent;
    var version = 0;
    //less then IE11
    var re = /MSIE ([0-9]{1,}[.0-9]{0,})/g;
    var isIE11 = /Trident\/7\./;
    if(!!re.exec(ua)) {
        version = parseFloat(RegExp.$1);
    }
    //for IE11
    if(isIE11.test(ua)) {
        version = 11;
    }

    return version;
}

// form 객체 만들어서 post submit 하는 function
// 페이지 move됨
function submitPost(postUrl, parm, target) {
    var f = document.createElement('form');

    var objs, value;
    for (var key in parm) {
        value = parm[key];
        objs = document.createElement('input');
        objs.setAttribute('type', 'hidden');
        objs.setAttribute('name', key);
        objs.setAttribute('value', value);
        f.appendChild(objs);
    }

    if (target)
        f.setAttribute('target', target);

    f.setAttribute('method', 'post');
    f.setAttribute('action', postUrl);
    document.body.appendChild(f);

    f.submit();
}

window.wadiz = window.wadiz || {};
window.wadiz.AccountMap = function() {
    this.map = new Object();
};
window.wadiz.AccountMap.prototype = {
    put : function(key, value) {
        this.map[key] = value;
    },
    get : function(key) {
        return this.map[key];
    },
    containsKey : function(key) {
        return key in this.map;
    },
    containsValue : function(value) {
        for (var prop in this.map) {
            if (this.map[prop] == value) return true;
        }
        return false;
    },
    isEmpty : function(key) {
        return (this.size() == 0);
    },
    clear : function() {
        for (var prop in this.map) {
            delete this.map[prop];
        }
    },
    remove : function(key) {
        delete this.map[key];
    },
    keys : function() {
        var keys = new Array();
        for (var prop in this.map) {
            keys.push(prop);
        }
        return keys;
    },
    values : function() {
        var values = new Array();
        for (var prop in this.map) {
            values.push(this.map[prop]);
        }
        return values;
    },
    size : function() {
        var count = 0;
        for (var prop in this.map) {
            count++;
        }
        return count;
    }
};