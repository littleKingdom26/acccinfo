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
        var url = location.href;
        if (url.indexOf("/board") > 0) {
            if ($(this).data('menu') == 'board') {
                $(this).closest('li').addClass('on');
                if ($(this).closest('li').find('.subGnb').length > 0) {
                    $(this).closest('li').find('.subGnb').show();
                }
            }
        }else if (url.indexOf("/player") > 0) {
            if ($(this).data('menu') == 'player') {
                $(this).closest('li').addClass('on');
                if ($(this).closest('li').find('.subGnb').length > 0) {
                    $(this).closest('li').find('.subGnb').show();
                }
            }
        } else if (url.indexOf("/banner") > 0) {
            if ($(this).data('menu') == 'banner') {
                $(this).closest('li').addClass('on');
                if ($(this).closest('li').find('.subGnb').length > 0) {
                    $(this).closest('li').find('.subGnb').show();
                }
            }
        }

    });
});