var teamResult = {
    init:function(){
        let _th = this;

        $('#eventDt').change(function(){
            _th.resultSet();
        });


        /*$('#dataList').on('click', '.btnTeamDetail', function () {

        });*/

        _th.resultSet();

    },
    resultSet:function(){
        console.log('$().val()', $('#eventDt').val());

        let data = {'searchEventDt': $('#eventDt').val()};

        common.ajax("GET", "/teamResult/search", data, "html", 'application/x-www-form-urlencoded; charset=UTF-8', function (data) {
            $('#resultData').html(data);
        });
    },
    teamSearchDetail:function(){

        $(".btnTeamDetail").click(function () {
            let param = {
                'teamInfoSeq': $(this).data('teaminfoseq')
            };
            console.log('param', param);
            common.ajax("GET", "/teamResult/teamResultDetail", param, "html", 'application/x-www-form-urlencoded; charset=UTF-8', function (data) {
                $('#teamScoreDetail').html(data);
            });
        });

    }
};