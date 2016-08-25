package kr.domaindriven.web;

import kr.domaindriven.model.Instructor;
import kr.domaindriven.model.Seminar;
import kr.domaindriven.model.Worker;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by donghoon on 2016-07-24.
 */
@Controller
public class InstructorController {
    private final Logger logger = LoggerFactory.getLogger(InstructorController.class);
    private final String LAYOUT = "layout";
    private final String REDIRECT = "redirect:/";
    private final String ACTIVE_CLASS = "active";

    @Autowired
    private InstructorService instructorService;

    /**
     * add form 화면
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addInstructor", method = RequestMethod.GET)
    public String addInstructorForm(Model model) {

        model.addAttribute("formMenu", ACTIVE_CLASS);
        model.addAttribute("addInstructorClass", ACTIVE_CLASS);
        model.addAttribute("page", "addInstructor");

        return LAYOUT;
    }

    /**
     * list 화면
     *
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = "/allInstructor", method = RequestMethod.GET)
    public String allInstructor(@PageableDefault Pageable pageable, Model model) {

        Page<Instructor> instructors = instructorService.findAll(pageable);
        model.addAttribute("instructors", instructors);

        model.addAttribute("listMenu", ACTIVE_CLASS);
        model.addAttribute("allInstructorClass", ACTIVE_CLASS);
        model.addAttribute("page", "allInstructor");

        return LAYOUT;
    }

    /**
     * edit form 화면
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/editInstructor", method = RequestMethod.GET)
    public String editInstructor(Model model, @RequestParam String id) {

        Instructor editInstructor = instructorService.findOne(id);

        model.addAttribute("editInstructor", editInstructor);
        model.addAttribute("page", "editInstructor");

        return LAYOUT;
    }

    @RequestMapping(value = "/deleteInstructor", method = RequestMethod.POST)
    public String deleteInstructor(@PageableDefault Pageable pageable, Model model, @RequestParam String id) {

        instructorService.deleteInstructor(id);

        return allInstructor(pageable, model);
    }
}
