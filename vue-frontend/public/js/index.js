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
            common.ajax("POST","/ajax/recordPlayerDetailAjax", param,"html",'application/x-www-form-urlencoded; charset=UTF-8',function(data){
                $('#playerDetail').html(data);
            });
        });

        $('#dataList').on('click', '.carDetail', function () {
            var param = {
                'carModel': $(this).data('carmodel')
            };
            common.ajax("POST", "/ajax/recordCarDetailAjax", param, "html", 'application/x-www-form-urlencoded; charset=UTF-8', function (data) {
                $('#carDetail').html(data);
            });
        });

        $('#staticBackdrop').on('hidden.bs.modal',function(e){
            $('#playerDetail').html('');
            $('.driverName').text('');
            $('#modalProfile').html('');
            $('.ttscore').text('');
        });

        $('#carModal').on('hidden.bs.modal', function (e) {
            $('.carName').text('');
            $('#carDetail').html('');
        });
    },
    fnSubmit:function(){
        var param = $('#frm').serialize();
        common.ajax("POST","/ajax/recordListAjax", param, "html", 'application/x-www-form-urlencoded; charset=UTF-8', function (data) {
            $('#dataList').html(data);
        });
    },
    driverDetail : function(){
        let driverName  = '';
        let playerId = $('.popPlayerId').val();
        let ttscore = $('.ttScore').val();
        $('.popDriverName').each(function(){
            driverName = $(this).val();
        });
        $('.driverName').text(driverName);
        $('.ttscore').text("TT Score : "+ttscore);

        $('#modalProfile').attr('href',"https://steamcommunity.com/profiles/"+ playerId.substring(1));
        $('#modalProfile').attr('target', "_blank");
        $('#modalProfile').html('<img src="/image/steam.png" class="img-thumbnail ml-2" width="30px" />');
    }
    ,carDetail : function(){
        let carName = $(".popCarName").val();
        $('.carName').text(carName);
    }
};