var banner = {

    init:function(){
        $('#btnSave').click(function (e) {
            e.preventDefault();
            $('#frm').submit();
        });

        $('.btnDel').click(function (e) {
            e.preventDefault();
            let seq = $(this).data("seq");
            let data = {
                'seq' : seq
            }

            common.ajax("POST", "/admin/banner/del", JSON.stringify(data), "json", 'application/json; charset=utf-8', function (data) {
                if (data.code == '0000') {
                    alert('배너를 삭제하였습니다.');
                    location.reload();
                } else {
                    alert('배너 삭제 실패하였습니다.');
                }
            });


        });
    }
};