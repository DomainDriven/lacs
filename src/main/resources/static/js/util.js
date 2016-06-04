/**
 * Created by donghoon on 2016. 6. 4..
 */

/**
 * ui toggle btn.
 * @param completeBtn
 * @param restartBtn
 */
function toggleBtn(completeBtn, restartBtn) {
    completeBtn.click(function () {
        $(this).css("display", "none");
        restartBtn.css("display", "block");
    });
    restartBtn.click(function () {
        $(this).css("display", "none");
        completeBtn.css("display", "block");
    });
}
