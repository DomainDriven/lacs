package kr.domaindriven.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Map;

/**
 * Created by donghoon on 2016. 5. 26..
 * <p/>
 * MongoDB 설정 클래스.
 */
@Configuration
@EnableMongoRepositories("kr.domaindriven.persistance")
public class MongoConfig {

    @Autowired
    private LacsProperties lacsProperties;

    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(getMongoInfo("host"), Integer.parseInt(getMongoInfo("port")));
    }

    /**
     * DB 작업을 위한 메인 객체인 MongoTemplate 을 bean 으로 등록.
     *
     * @return
     * @throws Exception
     */
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongo(), getMongoInfo("database"));
        //set mongo db writeConcern option "safe" to receive server acknowledge.
        mongoTemplate.setWriteConcern(WriteConcern.SAFE);
        return mongoTemplate;
    }

    private String getMongoInfo(String key) {
        Map<String, Map<String, String>> servers = lacsProperties.getServers();
        Map<String, String> mongoInfo = servers.get("mongo");
        return mongoInfo.get(key);
    }
}
