package kr.domaindriven.web;

import kr.domaindriven.model.Instructor;
import kr.domaindriven.model.form.InstructorForm;
import kr.domaindriven.service.InstructorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by donghoon on 2016. 7. 24..
 * Instructor 에 대해서 Rest로 CRUD 작업을 할 수 있는 Controller 입니다.
 */

@RestController
@RequestMapping(value = "/instructor")
public class InstructorRestCntrl {
    private Logger logger = LoggerFactory.getLogger(InstructorRestCntrl.class);

    @Autowired
    private InstructorService service;

    @ModelAttribute
    public InstructorForm setUpForm() {
        return new InstructorForm();
    }

    /**
     * 강사후보 추가 기능 구현.
     *
     * @param form
     * @param result
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Instructor save(@Validated InstructorForm form, BindingResult result) {
        logger.info("강사후보 생성.");
        if (result.hasErrors()) {
            logger.error("강사후보 생성 오류.");
            List<ObjectError> errorList = result.getAllErrors();
            for (ObjectError error : errorList) {
                logger.error(error.toString());
            }
            return null;
        }

        Instructor newInstructor = new Instructor(form.getName(), form.getPhoneNumber(), form.getMail());

        return service.save(newInstructor);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Instructor editWorker(@RequestParam String id, @RequestParam String name, @RequestParam String phone, @RequestParam String email) {
        logger.info("강사후보 편집.");
        logger.info("id: {}, name: {}, phone: {}, email: {}", id, name, phone, email);

        Instructor editInstructor = service.findOne(id);
        editInstructor.setName(name);
        editInstructor.setPhoneNumber(phone);
        editInstructor.setMail(email);

        return service.save(editInstructor);

    }
}
