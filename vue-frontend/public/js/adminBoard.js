var board = {
    editor : null,
    init:function(){
        $('#btnRegister').click(function () {
            location.href = "/admin/board/"+$('#nameSeq').val()+"/write";
        });

        $('#btnSearch').click(function () {
            $('#currentPage').val(1);
            $('#searchFrm').submit();
        });

        $('.detailBtn').click(function () {
            $('#searchFrm').attr('action','/admin/board/' + $('#nameSeq').val() + '/' + $(this).data('bbsseq'));
            $('#searchFrm').submit();
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

            if ($('#title').val() == '') {
                alert('제목을 입력하세요');
                return;
            }

            if (board.editor.getData() == '') {
                alert('내용을 입력하세요');
                return;
            }else{
                $('#content').val(board.editor.getData());
            }
            $('#frm').submit();

        });

        $('#btn-cancel').click(function () {
            $('#searchFrm').submit();
        });
    },
    view : function(){
        board.fnCkeditorSet();
        $('#btn-list').click(function () {
            $('#searchFrm').submit();
        });

        $('#btn-update').click(function () {
            if ($('#title').val() == '') {
                alert('제목을 입력하세요');
                return;
            }

            if (board.editor.getData() == '') {
                alert('내용을 입력하세요');
                return;
            } else {
                $('#content').val(board.editor.getData());
            }
            $('#frm').submit();
        });

        $('.commentDelBtn').click(function (e) {
            e.preventDefault();
            console.log($(this).data('seq'));
            let data = {
                seq: $(this).data('seq')
            };
            common.ajax("POST", "/admin/board/delComment", JSON.stringify(data), "json", 'application/json; charset=utf-8', function (data) {
                if (data.code == '0000') {
                    alert('댓글을 삭제하였습니다.');
                    location.reload();
                } else {
                    alert('글 등록이 실패하였습니다. 다시 시도해주시기 바립니다.');
                }
            });
        });

        $('#btn-delete').click(function (e) {
            e.preventDefault();
            let data = {
                seq: $('#seq').val()
            };
            console.log(data);
            common.ajax("POST", "/admin/board/delBoard", JSON.stringify(data), "json", 'application/json; charset=utf-8', function (data) {
                if (data.code == '0000') {
                    alert('게시물을 삭제하였습니다.');
                    $('#searchFrm').submit();
                } else {
                    alert('글 등록이 실패하였습니다. 다시 시도해주시기 바립니다.');
                }
            });

        });

    }
    ,update:function(){
        board.fnCkeditorSet();
        $('#btn-update').click(function () {
            let data = {
                nameSeq: $('#nameSeq').val(),
                seq: $('#seq').val(),
                title: $('#title').val(),
                content: board.editor.getData(),
                regId: $('#regId').val(),
                password: $('#password').val()
            };

            common.ajax("POST","/bbs/updateBbs", JSON.stringify(data), "json", 'application/json; charset=utf-8', function (data) {
                if (data.code == '0000') {
                    alert('글이 등록되었습니다.');
                    window.location.href = '/bbs/' + data.nameSeq + '/' + data.bbsSeq;
                } else {
                    alert('글 등록이 실패하였습니다. 다시 시도해주시기 바립니다.');
                }
            });

        });
    }
    ,fnCkeditorSet :function(){
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
                ,image: {
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