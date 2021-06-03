var race = {
    scoreInit:function(){
        $('#btnAdd').click(function (e) {
            e.preventDefault();
            let rank = 0;
            $('#scoreAddTable input[name=ranks]').each(function (idx) {
                rank = $(this).val();
            });
            rank = Number(rank) + 1;
            let html = "<tr>" +
                "<th>" + rank + "등</th>" +
                "<td>" +
                "   <input type='text' name='scores' class='textType' />" +
                "   <input type='hidden' name='ranks' value='" + rank + "' />" +
                "</td>" +
                "</tr>";
            $('#scoreAddTable').append(html);
        });

        $('#btnSave').click(function (e) {
            e.preventDefault();
            if ($('#title').val() == "") {
                alert('제목을 입력하세요');
                return false;
            }

            $('#frm').submit();
        });

        $('#scoreSelect').change(function (e) {
            if ($(this).val() != '') {
                common.ajax("GET", "/admin/event/scoreInfo/" + $(this).val(), "", "html", 'application/x-www-form-urlencoded; charset=UTF-8', function (data) {
                    $('#scoreInfoDetail').html(data);
                });
            }
        });


        $('#btnDel').click(function (e) {
            e.preventDefault();
            if (confirm("점수를 삭제 합니다. 해당 점수를 사용하는 이벤트는 재 설정 필요합니다.")) {
                common.ajax("DELETE", "/admin/event/scoreInfo/del/"+ $('#scoreSelect').val(), "", "json", 'application/json; charset=utf-8', function (data) {
                    if (data.code == '0000') {
                        alert('점수를 삭제 하였습니다.');
                        location.reload();
                    } else {
                        alert('점수 삭제 실패 하였습니다. 다시 시도 해 주시기 바립니다.');
                    }
                });
            }
        });
    },
    handicapInit: function () {
        $('#btnAdd').click(function (e) {
            e.preventDefault();
            let rank = 0;
            $('#handicapAddTable input[name=ranks]').each(function (idx) {
                rank = $(this).val();
            });
            rank = Number(rank) + 1;
            let html = "<tr>" +
                "<th>" + rank + "등</th>" +
                "<td>" +
                "   <input type='text' name='handicaps' class='textType' />" +
                "   <input type='hidden' name='ranks' value='" + rank + "' />" +
                "</td>" +
                "</tr>";
            $('#handicapAddTable').append(html);
        });

        $('#btnSave').click(function (e) {
            e.preventDefault();
            if ($('#title').val() == "") {
                alert('제목을 입력하세요');
                return false;
            }
            $('#frm').submit();
        });

        $('#handicapSelect').change(function (e) {
            if($(this).val() != ''){
                common.ajax("GET", "/admin/event/handicapInfo/" + $(this).val(), "", "html", 'application/x-www-form-urlencoded; charset=UTF-8', function (data) {
                    $('#handicapInfoDetail').html(data);
                });
            }
        });


        $('#btnDel').click(function (e) {
            e.preventDefault();
            if (confirm("핸디캡을 삭제 합니다. 해당 핸디캡을 사용하는 이벤트는 재 설정 필요 합니다.")) {
                common.ajax("DELETE", "/admin/event/handicapInfo/del/" + $('#handicapSelect').val(), "", "json", 'application/json; charset=utf-8', function (data) {
                    if (data.code == '0000') {
                        alert('핸디캡을 삭제 하였습니다.');
                        location.reload();
                    } else {
                        alert('핸디캡 삭제 실패 하였습니다. 다시 시도 해 주시기 바립니다.');
                    }
                });
            }
        });
    },
    eventInfoInit:function () {
        $('#btnSave').click(function () {
            if($('#title').val() == '') {
                alert('대회명을 입력하세요');
                return false;
            }

            if($('#round').val() == '') {
                alert('총 라운드를 입력하세요');
                return false;
            }

            if($('#division').val() == '') {
                alert('클래스를 선택하세요');
                return false;
            }

            if($('#scoreInfoSeq').val() == '') {
                alert('점수방식을 선택하세요');
                return false;
            }
            if($('#scoreInfoSeq').val() == '') {
                alert('점수방식을 선택하세요');
                return false
            }
            $('#frm').submit();
        });

        $('.btnDel').click(function (e) {
            e.preventDefault();
            if(confirm("이벤트를 삭제 합니다.")) {
                common.ajax("DELETE", "/admin/event/eventInfo/del/" + $(this).data('eventinfoseq'), "", "json", 'application/json; charset=utf-8', function (data) {
                    if(data.code == '0000') {
                        alert('이벤트를 삭제 하였습니다.');
                        location.reload();
                    } else {
                        alert('이벤트 삭제 실패 하였습니다. 다시 시도 해 주시기 바립니다.');
                    }
                });
            }

        });
    },
    eventInit:function(){
        $('#eventInfoSeq').change(function () {
            $('#round').html('<option value="">선택</option>');
            let maxRound = $("#eventInfoSeq option:selected").data("round");
            let appendOption = "";
            for (let i=1;i<=maxRound;i++){
                appendOption += "<option value="+i+">"+i+"라운드 </option>";
            }
            $('#round').append(appendOption);
        });
        $('#btnSave').click(function(e){
            e.preventDefault();
            if($('#eventInfoSeq').val() == ""){
                alert('대회명을 선택하세요');
                return false;
            }
            if($('#round').val() == "") {
                alert('라운드를 선택하세요');
                return false;
            }
            if($('#leagueData').val() == "") {
                alert('리그 데이터 파일을 입력 하세요');
                return false;
            }
            $('#background').show(200);
            $('#frm').submit();
        })
    },
    resultInit: function () {
        $('#eventInfoSeq').change(function () {
            $('#round').html('<option value="0">전체</option>');
            let maxRound = $("#eventInfoSeq option:selected").data("round");
            let appendOption = "";
            for (let i = 1; i <= maxRound; i++) {
                appendOption += "<option value=" + i + ">" + i + "라운드 </option>";
            }
            $('#round').append(appendOption);
        });
        $('#btnSearch').click(function (e) {
            e.preventDefault();
            if($('#eventInfoSeq').val() == "") {
                alert('대회명을 선택하세요');
                return false;
            }

            let data={
                eventInfoSeq:$('#eventInfoSeq').val()
                , round : $('#round').val()
            }

            common.ajax("GET", "/admin/event/result/search", data, "html", 'application/x-www-form-urlencoded; charset=UTF-8', function (data) {
                $('#resultTable').html(data);
            },function (jqXHR, textStatus, errorThrown){
                $('#resultTable').html('');
            });
        })
    },
    roundResultInit:function(){
        $('#btnResultDel').click(function (e) {
            e.preventDefault();
            if(confirm("해당 라운드를 삭제 하시겠습니까?")) {
                let round = $(this).data('round');
                let data = {
                    "eventInfoSeq" : $(this).data('eventinfoseq'),
                    "round": $(this).data('round')
                };
                common.ajax("DELETE", "/admin/event/result/del", JSON.stringify(data), "json", 'application/json; charset=utf-8', function (data) {
                    if(data.code == '0000') {
                        alert('해당 대회의 '+ round+'라운드를 삭제 하였습니다.');
                        location.reload();
                    } else {
                        alert('삭제 실패 하였습니다. 다시 시도 해 주시기 바립니다.');
                    }
                });
            }
        });

        $('#btnTeamPoint').click(function(e){
            e.preventDefault();
            let round = $(this).data('round');
            let eventInfoSeq = $(this).data('eventinfoseq');
            let data = {
                "eventInfoSeq": $(this).data('eventinfoseq'),
                "round": $(this).data('round')
            };
            common.ajax("POST","/admin/team/teamScore/save", data,"json",'',function(data){
                if(data.success) {
                    alert('팀포인트계산이 완료 되었습니다.');
                }
            });


        });

        $('.lapDetail').click(function () {

            let data = {carId: $(this).data('carid'),
                eventInfoSeq : $('#eventInfoSeq').val(),
                round : $('#round').val(),
                playerName : $(this).data('playername')
            };
            common.ajax("GET", "/admin/event/result/lapDetail", data, 'html', '', function (data) {
                $('#divLayer').html(data);
                $('#divLayer').bPopup();
            });

        });

    }
};