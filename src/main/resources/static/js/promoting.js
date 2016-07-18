/**
 * Created by jerry on 2016-07-14.
 */
$(window).load(function () {
    $(function () {
        $('#promotingInfo').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": true,
            "info": true,
            "autoWidth": false
        });
    });

    $(".deletingPromotion").on("click",function(){
        var indexNumber = $(this).attr("value");
        var title = $("#titleInfo").text();
        console.log(indexNumber+"$$"+title);
        promotingDelete(title,indexNumber);
    });

    function promotingDelete(title,indexNumber) {
        $.ajax({
            type: "POST",
            url: "/deletePromotion",
            data: {"title": title,"indexNumber": indexNumber},
            dataType: "html",
            success: function () {
                location.reload(); //페이지 다시실행
            }
        });
    }
});
