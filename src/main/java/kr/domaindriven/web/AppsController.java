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

import java.util.Date;

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
     * 메인 화면.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        logger.info("현재 진행중인 세미나 화면..");

        Seminar currentSeminar = smService.findByIsCompleted(false);
        long diffDate = 0;

        if (currentSeminar == null)
            currentSeminar = new Seminar("현재 진행중인 세미나가 없습니다.");
        else
            diffDate = (currentSeminar.getDate().getTime() - new Date().getTime()) / (24 * 60 * 60 * 1000);


        model.addAttribute("diffDate", diffDate);
        model.addAttribute("currentSeminar", currentSeminar);
        model.addAttribute("page", "currentSeminar");

        return LAYOUT;
    }

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
        Seminar currentseminar = smService.findByIsCompleted(false);
        String title = currentseminar.getTitle(); // 현재 진행중인 세미나 이름
        logger.info("title: " + title);
        Page<Instructor> instructors = instructorService.findAll(pageable); //등록된 강사정보 호출
        Page<Worker> workers = wkService.findAll(pageable); // 등록된 운영진 정보 호출
        Task castingInstructor = currentseminar.getTasks().get(0);
        String pageModelsValue = "castingInstructor";  // 등록이 하나라도 진행됬으면 확인페이지로. 최소 강사명은 선정해야할것같음

        if (currentseminar.getTasks().get(0).getProgress() == 0) {
            pageModelsValue = "castingInstructorForm";
            model.addAttribute("title", title);
            model.addAttribute("order", 0);
            model.addAttribute("instructors", instructors);
            model.addAttribute("workers", workers);
        }
        //// TODO: 2016-06-20 task가 아닌 필요한 모델에 대해 직접 데이터를 넘겨야 할 것 같음 예_String 강사명 - jerry 
        model.addAttribute("task", castingInstructor);
        model.addAttribute("page", pageModelsValue);

        return LAYOUT;
    }

    @RequestMapping(value = "/castingInstructor", method = RequestMethod.POST)
    public String castingInstructor(@ModelAttribute CastingInstructorForm castingInstructorForm, Model model) {
        logger.info(castingInstructorForm.toString());
        Seminar seminar = smService.findByIsCompleted(false);
        String title = seminar.getTitle(); // 현재 진행중인 세미나 이름
        smService.insertTasksElements(title, castingInstructorForm.getSelectedInstructor());
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
