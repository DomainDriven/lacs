/**
 * Created by jerry on 2016-05-18.
 */

/*
 진행도 확인을 위한 값. 세부 작업은 7개. 완료시 각 배열 인덱스의 인자는 true로 변경
 progress[(idIndex/2-0.5)] 와 같이 사용하는 이유는, 각 작업의 숫자는 1,3,5,7로 증가 되기에 2로 나누어서 본래 숫자로 만들었으며, 소수점자리 절삭을 위해 0.5를 넣었다.
 */

var progress = [false, false, false, false, false, false, false];


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

//document.getElementById("selectedInstructor").addEventListener("click", function(){} 과 같음

////TODO 중복구분이 많아서 리팩토링 필요함 - 재열
$('#selectedInstructor').on('click', function () {
    var target = document.getElementById("selectedInstructor");
    if (target.selectedIndex == 0) {
        document.getElementById("status2").style.visibility = 'hidden'
        document.getElementById("status1").style.visibility = 'visible'
        document.getElementById("status4").style.visibility = 'hidden'
        document.getElementById("status3").style.visibility = 'visible'
        progress[0] = false;
        progress[1] = false;
    } else {
        document.getElementById("status1").style.visibility = 'hidden'
        document.getElementById("status2").style.visibility = 'visible'
        document.getElementById("status3").style.visibility = 'hidden'
        document.getElementById("status4").style.visibility = 'visible'
        progress[0] = true;
        progress[1] = true;
    }
    progressF()
});

$('#selectedWorker').on('click', function () {
    var target = document.getElementById("selectedWorker");
    if (target.selectedIndex == 0) {
        document.getElementById("status6").style.visibility = 'hidden'
        document.getElementById("status5").style.visibility = 'visible'
        progress[2] = false;
    } else {
        document.getElementById("status5").style.visibility = 'hidden'
        document.getElementById("status6").style.visibility = 'visible'
        progress[2] = true;
    }
    progressF()
});

$('#phone').change(function () {
    statusChangeforTextBox(document.getElementById("phone").value, 5);
    progressF()
})


$('#subject').change(function () {
    statusChangeforTextBox(document.getElementById("subject").value, 7);
    progressF()
})

$('#date').change(function () {
    statusChangeforTextBox(document.getElementById("date").value, 9);
    progressF();
})

$('#account').change(function () {
    statusChangeforTextBox(document.getElementById("account").value, 11);
    progressF()
})

$('#file').change(function () {
    statusChangeforTextBox(document.getElementById("file").value, 13);
    progressF()
})


//TextBox에 글씨가 쓰였으면 '완료' 아니면 '진행중'
function statusChangeforTextBox(isFill, idIndex) {
    if (isFill != '') {
        document.getElementById("status" + idIndex).style.visibility = 'hidden'
        document.getElementById("status" + (idIndex + 1)).style.visibility = 'visible'
        progress[(idIndex / 2 - 0.5)] = true; //
    } else {
        document.getElementById("status" + (idIndex + 1)).style.visibility = 'hidden'
        document.getElementById("status" + idIndex).style.visibility = 'visible'
        progress[(idIndex / 2 - 0.5)] = false;
    }
}

//현재 진행률을 볼 수 있게 하는 함수.
function progressF() {
    var progressValue=0;
    for(i=0;i<progress.length;i++){
        if(progress[i]==true){progressValue++}
    }
    var lastProgressValue = progressValue * 15;
    if(lastProgressValue>=100){lastProgressValue=100;} // 100보다 커지면 100으로 고정함. ex) 세부항목이 7개 * 15 = 105 로 나오기에.. 100으로 고정

    document.getElementById("progress-bar").setAttribute("style", "width:"+lastProgressValue+"%");
    document.getElementById("progress-bar").innerHTML = lastProgressValue.toString()+"%";
}