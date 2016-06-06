package kr.domaindriven.persistance;

import kr.domaindriven.model.Subtask;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jerry on 2016-06-06.
 */
public interface SubtaskRepository extends MongoRepository<Subtask, String> {
}
