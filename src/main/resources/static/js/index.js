$(document).ready(function () {

    // $('input[name="daterange"]').daterangepicker();
    $(".instructor-multiple").select2();

    /**
     * Input Mask 함수.
     */

    var inputMaskList = [
        "#workerPhone", "#instructorPhone"
    ];

    inputMaskList.forEach(function (selector) {
        lacs.makeInputMask(selector);
    });

    /**
     * DatePicker 함수.
     */

    var datePickerList = [
        "#seminarDate", "#seminarEditDate", "#editSeminarDate", "#date"
    ];

    datePickerList.forEach(function (selector) {
        lacs.makeDatePicker(selector);
    });

    /**
     * DataTable 함수.
     */
    var dt_option1 = {
        "scrollCollapse": false,
        "paging": true
    };


    var dataTableList = [

        {
            "selector": "#seminars",
            "option": dt_option1
        },
        {
            "selector": "#workers",
            "option": dt_option1
        },
        {
            "selector": "#instructors",
            "option": dt_option1
        }

    ];

    dataTableList.forEach(function (t) {
        lacs.makeDataTable(t);
    });

    /**
     * 세미나 완료 버튼.
     */

    $("#completeSeminar").on("click", function () {

        var currentSeminarId = $("input[type=hidden]#currentSeminarId").val();

        lacs.ajaxWrapper(
            "/seminar/isCompleted",
            {
                id: currentSeminarId,
                isCompleted: true
            },
            function (response) {
                console.log(response);
                location.replace("/allSeminar");
            },
            function (error) {
                console.log("세미나 완료상태 편집 실패.");
                console.log(error);
            });

    });

    /**
     * 세미나 재시작 버튼.
     */
    $("#restartSeminar").on("click", function () {

        var currentSeminarId = $("input[type=hidden]#currentSeminarId").val();

        lacs.ajaxWrapper(
            "/seminar/isCompleted",
            {
                id: currentSeminarId,
                isCompleted: false
            },
            function (response) {
                console.log(response);
                location.replace("/allSeminar");
            },
            function (error) {
                console.log("세미나 완료상태 편집 실패.");
                console.log(error);
            });

    });

    /**
     * 강사후보 편집 버튼.
     */

    $("#editInstructorBtn").on("click", function () {

        var editInstructorId = $("#editInstructorId").val();
        var editInstructorName = $("#editInstructorName").val();
        var editInstructorPhone = $("#editInstructorPhone").val();
        var editInstructorEmail = $("#editInstructorEmail").val();

        lacs.ajaxWrapper(
            "/instructor/edit",
            {
                id: editInstructorId,
                name: editInstructorName,
                phone: editInstructorPhone,
                email: editInstructorEmail
            },
            function (response) {
                console.log(response);
                location.replace("/allInstructor");
            },
            function (error) {
                console.log("강사후보 편집 실패.");
                console.log(error);
            });

    });

    /**
     * 세미나 편집 버튼.
     */

    $("#editSeminarBtn").on("click", function () {

        var editSeminarId = $("#currentSeminarId").val();
        var editSeminarTitle = $("#editSeminarTitle").val();
        var editSeminarDate = $("#editSeminarDate").val();

        lacs.ajaxWrapper(
            "/seminar/editSeminar",
            {
                id: editSeminarId,
                title: editSeminarTitle,
                date: editSeminarDate
            },
            function (response) {
                console.log(response);
                location.replace("/allSeminar");
            },
            function (error) {
                console.log("세미나 편집 실패.");
                console.log(error);
            });

    });

    /**
     * 운영진 편집 버튼.
     */

    $("#editWorkerBtn").on("click", function () {

        var editWorkerId = $("#editWorkerId").val();
        var editWorkerName = $("#editWorkerName").val();
        var editWorkerPhone = $("#editWorkerPhone").val();
        var editWorkerEmail = $("#editWorkerEmail").val();

        lacs.ajaxWrapper(
            "/worker/edit",
            {
                id: editWorkerId,
                name: editWorkerName,
                phone: editWorkerPhone,
                email: editWorkerEmail
            },
            function (response) {
                console.log(response);
                location.replace("/allWorker");
            },
            function (error) {
                console.log("운영진 편집 실패.");
                console.log(error);
            });
    });

    /**
     * 당월 세미나 태스크의 운영진 할당 버튼.
     */

    var workerSelectBtnList = $(".workerSelectBtn");
    var workerList = $(".workerList");

    workerSelectBtnList.on("click", function () {
        var index = workerSelectBtnList.index(this);
        var workerName = workerList[index].options[workerList[index].selectedIndex].text;
        var currentSeminarId = $("#currentSeminarId").val();
        console.log("Index: " + index + ", WorkerName: " + workerName + ", CurrentSeminarId: " + currentSeminarId);

        lacs.ajaxWrapper(
            "/seminar/assignWorker",
            {
                id: currentSeminarId,
                workerName: workerName,
                index: index
            },
            function (response) {
                console.log(response);
                alert(response.tasks[index].taskName + "에 운영진 "
                    + response.tasks[index].workers[0].name + "이 할당 되었습니다.");
            },
            function (error) {
                console.log("운영진 작업 할당 실패.");
                console.log(error);
            });
    });

    /**
     * 당월 세미나의 예정일, 주제 변경, 청중수 변경 취소 버튼
     */

    var editCancelBtnList = [
        {
            "selector": "#cancelTitleBtn",
            "display": "#seminarTitleText",
            "form": "#seminarEditTitleForm"
        },
        {
            "selector": "#cancelDateBtn",
            "display": "#seminarDateInfoBox",
            "form": "#seminarEditDateForm"
        },
        {
            "selector": "#cancelAudienceBtn",
            "display": "#seminarAudienceInfoBox",
            "form": "#seminarEditAudienceForm"
        }
    ];

    editCancelBtnList.forEach(function (obj) {
        $(obj.selector).on("click", function () {
            $(obj.display).css("display", "block");
            $(obj.form).css("display", "none");
        });
    });

    /**
     * 당월 세미나의 주제 변경 전송 버튼
     */

    $("#seminarTitleText").on("click", function () {

        $("#seminarTitleText").css("display", "none");
        $("#seminarEditTitleForm").css("display", "block");

    });

    $("#seminarEditTitleBtn").click(function () {

        var currentSeminarId = $("input[type=hidden]#currentSeminarId").val();
        var editTitle = $("#seminarEditTitle").val();

        lacs.ajaxWrapper(
            "/seminar/editTitle",
            {
                id: currentSeminarId,
                title: editTitle
            },
            function (response) {
                console.log(response);
                location.replace("/");
            },
            function (error) {
                console.log("타이틀 편집 실패.");
                console.log(error);
                $("#seminarTitleInfoBox").css("display", "block");
                $("#seminarEditTitleForm").css("display", "none");
            });

    });

    /**
     * 당월 세미나의 예정일 변경 전송 버튼
     */

    $("#seminarDateIcon").on("click", function () {

        $("#seminarDateInfoBox").css("display", "none");
        $("#seminarEditDateForm").css("display", "block");

    });

    $("#seminarEditDateBtn").click(function () {

        var currentSeminarId = $("input[type=hidden]#currentSeminarId").val();
        var editDate = $("#seminarEditDate").val();

        lacs.ajaxWrapper(
            "/seminar/editDate",
            {
                id: currentSeminarId,
                date: editDate
            },
            function (response) {
                console.log(response);
                location.replace("/");
            },
            function (error) {
                console.log("예정일 편집 실패.");
                console.log(error);
                $("#seminarDateInfoBox").css("display", "block");
                $("#seminarEditDateForm").css("display", "none");
            });

    });

    /**
     * 당월 세미나 청중수 입력 버튼
     */
    $("#seminarAudienceIcon").on("click", function () {

        $("#seminarAudienceInfoBox").css("display", "none");
        $("#seminarEditAudienceForm").css("display", "block");

    });

    $("#seminarEditAudienceBtn").click(function () {

        var currentSeminarId = $("input[type=hidden]#currentSeminarId").val();
        var audienceCount = $("#seminarEditAudience").val();

        lacs.ajaxWrapper(
            "/seminar/editAudience",
            {
                id: currentSeminarId,
                audienceCount: audienceCount
            },
            function (response) {
                console.log(response.audience);
                location.replace("/");
            },
            function (error) {
                console.log("청중수 편집 실패.");
                console.log(error);
                $("#seminarAudienceInfoBox").css("display", "block");
                $("#seminarEditAudienceForm").css("display", "none");
            });
    });

    /**
     * 강사후보 추가 폼 전송 버튼
     */

    $("#addInstructor").on("click", function () {
        var instructorName = $("#instructorName").val();
        var instructorPhone = $("#instructorPhone").val();
        var instructorEmail = $("#instructorEmail").val();
        var checkSubmit = confirm("이름: " + instructorName + ", 전화번호: " + instructorPhone + ", 이메일 " + instructorEmail + "등록 하시겠습니까?");

        if (checkSubmit) {
            lacs.ajaxWrapper(
                "/instructor",
                {
                    name: instructorName,
                    phoneNumber: instructorPhone,
                    mail: instructorEmail
                },
                function (response) {
                    alert(response.name + "님, 저장에 성공하였습니다.");
                    $("#instructorName").val("");
                    $("#instructorPhone").val("");
                    $("#instructorEmail").val("");
                    $("#instructorName").attr("placeholder", "이름을 입력해 주세요.");
                    $("#instructorPhone").attr("placeholder", "전화번호를 입력해 주세요.");
                    $("#instructorEmail").attr("placeholder", "이메일을 입력해 주세요.");
                },
                function (error) {
                    console.log("강사 저장 실패.");
                    console.log(error);
                    if (error.responseText === "") {
                        $("#instructorName").attr("placeholder", "이름은 비어 있으면 안됩니다.");
                        $("#instructorPhone").attr("placeholder", "전화번호는 비어 있으면 안됩니다.");
                        $("#instructorEmail").attr("placeholder", "이메일은 비어 있으면 안됩니다.");
                    }
                });
        }
    });

    /**
     * 세미나 추가 폼 전송 버튼
     */

    $("#addSeminar").on("click", function () {
        var seminarTitle = $("#seminarTitle").val();
        var seminarDate = $("#seminarDate").val();
        var checkSubmit = confirm("세미나 타이틀: " + seminarTitle + ", 세미나 날짜: " + seminarDate + "등록 하시겠습니까?");

        if (checkSubmit) {
            lacs.ajaxWrapper(
                "/seminar",
                {
                    title: seminarTitle,
                    date: seminarDate
                },
                function (response) {
                    alert(response.title + ", " + response.date + " 저장에 성공하였습니다.");
                    $("#seminarTitle").val("");
                    $("#seminarDate").val("");
                    $("#seminarTitle").attr("placeholder", "ex) 개발자 경력 개발 세미나.");
                    $("#seminarDate").attr("placeholder", "ex) 05/29/2016");
                },
                function (error) {
                    console.log("세미나 저장 실패.");
                    console.log(error);
                    if (error.responseText === "") {
                        $("#seminarTitle").attr("placeholder", "세미나 타이틀은 비어 있으면 안됩니다.");
                        $("#seminarDate").attr("placeholder", "세미나 예정일은 비어 있으면 안됩니다.");
                    }
                });
        }
    });

    /**
     * 운영진 추가 폼 전송 버튼
     */

    $("#addWorker").on("click", function () {
        var workerName = $("#workerName").val();
        var workerPhone = $("#workerPhone").val();
        var workerEmail = $("#workerEmail").val();
        var checkSubmit = confirm("이름: " + workerName + ", 전화번호: " + workerPhone + ", 이메일 " + workerEmail + "등록 하시겠습니까?");

        if (checkSubmit) {
            lacs.ajaxWrapper(
                "/worker",
                {
                    name: workerName,
                    phone: workerPhone,
                    email: workerEmail
                },
                function (response) {
                    alert(response.name + "님, 저장에 성공하였습니다.");
                    $("#workerName").val("");
                    $("#workerPhone").val("");
                    $("#workerEmail").val("");
                    $("#workerName").attr("placeholder", "이름을 입력해 주세요.");
                    $("#workerPhone").attr("placeholder", "전화번호를 입력해 주세요.");
                    $("#workerEmail").attr("placeholder", "이메일을 입력해 주세요.");
                },
                function (error) {
                    console.log("운영진 저장 실패.");
                    console.log(error);
                    if (error.responseText === "") {
                        $("#workerName").attr("placeholder", "이름은 비어 있으면 안됩니다.");
                        $("#workerPhone").attr("placeholder", "전화번호는 비어 있으면 안됩니다.");
                        $("#workerEmail").attr("placeholder", "이메일은 비어 있으면 안됩니다.");
                    }
                });
        }
    });

    /**
     * 장소예약 상세화면.
     * @type {*|jQuery|HTMLElement}
     */
    var couponComplete = $("#couponComplete");
    var couponRestart = $("#couponRestart");
    var tozComplete = $("#tozComplete");
    var tozRestart = $("#tozRestart");
    var rpTaskProgress = $("#rpTaskProgress").val();

    lacs.progressToggleBtn(couponComplete, couponRestart, tozComplete, 50, 0, false);
    lacs.progressToggleBtn(tozComplete, tozRestart, couponRestart, 100, 50, true);

    /**
     * 장소 예약 progress 값에 따라 button 상태 변경.
     */
    if (rpTaskProgress === '100') {
        couponComplete.css("display", "none");
        couponRestart.css("display", "block").attr("disabled", true);
        tozComplete.css("display", "none");
        tozRestart.css("display", "block");
    } else if (rpTaskProgress === '50') {
        couponComplete.css("display", "none");
        couponRestart.css("display", "block");
        tozComplete.css("display", "block").attr("disabled", false);
        tozRestart.css("display", "none");
    } else if (rpTaskProgress === '0') {
        couponComplete.css("display", "block");
        couponRestart.css("display", "none");
        tozComplete.css("display", "block").attr("disabled", true);
        tozRestart.css("display", "none");
    }

    /**
     * 온오프 믹스 등록 상세화면.
     * @type {jQuery|HTMLElement}
     */
    var onOffMixComplete = $("#onOffMixComplete");
    var onOffMixRestart = $("#onOffMixRestart");
    var dummy = $("#dummy");
    var roTaskProgress = $("#roTaskProgress").val();

    lacs.progressToggleBtn(onOffMixComplete, onOffMixRestart, dummy, 100, 0, true);

    /**
     * 온오프 믹스 등록 progress 값에 따라 button 상태 변경.
     */
    if (roTaskProgress === '100') {
        onOffMixComplete.css("display", "none");
        onOffMixRestart.css("display", "block");
    } else if (roTaskProgress === '0') {
        onOffMixComplete.css("display", "block");
        onOffMixRestart.css("display", "none");
    }

    /**
     * 홍보 상세화면.
     * @type {jQuery|HTMLElement}
     */
    var promotingComplete = $("#promotingComplete");
    var promotingRestart = $("#promotingRestart");
    var prTaskProgress = $("#prTaskProgress").val();

    lacs.progressToggleBtn(promotingComplete, promotingRestart, dummy, 100, 0, true);

    /**
     * 홍보 progress 값에 따라 button 상태 변경.
     */
    if (prTaskProgress === '100') {
        promotingComplete.css("display", "none");
        promotingRestart.css("display", "block");
    } else if (prTaskProgress === '0') {
        promotingComplete.css("display", "block");
        promotingRestart.css("display", "none");
    }

    /**
     * 회고 상세화면.
     * @type {jQuery|HTMLElement}
     */
    var retrospectingComplete = $("#retrospectingComplete");
    var retrospectingRestart = $("#retrospectingRestart");
    var rtTaskProgress = $("#rtTaskProgress").val();

    lacs.progressToggleBtn(retrospectingComplete, retrospectingRestart, dummy, 100, 0, true);

    /**
     * 회고 progress 값에 따라 button 상태 변경.
     */
    if (rtTaskProgress === '100') {
        retrospectingComplete.css("display", "none");
        retrospectingRestart.css("display", "block");
    } else if (rtTaskProgress === '0') {
        retrospectingComplete.css("display", "block");
        retrospectingRestart.css("display", "none");
    }



});
