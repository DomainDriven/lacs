package helper;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by donghoon on 2016. 7. 31..
 */
@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories("kr.domaindriven.persistance")
public class MongoTestConfig {
}
