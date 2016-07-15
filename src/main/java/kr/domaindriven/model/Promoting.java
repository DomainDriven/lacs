package kr.domaindriven.model;

/**
 * Created by jerry on 2016-07-15.
 * 이 클래스는 홍보와 관련된 자원들 (예: URL주소, 홍보자)를 위한 클래스입니다.
 * Promoting Class를 이용하여 자원을 추가하고 삭제하는등 CRUD 기능을 위한 모델입니다.
 */
public class Promoting {
    private String locationName;
    private String locationURL;
    private String worker;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationURL() {
        return locationURL;
    }

    public void setLocationURL(String locationURL) {
        this.locationURL = locationURL;
    }

    // TODO: 2016-07-15 String에서 Worker로 변경하기 - 재열 
    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }
}
