package kr.domaindriven.web;

import kr.domaindriven.model.Worker;
import kr.domaindriven.model.form.WorkerForm;
import kr.domaindriven.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by donghoon on 2016. 5. 31..
 * Worker 에 대해서 Rest로 CRUD 작업을 할 수 있는 Controller 입니다.
 */

@RestController
@RequestMapping(value = "/worker")
public class WorkerRestCntrl {
    private Logger logger = LoggerFactory.getLogger(WorkerRestCntrl.class);

    @Autowired
    private WorkerService service;

    @ModelAttribute
    public WorkerForm setUpForm() {
        return new WorkerForm();
    }

    /**
     * 운영진 추가 기능 구현.
     *
     * @param form
     * @param result
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Worker save(@Validated WorkerForm form, BindingResult result) {
        logger.info("운영진 생성.");
        if (result.hasErrors()) {
            logger.error("운영진 생성 오류.");
            List<ObjectError> errorList = result.getAllErrors();
            for (ObjectError error : errorList) {
                logger.error(error.toString());
            }
            return null;
        }

        Worker newWorker = new Worker(form.getName(), form.getPhone(), form.getEmail());

        return service.save(newWorker);
    }
}
