package kr.domaindriven.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donghoon on 2016. 5. 19..
 * Modified by donghoon on 2016. 5. 26..
 * <p/>
 * 이 클래스는 세미나를 위해 만들었으며, 각 세미나는 하나 이상의 task 를 가지고 있음.
 * MongoDB seminars collection 에 저장.
 */
@Document(collection = "seminars")
public class Seminar {

    /**
     * mongodb objectId 이용시 unique key 를 자동생성 값으로 사용할 것으로 예상되므로 setter 를 삭제함.
     */
    @Id
    private String id;
    private String title;
    private boolean isCompleted = false;
    private List<Task> tasks;
    private String date;

    public Seminar() {

        Task selectInstructor = new Task(LacsCnst.CAST_INSTRUCTOR, LacsCnst.CAST_INSTRUCTOR_URL);
        Task reservePlace = new Task(LacsCnst.RESERVE_PLACE, LacsCnst.RESERVE_PLACE_URL);
        Task makePoster = new Task(LacsCnst.MAKE_POSTER, LacsCnst.MAKE_POSTER_URL);
        Task registerSeminar = new Task(LacsCnst.REGISTER_SEMINAR, LacsCnst.REGISTER_SEMINAR_URL);
        Task promostion = new Task(LacsCnst.PROMOTION, LacsCnst.PROMOTION_URL);
        Task rememberance = new Task(LacsCnst.REMEMBERANCE, LacsCnst.REMEMBERANCE_URL);

        /**
         * 고정적 으로 예상되는 task 들을 Seminar 생성시 할당한다.
         */
        List<Task> taskList = new ArrayList<Task>();
        taskList.add(selectInstructor);
        taskList.add(reservePlace);
        taskList.add(makePoster);
        taskList.add(registerSeminar);
        taskList.add(promostion);
        taskList.add(rememberance);
        this.tasks = taskList;
    }

    /**
     * 현재 진행중인 세미나가 없을 경우의 생성자.
     *
     * @param placeHolder
     */
    public Seminar(String placeHolder) {
        this.title = placeHolder;
    }

    /**
     * 기본 태스크를 세미나 객체가 생성될 때 만들어 준다.
     * 기본 테스크 : 강사 섭외, 장소예약, 포스터 제작, 홍보, 회고.
     *
     * @param title
     * @param date
     */
    public Seminar(String title, String date) {
        this();
        this.title = title;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 자동 인식을 위해 isCompleted --> getIsCompleted 로 이름 변경.
     *
     * @return
     */
    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
