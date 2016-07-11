package kr.domaindriven.web;

import kr.domaindriven.model.*;
import kr.domaindriven.model.form.CastingInstructorForm;
import kr.domaindriven.persistance.InstructorRepository;
import kr.domaindriven.service.InstructorService;
import kr.domaindriven.service.SeminarService;
import kr.domaindriven.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jerry on 2016-05-15.
 */
@Controller
public class AppsController {
    private final Logger logger = LoggerFactory.getLogger(AppsController.class);
    private final String LAYOUT = "layout";

    //// TODO: 2016-05-30 추후 Service로 재 작업 요망 - jerry
    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private SeminarService smService;

    @Autowired
    private WorkerService wkService;

    @Autowired
    private InstructorService instructorService;

    /**
     * task 화면
     *
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = "/castingInstructor", method = RequestMethod.GET)
    public String castingInstructor(@PageableDefault Pageable pageable, Model model) {
        logger.info("강사 섭외 상세 화면..");
        Seminar currentSeminar = smService.findByIsCompleted(false);
        String title = currentSeminar.getTitle(); // 현재 진행중인 세미나 이름
        logger.info("title: " + title);
        Page<Instructor> instructors = instructorService.findAll(pageable); //등록된 강사정보 호출
        Page<Worker> workers = wkService.findAll(pageable); // 등록된 운영진 정보 호출
        Task castingInstructor = currentSeminar.getTasks().get(0);
        String pageModelsValue = "castingInstructor";  // 등록이 하나라도 진행됬으면 확인페이지로. 최소 강사명은 선정해야할것같음

        if (currentSeminar.getTasks().get(0).getProgress() == 0) {
            pageModelsValue = "castingInstructorForm";
            model.addAttribute("title", title);
            model.addAttribute("order", 0);
            model.addAttribute("instructors", instructors);
            model.addAttribute("workers", workers);
        }else {
            Worker worker = castingInstructor.getWorkers().get(0);
            model.addAttribute("selectedWorker", worker);
        }
        model.addAttribute("instructors", instructors);
        model.addAttribute("seminar", currentSeminar);
        model.addAttribute("task", castingInstructor);
        model.addAttribute("page", pageModelsValue);

        return LAYOUT;
    }

    @RequestMapping(value = "/castingInstructor", method = RequestMethod.POST)
    public String castingInstructor(@ModelAttribute CastingInstructorForm castingInstructorForm, Model model, @PageableDefault Pageable pageable) {
        logger.info(castingInstructorForm.toString());
        Seminar seminar = smService.findByIsCompleted(false);
        Task castingInstructorTask = seminar.getTasks().get(0);
        Worker worker = wkService.findByName(castingInstructorForm.getSelectedWorker());

        Page<Instructor> instructors = instructorService.findAll(pageable); //등록된 강사정보 호출
        Page<Worker> workers = wkService.findAll(pageable); // 등록된 운영진 정보 호출

        // TODO: 2016-06-29 아래 진행률, 선택된강사,작업자 등록은 service로 옮기기 - 재열

        //진행률 업데이트
        castingInstructorTask.setProgress(Integer.parseInt(castingInstructorForm.getProgress()));
        //선택된 강사 등록
        castingInstructorTask.setSelectedInstructor(castingInstructorForm.getSelectedInstructor());
        //선택된 작업자 등록
        if (castingInstructorTask.getWorkers().get(0).getId() == null) {
            castingInstructorTask.getWorkers().clear();
            castingInstructorTask.getWorkers().add(0, worker);
        }

        //선택된 주제 등록 및 수정
        seminar.setTitle(castingInstructorForm.getTitle());
        //선택된 선호 날짜 등록
        castingInstructorTask.setFavoriteDate(castingInstructorForm.getDate());

        model.addAttribute("task", castingInstructorTask);
        smService.save(seminar);

        model.addAttribute("page", "castingInstructor");
        model.addAttribute("seminar", seminar);
        model.addAttribute("instructors", instructors);
        model.addAttribute("workers", workers);
        model.addAttribute("selectedWorker", worker);
        return LAYOUT;
    }

    @RequestMapping(value = "reservingPlace", method = RequestMethod.GET)
    public String reservingPlace(@RequestParam String title, Model model) {
        logger.info("장소 섭외 상세 화면..");
        logger.info("title: " + title);

        Seminar currentSeminar = smService.findByTitle(title);
        Task reservingPlace = currentSeminar.getTasks().get(1);

        model.addAttribute("title", title);
        model.addAttribute("order", 1);
        model.addAttribute("task", reservingPlace);
        model.addAttribute("page", "reservingPlace");

        return LAYOUT;
    }

    @RequestMapping(value = "makingPoster", method = RequestMethod.GET)
    public String makingPoster(@RequestParam String title, Model model) {
        logger.info("포스터 제작 상세 화면..");
        logger.info("title: " + title);

        Seminar currentSeminar = smService.findByTitle(title);
        Task makingPoster = currentSeminar.getTasks().get(2);

        model.addAttribute("title", title);
        model.addAttribute("order", 2);
        model.addAttribute("task", makingPoster);
        model.addAttribute("page", "makingPoster");

        return LAYOUT;
    }

    @RequestMapping(value = "registeringOnOffMix", method = RequestMethod.GET)
    public String registeringOnOffMix(@RequestParam String title, Model model) {
        logger.info("온오프 믹스 등록 상세 화면..");
        logger.info("title: " + title);

        Seminar currentSeminar = smService.findByTitle(title);
        Task registeringOnOffMix = currentSeminar.getTasks().get(3);

        model.addAttribute("title", title);
        model.addAttribute("order", 3);
        model.addAttribute("task", registeringOnOffMix);
        model.addAttribute("page", "registeringOnOffMix");

        return LAYOUT;
    }

    @RequestMapping(value = "promoting", method = RequestMethod.GET)
    public String promoting(@RequestParam String title, Model model) {
        logger.info("홍보 상세 화면..");
        logger.info("title: " + title);

        Seminar currentSeminar = smService.findByTitle(title);
        Task promoting = currentSeminar.getTasks().get(4);

        model.addAttribute("title", title);
        model.addAttribute("order", 4);
        model.addAttribute("task", promoting);
        model.addAttribute("page", "promoting");

        return LAYOUT;
    }

    @RequestMapping(value = "retrospecting", method = RequestMethod.GET)
    public String retrospecting(@RequestParam String title, Model model) {
        logger.info("회고 상세 화면..");
        logger.info("title: " + title);

        Seminar currentSeminar = smService.findByTitle(title);
        Task retrospecting = currentSeminar.getTasks().get(5);

        model.addAttribute("title", title);
        model.addAttribute("order", 5);
        model.addAttribute("task", retrospecting);
        model.addAttribute("page", "retrospecting");

        return LAYOUT;
    }

}
