package kr.domaindriven.persistance;

import kr.domaindriven.model.Seminar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Created by donghoon on 2016. 5. 26..
 * <p/>
 * MongoRepository 받음으로 인해 기본 crud 작업을 하기위한 인터페이스가 구현되어 있다.
 */
public interface SeminarRepository extends MongoRepository<Seminar, String>{

    /**
     * The placeholder ?0 lets you substitute the value from the method arguments into the JSON query string.
     *
     * @param title
     * @return
     */
    @Query(value = "{ 'title' : ?0 }")
    Seminar findByTitle(String title);

    @Query(value = "{ 'isCompleted' : ?0 }")
    Seminar findByIsCompleted(boolean isCompleted);

    //db.seminars.update({"title":"Go 프로그래밍 소개."},{$set: {"tasks.0.test":"kkkk"}});
  /*  @Query(value = "update({\"title\":\"Go 프로그래밍 소개.\"},{$set: {\"tasks.0.test\":\"kkkk\"})")
    Seminar insertTasksElements();*/
}
