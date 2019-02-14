/**
 * @author liang xiong <ytspin@163.com>
 * date:2017/3/29
 * desc:共有接口函数,部分方法需先载入jquery.js
 */
/**
 *封装获取get
 */
var $_GET = (function () {
    var url = window.document.location.href.toString();
    var u = url.split("?");
    if (typeof(u[1]) == "string") {
        u = u[1].split("&");
        var get = {};
        for (var i in u) {
            var j = u[i].split("=");
            get[j[0]] = u[i].substring(j[0].length + 1);

            //get[j[0]] = j[1];
        }
        console.log(get);
        return get;
    } else {
        return {};
    }
})();
var _baseUrl = "http://192.168.0.22:8080/BusinessServer/wechat";//链接
/**
 * ajax获取数据
 * @param url
 * @param data
 * @param success
 * @param error
 */
function ajaxRemote(url, data, async, success, error) {

    console.log("请求获取ajax数据:" + url);
    console.log(data);
    $.ajax({
        data: data,
        type: "POST",
        timeout: 2000,
        dataType: "json",
        url: url,
        async: async,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
            error();
        },
        success: function (data) {
            console.log(data);
            success(data);
        }
    });
}
/**
 * 验证openid
 * @param openid
 */
function verifyCode(openid, success, error) {
    ajaxRemote(_baseUrl, {action: 'getUserByWechat', openId: openid}, true, function (data) {
        if (data.result != "SUCCESS")
            error();
        else
            success();
    }, function () {
        error();
    });
}
function renderBtn() {
    /**
     *页面跳转
     * 当属性target_selfelf时,为空或时,当前页跳转;其他情况,新界面载入
     */
    $('[data-url]').click(function () {
        var fn = $(this).data('url');
        var target = $(this).attr('target');
        if (target == undefined || target == "_self")
            window.location.href = fn;
        else
            window.open(fn);
    })
}
/**
 * 获取时间（间隔月）
 * @param a 相差月份值
 * @returns {string|*}
 */
function getDateFromMonth(a) {
    var resultDate, year, month, date;
    var currDate = new Date();
    year = currDate.getFullYear();
    month = currDate.getMonth() + 1;
    if (month + a <= 0) {
        month += a;
        month += 12;
        month = month % 12 == 0 ? 12 : month;
        year--;

    } else if (month + a > 12) {
        year++;
        month = month + a - 12;
    } else {
        month += a;
    }
    month = (month < 10) ? ('0' + month) : month;
    resultDate = year + '-' + month + '-01';
    return resultDate;
}
function isPassive() {
    var supportsPassiveOption = false;
    try {
        addEventListener("test", null, Object.defineProperty({}, 'passive', {
            get: function () {
                supportsPassiveOption = true;
            }
        }));
    } catch (e) {
    }
    return supportsPassiveOption;
}
var scroll;
var loadingStat = 0;//0:初始状态 1:准备加载中 /
function refreshScroll() {
    scroll.refresh();
}
function createScroll(a, b, c) {
    scroll = new IScroll(a, {mouseWheel: true, click: true, fadeScrollbars: true, probeType: 1});
    scroll.on('scroll', function () {
        if (this.y <= this.maxScrollY) {   //判断上拉
            if (loadingStat == 0) {
                b();
                loadingStat = 1;
            }
        }
    });
    scroll.on('scrollEnd', function () {
        if (this.y <= this.maxScrollY) {   //判断上拉
            if (loadingStat == 1) {
                c();
                loadingStat = 0;
            }
        }
    });
}
