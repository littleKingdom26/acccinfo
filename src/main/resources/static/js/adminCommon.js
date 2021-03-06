var common = {
    ajax:function(method,url,param,dataType, contentType,fnSuccess,fnError){
        if (contentType == '') {
            contentType = 'application/x-www-form-urlencoded; charset=UTF-8';
        }
        $.ajax({
            beforeSend: function () {
                $('#background').show(200);
            },
            complete: function () {
                $('#background').hide(0);
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
            , error: function(jqXHR, textStatus, errorThrown){
                if(typeof fnError === 'function') {
                    fnError(jqXHR, textStatus, errorThrown);
                }
            }

        });
    }
};



$(function(){
    $('.menuBtn').click(function (e) {
        $('#gnbMenu li').each(function(idx){
           $(this).removeClass('on');
           $(this).find('.subGnb').hide();
        });

        $(this).closest('li').addClass('on');
        if ($(this).closest('li').find('.subGnb').length > 0) {
            $(this).closest('li').find('.subGnb').show();
        }
    });

    $('.menuBtn').each(function(){
        let url = location.href;

        if (url.indexOf($(this).data('menu'))>0) {
            $(this).closest('li').addClass('on');
            if ($(this).closest('li').find('.subGnb').length > 0) {
                $(this).closest('li').find('.subGnb').show();
            }
        }
    });
});