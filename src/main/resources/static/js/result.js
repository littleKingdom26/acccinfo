var result = {
    init:function(){
        let _th = this;
        $('#league').change(function(){
            let formax = $('#league option:selected').data('round');
            let html = "<option value='0'>All</option>";
            for (let i=1;i<=formax;i++ ){
                html+= "<option value="+i+">"+i +" Round</option>";
            }
            $('#round').html(html);
            _th.fnResult();
        });

        $('#round').change(function(){
            _th.fnResult();
        });

        $('#staticBackdrop').on('hidden.bs.modal', function (e) {
            $('#lapDetail').html('');
            $('.driverName').text('');
        });
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