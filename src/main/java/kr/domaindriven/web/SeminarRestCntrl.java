package kr.domaindriven.web;

import kr.domaindriven.model.Seminar;
import kr.domaindriven.model.form.SeminarForm;
import kr.domaindriven.service.SeminarService;
import kr.domaindriven.util.ControllerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by donghoon on 2016. 5. 29..
 * Seminar 에 대해서 Rest로 CRUD 작업을 할 수 있는 Controller 입니다.
 */

@RestController
@RequestMapping(value = "/seminar")
public class SeminarRestCntrl {
    private Logger logger = LoggerFactory.getLogger(SeminarRestCntrl.class);

    @Autowired
    private SeminarService service;
    @Autowired
    private ControllerUtil util;

    @ModelAttribute
    public SeminarForm setUpForm() {
        return new SeminarForm();
    }

    /**
     * 세미나 추가 기능 구현.
     *
     * @param form
     * @param result
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Seminar save(@Validated SeminarForm form, BindingResult result) {
        logger.info("세미나 생성.");
        if (result.hasErrors()) {
            util.LoggingError(result, logger);
            return null;
        }

        Seminar newSeminar = new Seminar(form.getTitle(), form.getDate());

        return service.save(newSeminar);
    }

    @RequestMapping(value = "/taskProgress", method = RequestMethod.POST)
    public Seminar updateTaskProgress(@RequestParam String title,
                                      @RequestParam String order,
                                      @RequestParam String progress) {
        logger.info("태스크 진행률 업데이트.");
        logger.info("title: {}, order: {}, progress: {}", title, order, progress);

        Seminar currentSeminar = service.findByTitle(title);
        currentSeminar.getTasks().get(Integer.parseInt(order)).setProgress(Integer.parseInt(progress));
        return service.save(currentSeminar);
    }

}
