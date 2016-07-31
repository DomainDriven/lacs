package helper;

import kr.domaindriven.ApplicationTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by donghoon on 2016. 7. 31..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ApplicationTest.class)
@WebAppConfiguration
@ActiveProfiles("Test")
public class ControllerIntegrationTestHelper {
}
