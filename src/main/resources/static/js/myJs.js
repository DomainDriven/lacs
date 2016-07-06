/**
 * Created by jerry on 2016-05-18.
 */
$(window).load(function () {
    /*
     진행도 확인을 위한 값. 세부 작업은 4개. 완료시 각 배열 인덱스의 인자는 true로 변경
     */

    var progress = [false, false, false, false];


//선택한 값 넘겨주기
    function getSelectValue() {
        var target = document.getElementById("selectedInstructor");
        var selectedInstructorId = target.options[target.selectedIndex].id;
        alert(selectedInstructorId);
        document.getElementById("phone").value = document.getElementById("pn" + selectedInstructorId).innerHTML;
    }

//AJAX를 사용한 강사연락처 가져오기 기능
    function loadDoc() {
        var target = document.getElementById("selectedInstructor");
        /*alert("Start"+target.selectedIndex)*/
        if (target.selectedIndex != 0) {

            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    document.getElementById("phone").value = xhttp.responseText;
                }
            };
            xhttp.open("POST", "../findInstructorsPhoneNumber", true);
            /* xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); 헤더설정시*/
            xhttp.send(target.selectedIndex);
        }
    }

    function progressUpdate1(title, order, progress) {
        $.ajax({
            type: "POST",
            url: "../seminar/taskProgress",
            data: {"title": title, "order": order, "progress": progress},
            dataType: "html",
            success: function () {
                alert("Data Saved");
            }
        });
    }

//document.getElementById("selectedInstructor").addEventListener("click", function(){} 과 같음

////TODO 중복구분이 많아서 리팩토링 필요함 - 재열
//강사선정 : 작업번호 0 : progress[0]
    $("#selectedInstructor, #selectedInstructorList").on('change', function () {
        ////TODO 제이쿼리사용후 중복코드 삭제필요 01/07/2016 - 재열
        var target = $("#selectedInstructor option").index($("#selectedInstructor option:selected"));
        if (target == -1) {
            target = $("#selectedInstructorList option").index($("#selectedInstructorList option:selected"));
        }
        if (target == 0) {
            document.getElementById("status2").style.visibility = 'hidden'
            document.getElementById("status1").style.visibility = 'visible'
        } else {
            document.getElementById("status1").style.visibility = 'hidden'
            document.getElementById("status2").style.visibility = 'visible'
        }
        progressF("progress-bar", getProgressValue(0, target));
    });

//작업자선정  : 작업번호 1 : progress[1]
    $("#selectedWorker, #selectedWorkerList").on('change', function () {
        var target = $("#selectedWorker option").index($("#selectedWorker option:selected"));
        if (target == -1) {
            target = $("#selectedWorkerList option").index($("#selectedWorkerList option:selected"));
        }
        if (target == null) {
            target = document.getElementById("selectedWorkerList");
        }
        if (target.selectedIndex == 0) {
            document.getElementById("status4").style.visibility = 'hidden'
            document.getElementById("status3").style.visibility = 'visible'
        } else {
            document.getElementById("status3").style.visibility = 'hidden'
            document.getElementById("status4").style.visibility = 'visible'
        }
        progressF("progress-bar", getProgressValue(1, target));
    });

//주제입력  : 작업번호 2 : progress[2]
    $('#subject').change(function () {
        var target = 0;
        if ($('#subject').val() != "") {
            target = 1;
        }
        statusChangeforTextBox(document.getElementById("subject").value, 5);
        progressF("progress-bar", getProgressValue(2, target));
    });

//날짜입력  : 작업번호 3 : progress[3]
    $('#date').change(function () {
        var target = 0;
        if ($('#date').val() != "") {
            target = 1;
        }
        statusChangeforTextBox(document.getElementById("date").value, 7);
        progressF("progress-bar", getProgressValue(3, target));
    });

//제출
    $('#submit').on('click', function () {
        alert("제출버튼눌림");
        var title = $('#currentSeminarTitle').val();
        var order = $('#taskOrder').val();
        var progress = getProgressValue().toString();
        progressUpdate1(title, order, progress);
        document.getElementById("progress").value = progress;
    });

//강사섭외에서 수정버튼 클릭시,modal정보 연동을 위한 함수
    $("#changeCasting").on('click', function () {
        var selectedINST = $("#selectedInstructor").text(); //선택 된 강사 명
        var selectedWorker = $("#selectedWorker").text(); //선택 된 담당자 명
        var progressInfo = $("#progressInfo").text(); //선택된 진행률
        
        //수정버튼 클릭시 진행도 추가를 1회로 제한하기 위한 사전요건. 예를 들어 각 항목에 값이 이미 있고, 
        // 진행도가 반영된 상태라면 해당 progress[] 위치를 true로 변경함. 
        if (selectedINST != '') {
            progress[0] = true;
        }
        if (selectedWorker != '') {
            progress[1] = true;
        }
        if ($("#subjectModal").val() != '') {
            progress[2] = true;
        }
        if ($("#dateModal").val() != '') {
            progress[3] = true;
        }
        

        alert(selectedINST + " " + selectedWorker + " " + progressInfo)

        $("#selectedInstructorList").val(selectedINST).val(); //이렇게 왜 되나? 우연히발견
        $("#selectedWorkerList").val(selectedWorker).val(); //이렇게 왜 되나? 우연히발견

        //총 진행률/25를 수행하여 순차적으로 진행된 progress 값 업데이트
        $("#progress-bar").css("width", progressInfo);
        $("#progress-bar").text(progressInfo);

    });

//TextBox에 글씨가 쓰였으면 '완료' 아니면 '진행중'
    function statusChangeforTextBox(isFill, idIndex) {
        if (isFill != '') {
            document.getElementById("status" + idIndex).style.visibility = 'hidden'
            document.getElementById("status" + (idIndex + 1)).style.visibility = 'visible'
            progress[(idIndex / 2 - 0.5)] = true;
        } else {
            document.getElementById("status" + (idIndex + 1)).style.visibility = 'hidden'
            document.getElementById("status" + idIndex).style.visibility = 'visible'
            progress[(idIndex / 2 - 0.5)] = false;
        }
    }

//현재 진행률을 볼 수 있게 하는 함수. 인자로 progress의 id와 진행값(%)를 받는다.
    function progressF(elementID, getProgressValue) {
        var lastProgressValue = getProgressValue + "%";
        $("#" + elementID).css("width", lastProgressValue);
        $("#" + elementID).text(lastProgressValue);
    }

    /* 진행률을 구하기 위한 함수
     * function getProgressValue(tasknum,indexNumber)
     * taskNumber는 강사섭외 폼 태그(Html form)에서 input태그의 순번. 예) 강사선택(select)는 0이다.
     * selectedNumber는 select 형태의 input중 선택된 순번을 의미한다.
     * 아래의 함수에서는 진행률이 올라갔을 경우 progress[tasknum]에 표기하여 진행률의 증감을 한번만 할 수 있도록 한다.
     *
     * */

    function getProgressValue(taskNumber, selectedNumber) {
        var progressValue = 0;
        for (var i in progress) {
            if (progress[i] == true) progressValue += 25;
        }

        if ((progress[taskNumber] == false) && (selectedNumber != 0)) {
            if (progressValue < 100) {
                progressValue += 25;
            } else {
                progressValue = 100;
            }
            progress[taskNumber] = true;// 진행률이 올라간 상태면 false에서 true로 바뀜
        } else if (selectedNumber == 0) {
            if (progressValue > 0) {
                progressValue -= 25;
            } else {
                progressValue = 0;
            }
            progress[taskNumber] = false;
        }
        return progressValue;
    }
});
