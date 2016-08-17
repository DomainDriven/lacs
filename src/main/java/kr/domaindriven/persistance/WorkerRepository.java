package kr.domaindriven.persistance;

import kr.domaindriven.model.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Created by donghoon on 2016-05-31.
 */
public interface WorkerRepository extends MongoRepository<Worker, String> {

    @Query(value = "{ 'name' : ?0 }")
    Worker findByName(String name);

    @Query(value = "{ 'accessCode' : ?0 }")
    Worker findByAccessCode(String accessCode);

}
