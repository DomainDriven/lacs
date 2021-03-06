package kr.domaindriven.web;

import kr.domaindriven.model.Seminar;
import kr.domaindriven.model.Worker;
import kr.domaindriven.model.form.SeminarForm;
import kr.domaindriven.service.SeminarService;
import kr.domaindriven.service.WorkerService;
import kr.domaindriven.util.ControllerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by donghoon on 2016. 5. 29..
 * Seminar 에 대해서 Rest로 CRUD 작업을 할 수 있는 Controller 입니다.
 */

@RestController
@RequestMapping(value = "/seminar")
public class SeminarRestCntrl {
    private Logger logger = LoggerFactory.getLogger(SeminarRestCntrl.class);

    @Autowired
    private SeminarService smService;
    @Autowired
    private WorkerService wService;
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
    public Seminar saveSeminar(@Validated SeminarForm form, BindingResult result) {
        if (result.hasErrors()) {
            util.LoggingError(result, logger);
            return null;
        }

        Seminar newSeminar = new Seminar(form.getTitle(), form.getDate());

        return smService.save(newSeminar);
    }

    @RequestMapping(value = "/taskProgress", method = RequestMethod.POST)
    public Seminar updateTaskProgress(@RequestParam String title,
                                      @RequestParam String order,
                                      @RequestParam String progress) {

        Seminar currentSeminar = smService.findByTitle(title);
        currentSeminar.getTasks().get(Integer.parseInt(order)).setProgress(Integer.parseInt(progress));
        return smService.save(currentSeminar);
    }

    @RequestMapping(value = "/assignWorker", method = RequestMethod.POST)
    public Seminar assignWorkerToSeminar(@RequestParam String id,
                                @RequestParam String workerName,
                                @RequestParam int index) {

        Seminar currentSeminar = smService.findOne(id);
        Worker selectedWorker = wService.findByName(workerName);
        currentSeminar.getTasks().get(index).setWorkers(Arrays.asList(selectedWorker));

        return smService.save(currentSeminar);
    }

    @RequestMapping(value = "/editSeminar", method = RequestMethod.POST)
    public Seminar editSeminar(@RequestParam String id, @RequestParam String title, @RequestParam Date date) {

        Seminar editSeminar = smService.findOne(id);
        editSeminar.setTitle(title);
        editSeminar.setDate(date);

        return smService.save(editSeminar);

    }

    @RequestMapping(value = "/editTitle", method = RequestMethod.POST)
    public Seminar updateSerminarTitle(@RequestParam String id, @RequestParam String title) {

        Seminar currentSeminar = smService.findOne(id);
        currentSeminar.setTitle(title);

        return smService.save(currentSeminar);
    }

    @RequestMapping(value = "/editDate", method = RequestMethod.POST)
    public Seminar updateSerminarDate(@RequestParam String id, @RequestParam Date date) {

        Seminar currentSeminar = smService.findOne(id);
        currentSeminar.setDate(date);

        return smService.save(currentSeminar);
    }

    @RequestMapping(value = "/editAudience", method = RequestMethod.POST)
    public Seminar editAudience(@RequestParam String id, @RequestParam int audienceCount) {

        Seminar currentSeminar = smService.findOne(id);
        currentSeminar.setAudience(audienceCount);

        return smService.save(currentSeminar);
    }

    @RequestMapping(value = "/isCompleted", method = RequestMethod.POST)
    public Seminar isCompleted(@RequestParam String id, @RequestParam boolean isCompleted) {

        Seminar currentSeminar = smService.findOne(id);
        currentSeminar.setIsCompleted(isCompleted);

        return smService.save(currentSeminar);
    }

}
