/**
 * Created by jerry on 2016-05-18.
 */

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

function progressUpdate1(title, order, progress){
    $.ajax({
        type: "POST",
        url: "../seminar/taskProgress",
        data: {"title": title, "order":order, "progress":progress},
        dataType: "html",
        success: function(){
            alert( "Data Saved ");
        }
    });
}

//document.getElementById("selectedInstructor").addEventListener("click", function(){} 과 같음

////TODO 중복구분이 많아서 리팩토링 필요함 - 재열
//강사선정 : 작업번호 0 : progress[0]
$("#selectedInstructor,#selectedInstructorList").on('click', function () {
    ////TODO 제이쿼리사용후 중복코드 삭제필요 01/07/2016 - 재열
    var target = document.getElementById("selectedInstructor");
    if(target==null){target = document.getElementById("selectedInstructorList");}
    if (target.selectedIndex == 0) {
        document.getElementById("status2").style.visibility = 'hidden'
        document.getElementById("status1").style.visibility = 'visible'
        progress[0] = false;
    } else {
        document.getElementById("status1").style.visibility = 'hidden'
        document.getElementById("status2").style.visibility = 'visible'
        progress[0] = true;
    }
    progressF("progress-bar",getProgressValue());
});

//작업자선정  : 작업번호 1 : progress[1]
$("#selectedWorker,#selectedWorkerList").on('click', function () {
    var target = document.getElementById("selectedWorker");
    if(target==null){target = document.getElementById("selectedWorkerList");}
    if (target.selectedIndex == 0) {
        document.getElementById("status4").style.visibility = 'hidden'
        document.getElementById("status3").style.visibility = 'visible'
        progress[1] = false;
    } else {
        document.getElementById("status3").style.visibility = 'hidden'
        document.getElementById("status4").style.visibility = 'visible'
        progress[1] = true;
    }
    progressF("progress-bar",getProgressValue());
});

//주제입력  : 작업번호 2 : progress[2]
$('#subject').change(function () {
    statusChangeforTextBox(document.getElementById("subject").value, 5);
    progressF("progress-bar",getProgressValue());
})

//날짜입력  : 작업번호 3 : progress[3]
$('#date').change(function () {
    statusChangeforTextBox(document.getElementById("date").value, 7);
    progressF("progress-bar",getProgressValue());
})

//제출
$('#submit').on('click', function () {
    alert("제출버튼눌림");
    var title = $('#currentSeminarTitle').val();
    var order = $('#taskOrder').val();
    var progress = getProgressValue().toString();
    progressUpdate1(title,order,progress);
    document.getElementById("progress").value = progress;
});

//강사섭외에서 수정버튼 클릭시,modal정보 연동을 위한 함수
$("#changeCasting").on('click', function () {
    var selectedINST = $("#selectedInstructor").text(); //선택 된 강사 명
    var selectedWorker = $("#selectedWorker").text(); //선택 된 담당자 명
    var progress = $("#progressInfo").text(); //선택된 진행률

    alert(selectedINST +" "+ selectedWorker +" "+ progress)

    $("#selectedInstructorList").val(selectedINST).val(); //이렇게 왜 되나? 우연히발견
    $("#selectedWorkerList").val(selectedWorker).val(); //이렇게 왜 되나? 우연히발견

    //총 진행률/25를 수행하여 순차적으로 진행된 progress 값 업데이트
    var temp = progress.replace("%",'')/25;

    for(var i=0;i<temp;i++){

        progress[i] = true;
        //        alert("progress"+i+" "+progress[i]);
    }
    
    $("#progress-bar").css("width",progress);
    $("#progress-bar").text(progress);

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
function progressF(elementID,getProgressValue) {
    var lastProgressValue= getProgressValue+"%";
    $("#"+elementID).css("width",lastProgressValue);
    $("#"+elementID).text(lastProgressValue);
}

//최초 강사섭외 페이지(castingInstructorForm.html 에서 진행률을 구하기 위한 폼
function getProgressValue() {
    var progressValue = 0;
    for (var i = 0; i < progress.length; i++) {
        if (progress[i] == true) {
            progressValue++
        }
    }
    var lastProgressValue = progressValue * 25;
    if (lastProgressValue >= 100) {
        lastProgressValue = 100;
    } // 100보다 커지면 100으로 고정함. ex) 세부항목이 7개 * 15 = 105 로 나오기에.. 100으로 고정
    return lastProgressValue;
}
