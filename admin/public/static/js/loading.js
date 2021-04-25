Loading = {
    show: function () {
        console.log("出现被调用");
        $.blockUI({
            message: '<img src="/static/image/loading.gif"/>',
            css: {
                padding: "10px",
                left: "50%",
                width: "80px",
                marginLeft: "-40px",
            }
        })
    },
    hide: function () {
        console.log("隐藏被调用");
        setTimeout(function () {
            $.unblockUI();
        },100);
    }
}