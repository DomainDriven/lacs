/**
 * Created by donghoon on 2016. 7. 27..
 */

/**
 * Lacs 어플리케이션 메인 모듈 정의.
 */
function Lacs() {

    /**
     * backBtn class 를 붙인 tag 를 클릭하면 뒤로가기.
     *
     * css class tag 만 추가하면 동작시키키 위해서 function 으로 감싸지 않음.
     */
    $(".backBtn").click(function () {
        history.back();
    });

};

/**
 * Lacs 어플리케이션 에서 공통으로 사용할 함수 정의.
 * @type {{makeDataTable: Lacs.makeDataTable, makeDateRangePicker: Lacs.makeDateRangePicker}}
 */
Lacs.prototype = {

    makeDataTable: function (table) {
        var table = $(table.selector).DataTable(table.option);
        return table;
    },

    makeDatePicker: function (selector) {
        var picker = $(selector).datepicker();
        return picker;
    },

    makeInputMask: function (selector) {
        var mask = $(selector).inputmask("999-9999-9999");
        return mask;
    },

    /**
     * ajax call 에서 공통부분을 뽑아낸 함수.
     * @param url
     * @param successFunc
     * @param errorFunc
     */
    ajaxWrapper: function (url, data, successFunc, errorFunc, dataType) {
        if (dataType === null)
            dataType = "json";

        $.ajax({
            type: "post",
            async: true,
            url: url,
            data: data,
            success: successFunc,
            error: errorFunc,
            dataType: dataType
        })
    },

    /**
     * ui toggle btn.
     * @param completeBtn
     * @param restartBtn
     */
    progressToggleBtn: function (completeBtn, restartBtn, nextBtn, completeVal, restartVal, disableVal) {
        var progressBar = $(".progress-bar");
        var title = $("#currentSeminarTitle").val();
        var order = $("#taskOrder").val();

        completeBtn.click(function () {
            $(this).css("display", "none");
            restartBtn.css("display", "block");
            progressBar.attr("aria-valuenow", completeVal).css("width", completeVal + "%").text(completeVal + "%");
            $.ajax({
                type: "post",
                async: true,
                dataType: "json",
                url: "/seminar/taskProgress",
                data: {
                    title: title,
                    order: order,
                    progress: completeVal
                },
                success: function () {
                    nextBtn.attr("disabled", disableVal);
                }
            })
        });
        restartBtn.click(function () {
            $(this).css("display", "none");
            completeBtn.css("display", "block");
            progressBar.attr("aria-valuenow", restartVal).css("width", restartVal + "%").text(restartVal + "%");
            $.ajax({
                type: "post",
                async: true,
                dataType: "json",
                url: "/seminar/taskProgress",
                data: {
                    title: title,
                    order: order,
                    progress: restartVal
                },
                success: function () {
                    nextBtn.attr("disabled", !disableVal);
                }
            });
        });
    }

};

/**
 * Lacs 모듈 인스턴스화. -- public.
 * @type {Lacs}
 */
var lacs = new Lacs();
