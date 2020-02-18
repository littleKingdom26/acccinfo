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

        $('#dataList').on('click', '.driverDetail', function () {
            var param = {
                'playerId': $(this).data('playerid')
            };
            $.ajax({
                beforeSend: function () {
                    $('#background').show(200);
                },
                complete: function () {
                    $('#background').hide(200);
                }
                , dataType: "html"
                , method: "POST"
                , data: param
                , url: "/recordPlayerDetailAjax"
                , success: function (data) {
                    $('#playerDetail').html(data);
                }
            });
        });

        $('#staticBackdrop').on('hidden.bs.modal',function(e){
            $('#playerDetail').html('');
            $('.driverName').text('');
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

    },
    driverDetail : function(){
        var driverName  = '';
        $('.popDriverName').each(function(){
            driverName = $(this).val();
        });
        $('.driverName').text(driverName);
    }
};