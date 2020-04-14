var board = {
    editor : null,
    init:function(){

        $('#btn-more').click(function(){
            let currentPage = $('#currentPage').val();
            currentPage = Number(currentPage) + 1;
            $('#currentPage').val(currentPage);
            let data = {
                currentPage: currentPage
            };

            common.ajax("GET", '/bbs/moreList/' + $('#nameSeq').val(), data, "html", 'application/json; charset=utf-8', function (data) {
                $('#boardList').append(data);
            });
        });

        $('#write').click(function(){
            let url = location.pathname;
            location.href=url+"/write";
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
        board.fnCkeditorSet();

        $('#btn-save').click(function () {

            if($('#title').val() == ''){
                alert("title을 입력하세요");
                return;
            }

            if($('#password').val() == ''){
                alert("password을 입력하세요");
                return;
            }

            if(board.editor.getData() == ''){
                alert('Content를 입력하세요');
                return;
            }

            let data ={
                nameSeq : $('#nameSeq').val(),
                title : $('#title').val(),
                content : board.editor.getData(),
                regId : $('#regId').val(),
                password : $('#password').val()
            };
            console.log(data);
            common.ajax("POST","/bbs/saveBbs", JSON.stringify(data), "json", 'application/json; charset=utf-8', function (data) {
                 if (data.code == '0000') {
                     alert('글이 등록되었습니다.');
                     window.location.href = '/bbs/' + data.nameSeq;
                 } else {
                     alert('글 등록이 실패하였습니다. 다시 시도해주시기 바립니다.');
                 }
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

            common.ajax("POST","/bbs/saveComment", JSON.stringify(data), "json",'application/json; charset=utf-8', function (data) {
                if (data.code == '0000') {
                    alert('댓글이 등록되었습니다.');
                    let html = '<tr>' +
                        '<td>' + data.regId + '</td> ' +
                        '<td>' + data.comment + '</td> ' +
                        '</tr>';
                    $('#commentList').append(html);
                    $('#comment').val('');
                    $('#regId').val('')
                } else {
                    alert('댓글 등록이 실패하였습니다. 다시 시도해주시기 바립니다.');
                }
            });
        });

        $('#password').keypress(function (e) {
            if (e.keyCode == '13') {
                board.modifCheck();
            }
        })

        $('#modifyCheck').click(function(){
           board.modifCheck();
        });
    }, modifCheck: function(){
        let data = {
            password: $('#password').val(),
            seq: $('#bbsSeq').val(),
            nameSeq: $('#nameSeq').val()
        };

        common.ajax("POST", "/bbs/modifyCheck", JSON.stringify(data), "json", 'application/json; charset=utf-8', function (data) {
            console.log(data);
            if (data.code == '0000') {
                $('#check').val('Y');
                $('#frm').submit();
            } else {
                alert('비밀번호가 틀립니다.');
            }
        });
    }
    ,update:function(){
        board.fnCkeditorSet();


        $('#btn-update').click(function () {
            if ($('#title').val() == '') {
                alert("title을 입력하세요");
                return;
            }

            if (board.editor.getData() == '') {
                alert('Content를 입력하세요');
                return;
            }
            let data = {
                nameSeq: $('#nameSeq').val(),
                seq: $('#seq').val(),
                title: $('#title').val(),
                content: board.editor.getData()
            };

            common.ajax("POST","/bbs/updateBbs", JSON.stringify(data), "json", 'application/json; charset=utf-8', function (data) {
                if (data.code == '0000') {
                    alert('글이 수정되었습니다.');
                    window.location.href = '/bbs/' + data.nameSeq + '/' + data.bbsSeq;
                } else {
                    alert('글 수정이 실패하였습니다. 다시 시도해주시기 바립니다.');
                }
            });

        });
        $('#btn-del').click(function () {
            if (confirm("게시물을 삭제 하시겠습니까?")) {
                let data = {
                    nameSeq: $('#nameSeq').val(),
                    seq: $('#seq').val()
                };

                common.ajax("POST", "/bbs/delete", JSON.stringify(data), "json", 'application/json; charset=utf-8', function (data) {
                    if (data.code == '0000') {
                        alert('글을 삭제 하였습니다.');
                        window.location.href = '/bbs/' + $('#nameSeq').val();
                    } else {
                        alert('글 삭제 실패하였습니다. 다시 시도해주시기 바립니다.');
                    }
                });
            }


        });
    }
    , fnCkeditorSet: function () {
        ClassicEditor
            .create(document.querySelector('#content'), {
                toolbar: {
                    items: [
                        'heading',
                        '|',
                        'bold',
                        'italic',
                        'link',
                        'bulletedList',
                        'numberedList',
                        '|',
                        'indent',
                        'outdent',
                        '|',
                        'imageUpload',
                        'blockQuote',
                        'insertTable',
                        'mediaEmbed',
                        'undo',
                        'redo'
                    ]
                },
                language: 'ko',
                ckfinder: {
                    uploadUrl: '/ckEditorImgUpload'
                }
                , image: {
                    toolbar: [
                        'imageStyle:full',
                    ],
                    resizeUnit: 'px'
                },
                table: {
                    contentToolbar: [
                        'tableColumn',
                        'tableRow',
                        'mergeTableCells'
                    ]
                }
            })
            .then(editor => {
                board.editor = editor;
            })
            .catch(error => {

            });
    }

};