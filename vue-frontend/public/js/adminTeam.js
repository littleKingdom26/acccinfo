let teamInfo = {
    init:function(){
        let _this = this;
        $("#btnSave").click(function(e){
            e.preventDefault();
            if($("#teamName").val() == '') {
                alert("팀명을 입력하세요");
            }else{
                _this.submit();
            }
        });

        $('#teamName').keypress(function (e) {
            if(e.keyCode == 13) {
                _this.submit();
            }
        });

        $('.btnDel').click(function(e){
            e.preventDefault();
            let teamInfoSeq = $(this).data("teaminfoseq");
            common.ajax("DELETE", "/admin/team/teamInfo/delete/"+ teamInfoSeq, '', 'json', '', function (data) {
                if(data.success) {
                    location.reload();
                }
            });

        })
    },
    submit:function(){
        common.ajax("POST","/admin/team/teamInfo/save", {teamName: $("#teamName").val()},'json','',function(data){
            if(data.success) {
                location.reload();
            }else{
                alert(data.msg);
            }
        });
    }

};
let team = {
    init:function(){
        let _this = this;
        $('#btnSearch').click(function (e){
            e.preventDefault();
            _this.teamSearch();
        });

        $('#teamInfoSeq').change(function(e){
            _this.teamSearch();
        });

        $('#teamSearch').on('click', '.btnDel', function () {
            common.ajax("DELETE", "/admin/team/team/delete/" + $(this).data('teamseq'), '', 'json', '', function (data) {
                if(data.success) {
                    _this.teamSearch();
                }
            });
        });

        $('#playerSearch').on('click', '.btnSave', function () {

            if($('#teamInfoSeq').val() == '') {
                alert("팀 선택을 먼저 하시기 바랍니다.");
                return false;
            }
            let data = {"teamInfoSeq": $('#teamInfoSeq').val(),"playerId":$(this).data("playerid")};
            common.ajax('POST', '/admin/team/team/save', data, 'json', '', function (data) {
                if(data.success) {
                    _this.teamSearch();
                }else{
                    alert(data.msg);
                }
            });
        });

        $('#btnPlayerSearch').click(function (e) {
            e.preventDefault();
            _this.playerSearch();
        });

        $('#keyword').keypress(function (e) {
            if(e.keyCode == 13) {
                _this.playerSearch();
            }
        });

    },
    teamSearch :function(){
        common.ajax("GET","/admin/team/team/search",{teamInfoSeq:$('#teamInfoSeq').val()},'html','',function(data){
            $('#teamSearch').html(data);
        });
    },
    playerSearch :function(){
        let keyword = $('#keyword').val();
        common.ajax("GET", "/admin/team/player/search", {keyword: keyword}, 'html', '', function (data) {
            $('#playerSearch').html(data);
        });

    }

};
let teamScore = {
    init:function(){
        let _this = this;
        _this.submit();
        $('#searchEventDt').change(function (e){
            _this.submit();
        });

        $('#teamScoreTable').on('click', '.btnTeamDetail', function (e) {
            e.preventDefault();
            let teamInfoSeq = $(this).data('teaminfoseq');
            let searchEventDt = $('#searchEventDt').val();
            console.log('teamInfoSeq', teamInfoSeq);
            common.ajax("GEt","/admin/team/teamScore/detail",{teamInfoSeq : teamInfoSeq,searchEventDt:searchEventDt},'html','',function(data){
                $('#divLayer').html(data);
                $('#divLayer').bPopup();
            });
        });

    },
    submit : function(){
        common.ajax("GET", "/admin/team/teamScore/search", {searchEventDt: $('#searchEventDt').val()}, 'html', '', function (data) {
            $('#teamScoreTable').html(data);
        });
    }
};