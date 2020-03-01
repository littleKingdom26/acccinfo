var board = {
    init:function(){

        $('#btn-more').click(function(){
            let currentPage = $('#currentPage').val();
            currentPage = Number(currentPage) + 1;
            $('#currentPage').val(currentPage);
            let data = {
                currentPage: currentPage
            };

            $.ajax({
                type: 'GET',
                url: '/bbs/moreList/'+$('#nameSeq').val(),
                dataType: 'html',
                data: data
            }).done(function(data){
                $('#boardList').append(data);
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        });

    },
    moreBtnCheck:function(totalCount,currentPage,pageCount){
        if(totalCount <= (currentPage * pageCount)){
            $('#btn-more').hide();
        }else{
            $('#btn-more').show();
        }

    },
    write:function(){
        $('#btn-save').click(function () {
            let data ={
                nameSeq : $('#nameSeq').val(),
                title : $('#title').val(),
                content : $('#content').val(),
                regId : $('#regId').val(),
                password : $('#password').val()
            };
            $.ajax({
                type: 'POST',
                url: '/bbs/saveBbs',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (data) {
                if(data.code == '0000'){
                    alert('글이 등록되었습니다.');
                    window.location.href = '/bbs/'+data.nameSeq;
                }else{
                    alert('글 등록이 실패하였습니다. 다시 시도해주시기 바립니다.');
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        });
    },
    view : function(){
        $('.btn-comment-save').click(function () {

            let seq = $(this).data("seq");
            if($('#comment').val()==''){
                alert('댓글을 입력하세요');
                return;
            }
            if($('#regId').val()==''){
                alert('작성자를 입력하세요');
                return;
            }
            let data = {
                comment: $('#comment').val(),
                bbsSeq: seq,
                regId: $('#regId').val()
            };
            $.ajax({
                type: 'POST',
                url: '/bbs/saveComment',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (data) {
                if (data.code == '0000') {
                    console.log(data);
                    alert('댓글이 등록되었습니다.');
                    let html = '<tr>' +
                        '<td>'+data.regId+'</td> '+
                        '<td>'+data.comment+'</td> '+
                        '</tr>';
                    $('#commentList').append(html);
                } else {
                    alert('댓글 등록이 실패하였습니다. 다시 시도해주시기 바립니다.');
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });

        });
    }
    ,update:function(){
        $('#btn-update').click(function () {
            let data = {
                nameSeq: $('#nameSeq').val(),
                seq: $('#seq').val(),
                title: $('#title').val(),
                content: $('#content').val(),
                regId: $('#regId').val(),
                password: $('#password').val()
            };
            $.ajax({
                type: 'POST',
                url: '/bbs/updateBbs',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (data) {
                if (data.code == '0000') {
                    alert('글이 등록되었습니다.');
                    window.location.href = '/bbs/' + data.nameSeq+'/'+data.bbsSeq;
                } else {
                    alert('글 등록이 실패하였습니다. 다시 시도해주시기 바립니다.');
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        });
    }

};