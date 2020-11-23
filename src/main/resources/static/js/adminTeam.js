var teamInfo = {
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
var team = {
    init:function(){

    }

};