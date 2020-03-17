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
        }
    });
});