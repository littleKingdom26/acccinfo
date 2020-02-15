var index = {
    init:function(){
        var _th = this;
        $('#trackSel').change(function(){
            let trackSeq = $(this).val();
            $('#trackSeq').val(trackSeq);
            $('#sessionId').val('0');
            $('#weekSel').val('0');
            _th.fnSubmit();
        });

        $('#weekSel').change(function () {
            let trackSeq = $("#weekSel option:selected").data("trackseq");
            $('#trackSel').val(trackSeq);
            $('#sessionId').val($(this).val());
            $('#trackSeq').val(trackSeq);
            _th.fnSubmit();
        });
    },
    fnSubmit:function(){
        var param = $('#frm').serialize();
        $.ajax({
            beforeSend:function(){
                $('#background').show(200);
            },
            complete:function(){
                $('#background').hide(200);
            }
            , dataType : "html"
            , method : "POST"
            , data : param
            , url : "/recordListAjax"
            , success :function (data) {
                $('#dataList').html(data);
            }
        });

    }
}