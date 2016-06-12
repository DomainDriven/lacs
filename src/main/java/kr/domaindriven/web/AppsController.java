package kr.domaindriven.web;

import kr.domaindriven.model.*;
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
     * 메인 화면.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        logger.info("현재 진행중인 세미나 화면..");

        Seminar currentSeminar = smService.findByIsCompleted(false);


        if (currentSeminar == null) {
            currentSeminar = new Seminar("현재 진행중인 세미나가 없습니다.");
        }
        model.addAttribute("currentSeminar", currentSeminar);
        model.addAttribute("page", "currentSeminar");

        return LAYOUT;
    }

    /**
     * form 화면
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addSeminar", method = RequestMethod.GET)
    public String addSeminarForm(Model model) {
        logger.info("세미나 추가 폼 화면..");

        model.addAttribute("page", "addSeminar");

        return LAYOUT;
    }

    @RequestMapping(value = "/addWorker", method = RequestMethod.GET)
    public String addInstructorForm(Model model) {
        logger.info("운영진 추가 폼 화면..");

        model.addAttribute("page", "addWorker");

        return LAYOUT;
    }

    /**
     * list 화면
     *
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = "/allSeminar", method = RequestMethod.GET)
    public String allSeminar(@PageableDefault Pageable pageable, Model model) {
        logger.info("모든 세미나 리스트 화면..");

        Page<Seminar> seminars = smService.findAll(pageable);
        model.addAttribute("seminars", seminars);

        model.addAttribute("page", "allSeminar");

        return LAYOUT;
    }

    @RequestMapping(value = "/allWorker", method = RequestMethod.GET)
    public String allWorker(@PageableDefault Pageable pageable, Model model) {
        logger.info("모든 운영진 리스트 화면..");

        Page<Worker> workers = wkService.findAll(pageable);
        model.addAttribute("workers", workers);

        model.addAttribute("page", "allWorker");

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
        // TestModel testModel = new TestModel(); //test 데이터 입력을 위한 TestModel
        Seminar seminar = smService.findByIsCompleted(false);
        String currentSeminarTitle = seminar.getTitle(); // 현재 진행중인 세미나 이름
        Page<Instructor> instructors = instructorService.findAll(pageable); //등록된 강사정보 호출
        Page<Worker> workers = wkService.findAll(pageable); // 등록된 운영진 정보 호출
        model.addAttribute("instructors", instructors);
        model.addAttribute("workers",workers);
        model.addAttribute("page", "castingInstructor");

        return LAYOUT;
    }

    /*@RequestMapping(value = "/castingInstructor", method = RequestMethod.POST)
    public String castingInstructor(@ModelAttribute SelectedInstructorForm selectedInstructorForm, Model model) {
        logger.info("강사섭외 진행률:" + selectedInstructorForm.getProgress() + "%");
        // TODO: 2016-06-08 서비스로직은 서비스 패키지로 옮기기. -재열 
        Seminar seminar = smService.findByIsCompleted(false);
        int progress = selectedInstructorForm.getProgress(); //강사섭외 진행률
        seminar.getTasks().get(0).setProgress(progress);// 강사섭외 task의 progress 변경
        CastingInstructor castingInstructor = new CastingInstructor(selectedInstructorForm);
        smService.save(seminar,castingInstructor); // 강사섭외 진행률 업데이트
        model.addAttribute("selectedInstrouctor", selectedInstructorForm);
        model.addAttribute("page", "seminarInstructor");
        return LAYOUT;
    }*/

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
