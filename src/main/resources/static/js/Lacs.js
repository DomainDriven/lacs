/**
 * Created by donghoon on 2016. 7. 27..
 */

/**
 * Lacs 어플리케이션 메인 모듈 정의.
 */
function Lacs() {
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

    makeDateRangePicker: function (selector) {
        var picker = $(selector).daterangepicker();
        return picker;
    },

    makeInputMask: function (selector) {
        var mask = $(selector).inputmask("999-9999-9999");
        return mask;
    }

};

/**
 * Lacs 모듈 인스턴스화. -- public.
 * @type {Lacs}
 */
var lacs = new Lacs();
