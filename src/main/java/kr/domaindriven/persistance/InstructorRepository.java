package kr.domaindriven.persistance;

import kr.domaindriven.model.Instructor;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jerry on 2016-05-30.
 */
public interface InstructorRepository extends MongoRepository<Instructor, String> {
    Instructor findByName(String name);
}
