/**
 * Created by donghoon on 2016. 6. 4..
 */

/**
 * ui toggle btn.
 * @param completeBtn
 * @param restartBtn
 */
function toggleBtn(complete, restart, next, completeVal, restartVal, disableVal) {
    var progressBar = $(".progress-bar");
    var completeBtn = $("#" + complete);
    var restartBtn = $("#" + restart);
    var nextBtn = $("#" + next);

    completeBtn.click(function () {
        $(this).css("display", "none");
        restartBtn.css("display", "block");
        nextBtn.attr("disabled", disableVal);
        progressBar.attr("aria-valuenow", completeVal)
            .css("width", completeVal + "%").text(completeVal + "%");
    });
    restartBtn.click(function () {
        $(this).css("display", "none");
        completeBtn.css("display", "block");
        nextBtn.attr("disabled", !disableVal);
        progressBar.attr("aria-valuenow", restartVal)
            .css("width", restartVal + "%").text(restartVal + "%");
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
