package kr.domaindriven.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by donghoon on 2016. 5. 19..
 * 이 클래스는 작업별 진행자를 나타냄.
 */
@Document(collection = "workers")
public class Worker {

    /**
     * mongodb objectId 이용시 unique key 를 자동생성 값으로 사용할 것으로 예상되므로 setter 를 삭제함.
     */
    @Id
    private String id;
    private String name;

    public Worker(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
