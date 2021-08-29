var player = {

    init:function(){
        $('#btnSearch').click(function () {
            $('#searchFrm').submit();
        });

        $('.driverDetail').click(function(){
           let data = {'playerId' : $(this).data('playerid')+''};
            common.ajax("GET","/admin/player/detail",data,'html','',function(data){
                $('#divLayer').html(data);
                $('#divLayer').bPopup();
            });

        });
    }
};