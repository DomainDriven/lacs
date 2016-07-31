package kr.domaindriven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by donghoon on 2016. 7. 31..
 */
@SpringBootApplication
@EnableConfigurationProperties
@ActiveProfiles("test")
public class ApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }

}
