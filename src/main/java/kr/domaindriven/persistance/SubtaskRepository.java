package kr.domaindriven.persistance;

import kr.domaindriven.model.Subtask;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Created by jerry on 2016-06-06.
 */
public interface SubtaskRepository extends MongoRepository<Subtask, String> {

    @Query(value = "{ 'task.seminarId' : ?0 }&&'task.taskName' : ?1 }")
    Subtask castingInstructor_findBySeminarIDWithTaskName(String seminarId, String taskName);
}
