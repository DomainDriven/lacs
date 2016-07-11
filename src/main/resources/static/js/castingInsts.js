/*
 castingInstructor.html에서만 쓰이는 JS
 */
$(window).load(function () {

    /*
     진행도 확인을 위한 값. 세부 작업은 4개. 완료시 각 배열 인덱스의 인자는 true로 변경
     */

    var selectedField;
    var selectedFieldIDs = ["selectedInstructor", "selectedWorker", "selectedTitle", "selectedDate"];

    checkFieldStatusByProgressArray();

// 강사섭외의 각 입력 필드가 채워져 있을 경우 진행상황 check , selectedField[]값 기준. CStatus는 입력필드가 아닌 곳을 이야기한다.
    function checkFieldStatusByProgressArray() {
        selectedField = [false, false, false, false];
        var visibleCStatus;
        var hiddenCStatus;
        var visibleStatus;
        var hiddenStatus;
        for (var i = 1; i <= selectedField.length; i++) {
            if ($("#" + selectedFieldIDs[i-1]).text() != '') {
                selectedField[i-1] = true;
            } //각 필드에 값이 있으면 progress 값 업데이트
            console.log(i + "" + selectedField[i-1]);
            if (selectedField[i-1] == true) {
                 visibleCStatus = "Cstatus" + (i*2);
                 hiddenCStatus = "Cstatus" + ((i*2)-1);
                 visibleStatus = "status" + (i*2);
                 hiddenStatus = "status" + ((i*2)-1);
                console.log(visibleStatus + "" + hiddenStatus);
            }else{
                visibleCStatus = "Cstatus" + ((i*2)-1);
                hiddenCStatus = "Cstatus" + (i*2);
                visibleStatus = "status" + ((i*2)-1);
                hiddenStatus = "status" + (i*2);
            }
            document.getElementById(hiddenCStatus).style.visibility = 'hidden';
            document.getElementById(visibleCStatus).style.visibility = 'visible';
            document.getElementById(hiddenStatus).style.visibility = 'hidden';
            document.getElementById(visibleStatus).style.visibility = 'visible';
        }
    }
});
