/**
 * Created by jerry on 2016-07-14.
 */
$(document).ready(function () {
    var lastIndex;

    $(function () {

        var promotingOption = {
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": true,
            "info": true,
            "autoWidth": false
        };

        var promotingTableList = [
            {
                "selector": "#promotingInfo",
                "option": promotingOption
            }
        ];

        promotingTableList.forEach(function (table) {
            lacs.makeDataTable(table);
        });

    });
    //수정 클릭시 데이터 호출
    $(".modifyingPromotion").on("click", function () {
        var indexNumber = $(this).attr("value");
        lastIndex = indexNumber;
        var locationName = $("#locationName" + indexNumber).text();
        var locationURL = $("#locationURL" + indexNumber).text();
        var worker = $("#worker" + indexNumber).text();
        $("#locationNameInputForm").val(locationName);
        $("#locationURLInputForm").val(locationURL);
        $("#workerInputForm").val(worker);
    });

    $("#modifyPromotionsSubmit").on("click", function () {
        var title = $("#titleInfo").text();
        alert(lastIndex + "삭제후생성");
        promotingDelete(title, lastIndex);
    });

    //홍보자료 삭제 버튼
    $(".deletingPromotion").on("click", function () {
        var indexNumber = $(this).attr("value");
        var title = $("#titleInfo").text();
        alert(indexNumber + "삭제");
        console.log(indexNumber + "$$" + title);
        promotingDelete(title, indexNumber);
        location.reload(); //페이지 다시실행
    });

    //홍보자료 삭제를 위한 함수
    function promotingDelete(title, indexNumber) {
        lacs.ajaxWrapper(
            "/deletePromotion",
            {"title": title, "indexNumber": indexNumber},
            function () {
                alert("홍보자료삭제성공!");
            }
        )
    };

});
