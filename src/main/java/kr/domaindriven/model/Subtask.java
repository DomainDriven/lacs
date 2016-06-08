package kr.domaindriven.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jerry on 2016-06-06.
 * 이 클래스는 세미나의 하위 클래스들을 한곳에 모아서 데이터베이스에서 관리를 하기위해 만들었습니다.
 * DTO 입니다.
 */
@Document(collection = "subtasks")
public class Subtask<T extends Task> implements ISubtask {
    @Id
    private String id;
    private T task;

    @Override
    public T getTask() {
        return task;
    }

    public void setTask(T task) {
        this.task = task;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
