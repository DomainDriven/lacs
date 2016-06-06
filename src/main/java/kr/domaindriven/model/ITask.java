package kr.domaindriven.model;

import java.util.List;

/**
 * Created by jerry on 2016-06-06.
 * 이 인터페이스는 작업(TASK)을 위한 것이다.
 * 구현체는 작업명, 작업자, 작업진행률, 완료여부, URL을 알려주어야 한다.
 */
public interface ITask {
    /**
     * 연관된 세미나의 아이디를 제공한다.
     * @return String
     */
    String getSeminarId();

    /**
     * 연관된 세미나의 아이디를 수정한다.
     * @return String
     */
    void setSeminarId(String seminarId);

    String getTaskName();

    void setTaskName(String taskName);

    List<Worker> getWorkers();

    void setWorkers(List<Worker> workers);

    int getProgress();

    void setProgress(int progress);

    boolean isCompleted();

    void setCompleted(boolean completed);

    String getRequestUrl();

    void setRequestUrl(String requestUrl);
}
