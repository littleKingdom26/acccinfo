var result = {
    init:function(){
        let _th = this;
        $('#league').change(function(){
           _th.fnRoundSet();
        });

        $('#round').change(function(){
            _th.fnResult();
        });

        $('#staticBackdrop').on('hidden.bs.modal', function (e) {
            $('#lapDetail').html('');
            $('.driverName').text('');
        });

        /*$('#league option:eq(1)').prop("selected", true);*/
        _th.fnRoundSet();
    },
    fnRoundSet:function(){
        let _th = this;
        let html = '';
        if($('#league option:selected').val() == '0') {
            let date = new Date();
            for (let i=2020;i<=date.getFullYear();i++){
                html += "<option value='"+i+"'>"+i+"</option>";
            }
            $('#roundText').text("Year");
        }else{
            let formax = $('#league option:selected').data('round');
            html = "<option value='0'>All</option>";
            for (let i = 1; i <= formax; i++) {
                html += "<option value=" + i + ">" + i + " Round</option>";
            }
            $('#roundText').text("Round");
        }
        $('#round').html(html);
        _th.fnResult();
    },
    fnResult:function(){
        let data = {
            eventInfoSeq: $('#league').val(),
            round: $('#round').val()
        };
        common.ajax("GET", "/result/search", data, "html", 'application/x-www-form-urlencoded; charset=UTF-8', function (data) {
            $('#resultData').html(data);
        });
    },
    roundSearch:function(){
        $(".lapDetail").click(function(){
            let data = {
                carId: $(this).data('carid'),
                eventInfoSeq: $('#league').val(),
                round: $('#round').val(),
                playerName: $(this).data('playername')
            };
            common.ajax("GET", "/result/lapDetail", data, 'html', 'application/x-www-form-urlencoded; charset=UTF-8', function (data) {
                $('#lapDetail').html(data);
            });
        });
    },
    lapDetail :function(){

    }
};