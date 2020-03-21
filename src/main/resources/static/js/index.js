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

            common.ajax("POST","/recordPlayerDetailAjax", param,"html",'application/x-www-form-urlencoded; charset=UTF-8',function(data){
                $('#playerDetail').html(data);
            });

        });

        $('#staticBackdrop').on('hidden.bs.modal',function(e){
            $('#playerDetail').html('');
            $('.driverName').text('');
        });
    },
    fnSubmit:function(){
        var param = $('#frm').serialize();
        common.ajax("POST","/recordListAjax", param, "html", 'application/x-www-form-urlencoded; charset=UTF-8', function (data) {
            $('#dataList').html(data);
        });
    },
    driverDetail : function(){
        let driverName  = '';
        let playerId = $('.popPlayerId').val();
        $('.popDriverName').each(function(){
            driverName = $(this).val();
        });
        $('.driverName').text(driverName);
        $('#modalProfile').attr('href',"https://steamcommunity.com/profiles/"+ playerId.substring(1));
        $('#modalProfile').attr('target', "_blank");
    }
};