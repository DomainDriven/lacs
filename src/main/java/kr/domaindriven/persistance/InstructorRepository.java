package kr.domaindriven.persistance;

import kr.domaindriven.model.Instructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Created by jerry on 2016-05-30.
 */
public interface InstructorRepository extends MongoRepository<Instructor, String> {
    @Query(value = "{ 'name' : ?0 }")
    Instructor findByName(String name);
}

