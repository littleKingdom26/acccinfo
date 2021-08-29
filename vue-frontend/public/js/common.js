var common = {
    ajax:function(method,url,param,dataType, contentType,fnSuccess){
        if (contentType == '') {
            contentType = 'application/x-www-form-urlencoded; charset=UTF-8';
        }
        $.ajax({
            beforeSend: function () {
                $('#background').show(200);
            },
            complete: function () {
                $('#background').hide(200);
            }
            , dataType: dataType
            , method: method
            , data: param
            , contentType :contentType
            , url: url
            , success: function (data) {
                if (typeof fnSuccess === 'function') {
                    fnSuccess(data);
                }
            }
        });


    }
};


