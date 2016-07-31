package kr.domaindriven.persistance;

import helper.MongoRepositoryIntegrationTestHelper;
import kr.domaindriven.model.Instructor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by donghoon on 2016. 7. 31..
 */
public class InstructorRepositoryIntegrationTest extends MongoRepositoryIntegrationTestHelper {

    @Autowired
    private InstructorRepository instructorRepository;

    /**
     * @Test 단위별 실행마다 셋업 해주는 메소드.
     */
    @Before
    public void setup() {
    }


    /**
     * 강사정보를 담은 인스턴스 1개를 생성후 저장 ->
     * 생성된 인스턴스 정보를 이용해 조회 한 데이터와 처음 생성한 인스턴스 정보가 같은지 비교하여 제대로 저장되 었는지 판단.
     */
    @Test
    public void 강사정보_저장_테스트() {

        //given
        Instructor instructor = new Instructor("이동훈", "010-2057-5480", "devarchi33@gmail.com");

        //when
        instructorRepository.save(instructor);

        //then
        assertThat(instructorRepository.findByName("이동훈").getMail(), equalTo("devarchi33@gmail.com"));
    }
}
