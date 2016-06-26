package kr.domaindriven.model;

/**
 * Created by jerry on 2016-06-01.
 */
public enum LacsCnstE {

    CAST_INSTRUCTOR("강사 섭외", "/castingInstructor"), RESERVE_PLACE("장소 예약", "/reservingPlace"), MAKE_POSTER("포스터 제작", "/makingPoster"),
    REGISTER_SEMINAR("세미나 등록", "/registeringOnOffMix"), PROMOTION("홍보", "/promoting"), RETROSPECTION("회고", "/retrospecting");

    private String taskName;
    private String URL;

    LacsCnstE(String taskName, String URL) {
        this.taskName = taskName;
        this.URL = URL;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getURL() {
        return URL;
    }

}
