/**
 * Created by donghoon on 2016. 6. 4..
 */

/**
 * ui toggle btn.
 * @param completeBtn
 * @param restartBtn
 */
function toggleBtn(completeBtn, restartBtn, nextBtn, completeVal, restartVal, disableVal) {
    var progressBar = $(".progress-bar");
    var title = $("#currentSeminarTitle").val();
    var order = $("#taskOrder").val();

    completeBtn.click(function () {
        $(this).css("display", "none");
        restartBtn.css("display", "block");
        nextBtn.attr("disabled", disableVal);
        progressBar.attr("aria-valuenow", completeVal)
            .css("width", completeVal + "%").text(completeVal + "%");
        ajaxWrapper("/seminar/taskProgress", {
            title: title,
            order: order,
            progress: completeVal
        });
    });
    restartBtn.click(function () {
        $(this).css("display", "none");
        completeBtn.css("display", "block");
        nextBtn.attr("disabled", !disableVal);
        progressBar.attr("aria-valuenow", restartVal)
            .css("width", restartVal + "%").text(restartVal + "%");
        ajaxWrapper("/seminar/taskProgress", {
            title: title,
            order: order,
            progress: restartVal
        });
    });
}

/**
 * backBtn class 를 붙인 tag 를 클릭하면 뒤로가기.
 *
 * css class tag 만 추가하면 동작시키키 위해서 function 으로 감싸지 않음.
 */
$(".backBtn").click(function () {
    history.back();
});

/**
 * ajax call 에서 공통부분을 뽑아낸 함수.
 * @param url
 * @param successFunc
 * @param errorFunc
 */
function ajaxWrapper(url, data, successFunc, errorFunc) {
    $.ajax({
        type: "post",
        async: true,
        dataType: "json",
        url: url,
        data: data,
        success: successFunc,
        error: errorFunc
    })
}
