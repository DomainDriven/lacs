package kr.domaindriven.web;

import helper.ControllerIntegrationTestHelper;
import kr.domaindriven.service.InstructorService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by donghoon on 2016. 7. 31..
 */
public class InstructorRestCntrlIntegrationTest extends ControllerIntegrationTestHelper {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Autowired
    private InstructorService instructorService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void 강사정보_등록() throws Exception {

        //given
        String json = "{\"name\":\"Donghoon Lee\",\"phoneNumber\":\"010-2057-5488\",\"mail\":\"imfly7@naver.com\"}";

        //when
        mvc.perform(post("/instructor").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
