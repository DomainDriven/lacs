package kr.domaindriven.model;

import org.springframework.cglib.core.KeyFactory;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by donghoon on 2016. 5. 19..
 * 이 클래스는 각 작업을 나타내기 위해 만들었음.
 * 각 작업은 하나 이상의 Worker를 포함한다.
 */
public class Task implements ITask {

    /**
     * 연관된 세미나의 아이디
     */
    private String seminarId;

    //// TODO: 2016-06-06 아래 id필드?? 무슨말씀이신지 모르겠어요 - 재열
    /**
     * seminars document 에 list 형태로 저장되므로 id 필드는 향후 삭제가 필요해 보임.
     */
    private String taskName;
    private List<Worker> workers;

    /**
     * 테스크가 처음 생성된 시점에서 progress 는 0 이므로 default 0 으로 할당함.
     */
    private int progress = 0;
    /**
     * 테스크가 처음 생성된 시점에서 미완결 상태이므로 isCompleted default 를 false 로 할당함.
     */
    private boolean isCompleted = false;

    /**
     * th:each 반복자에서 link 를 걸기 위해서 사용한다.
     */
    private String requestUrl;


    public Task() {
    }

    /**
     * 테스크의 이름을 default 인자로 설정함.
     *
     * @param taskName
     */
    public Task(String taskName, String requestUrl) {
        this();
        this.taskName = taskName;
        this.requestUrl = requestUrl;
    }

    /**
     * @param taskName
     * @param workers
     */
    public Task(String taskName, List<Worker> workers) {
        this.taskName = taskName;
        this.workers = workers;
    }

    @Override
    public String getSeminarId() {
        return this.seminarId;
    }

    public void setSeminarId(String seminarId){
        this.seminarId = seminarId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
