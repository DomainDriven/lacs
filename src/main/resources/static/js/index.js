/**
 * Created by donghoon on 2016. 5. 27..
 */
$(document).ready(function () {
    $('input[name="daterange"]').daterangepicker();
    $("#seminarDate").datepicker();
    $("#seminars").DataTable();
    $(".instructor-multiple").select2();
    $("#workers").DataTable();
    $("#workerPhone").inputmask("999-9999-9999");

    $("#addSeminar").on("click", function () {
        var seminarTitle = $("#seminarTitle").val();
        var seminarDate = $("#seminarDate").val();
        var checkSubmit = confirm("세미나 타이틀: " + seminarTitle + ", 세미나 날짜: " + seminarDate + "등록 하시겠습니까?");

        if (checkSubmit) {
            $.ajax({
                type: "post",
                async: true,
                dataType: "json",
                url: "/seminar",
                data: {
                    title: seminarTitle,
                    date: seminarDate
                },
                success: function (response) {
                    alert(response.title + ", " + response.date + " 저장에 성공하였습니다.");
                    $("#seminarTitle").val("");
                    $("#seminarDate").val("");
                    $("#seminarTitle").attr("placeholder", "ex) 개발자 경력 개발 세미나.");
                    $("#seminarDate").attr("placeholder", "ex) 05/29/2016");
                },
                error: function (error) {
                    console.log("세미나 저장 실패.");
                    console.log(error);
                    if (error.responseText === "") {
                        $("#seminarTitle").attr("placeholder", "세미나 타이틀은 비어 있으면 안됩니다.");
                        $("#seminarDate").attr("placeholder", "세미나 예정일은 비어 있으면 안됩니다.");
                    }
                }
            })
        }
    });

    $("#addWorker").on("click", function () {
        var workerName = $("#workerName").val();
        var workerPhone = $("#workerPhone").val();
        var workerEmail = $("#workerEmail").val();
        var checkSubmit = confirm("이름: " + workerName + ", 전화번호: " + workerPhone + ", 이메일 " + workerEmail + "등록 하시겠습니까?");

        if (checkSubmit) {
            $.ajax({
                type: "post",
                async: true,
                dataType: "json",
                url: "/worker",
                data: {
                    name: workerName,
                    phone: workerPhone,
                    email: workerEmail
                },
                success: function (response) {
                    alert(response.name + "님, 저장에 성공하였습니다.");
                    $("#workerName").val("");
                    $("#workerPhone").val("");
                    $("#workerEmail").val("");
                    $("#workerName").attr("placeholder", "이름을 입력해 주세요.");
                    $("#workerPhone").attr("placeholder", "전화번호를 입력해 주세요.");
                    $("#workerEmail").attr("placeholder", "이메일을 입력해 주세요.");
                },
                error: function (error) {
                    console.log("강사 저장 실패.");
                    console.log(error);
                    if (error.responseText === "") {
                        $("#workerName").attr("placeholder", "이름은 비어 있으면 안됩니다.");
                        $("#workerPhone").attr("placeholder", "전화번호는 비어 있으면 안됩니다.");
                        $("#workerEmail").attr("placeholder", "이메일은 비어 있으면 안됩니다.");
                    }
                }
            })
        }
    });

    /**
     * 장소예약 상세화면.
     * @type {*|jQuery|HTMLElement}
     */
    toggleBtn("couponComplete", "couponRestart", "tozComplete", 50, 0, false);
    toggleBtn("tozComplete", "tozRestart", "couponRestart", 100, 50, true);

});
