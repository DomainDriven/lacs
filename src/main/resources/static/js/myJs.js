/**
 * Created by jerry on 2016-05-18.
 */

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
        document.getElementById("status2").style.visibility='hidden'
        document.getElementById("status1").style.visibility='visible'
        document.getElementById("status4").style.visibility='hidden'
        document.getElementById("status3").style.visibility='visible'
    } else {
        document.getElementById("status1").style.visibility='hidden'
        document.getElementById("status2").style.visibility='visible'
        document.getElementById("status3").style.visibility='hidden'
        document.getElementById("status4").style.visibility='visible'
    }});


/*
$('#phone').on('click', function (){
   // statusChangeforTextBox(document.getElementById("phone").valueOf(),3);
}
*/

//TextBox에 글씨가 쓰였으면 '완료' 아니면 '진행중'
function statusChangeforTextBox(isFill,idIndex){

    if (isFill == null) {
        document.getElementById("status" + idIndex+1).style.visibility='hidden'
        document.getElementById("status"+ idIndex).style.visibility='visible'
    } else {
        document.getElementById("status"+ idIndex).style.visibility='hidden'
        document.getElementById("status"+ idIndex+1).style.visibility='visible'
    }
}