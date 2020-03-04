var common = {
    ajax:function(method,url,param,dataType, contentType,fnSuccess){
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
                fnSuccess(data);
            }
        });


    }
};