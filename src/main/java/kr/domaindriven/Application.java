package kr.domaindriven;

import kr.domaindriven.config.LacsProperties;
import kr.domaindriven.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by jerry on 2016-05-15.
 */
@SpringBootApplication
@EnableWebMvc
public class Application extends WebMvcConfigurerAdapter implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private LacsProperties props;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private FileService fileService;

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * static folder 이하에 있는 정적자원들 인식.
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/static/**")) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
    }

    /**
     * CommandLineRunner 인터페이스 메소드.
     * main 함수 bootstraping 시 실행되는 메소드.
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        Map<String, Map<String, String>> servers = props.getServers();
        Map<String, String> mongoInfo = servers.get("mongo");
        String host = mongoInfo.get("host");
        int port = Integer.parseInt(mongoInfo.get("port"));
        String database = mongoInfo.get("database");

        logger.debug("Mongo Host: {}", host);
        logger.debug("Mongo Port: {}", port);
        logger.debug("Mongo Database: {}", database);


        if (props.getEnv().equals("dev")) {
            logger.debug("개발 환경.");
            /**
             * 어플리케이션 구동시에 resources/data 폴더의 json file data 를 삽입한다.
             */
            List<String> collectionList = Arrays.asList("seminars", "workers", "instructors");

            for (String collectionName : collectionList) {
                mongoTemplate.dropCollection(collectionName);
                fileService.insertJsonFile("classpath:data/" + collectionName + ".json", collectionName);
            }
        } else if (props.getEnv().equals("prod")) {
            logger.debug("운영 환경.");
        }
    }
}
