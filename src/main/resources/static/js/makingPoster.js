$(window).load(function () {
       
    if($(".progress-bar").text() == 0){
        restart();
    }else if($(".progress-bar").text() == 100){
        complete();
    }
    
    /**
     * 포스터 작업의 버튼
     */

    $("#PosterComplete").on("click", function () {
        complete();
    });

    $("#PosterRestart").on("click", function () {
        restart();
    });

    /**
     * 완료시
     * 버튼 Restart -> Complete
     * 진행바 100%로
     */

    function complete() {
        $("#PosterComplete").css("display", "none");
        $("#PosterRestart").css("display", "block");
        $(".progress-bar").css("width", "100%");
        $(".progress-bar").text("100%");
    }
    /**
     * 취소시
     * 버튼 Complete -> Restart
     * 진행바 0%로
     */

    function restart() {
        $("#PosterRestart").css("display", "none");
        $("#PosterComplete").css("display", "block");
        $(".progress-bar").css("width", "0%");
        $(".progress-bar").text("0%");
    }


});

