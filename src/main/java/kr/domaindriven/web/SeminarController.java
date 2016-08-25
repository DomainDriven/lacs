package kr.domaindriven.web;

import kr.domaindriven.model.Seminar;
import kr.domaindriven.model.Worker;
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
 * Created by donghoon on 2016-06-24.
 */
@Controller
public class SeminarController {
    private final Logger logger = LoggerFactory.getLogger(SeminarController.class);
    private final String LAYOUT = "layout";
    private final String REDIRECT = "redirect:/";
    private final String ACTIVE_CLASS = "active";

    @Autowired
    private SeminarService smService;
    @Autowired
    private WorkerService wService;

    /**
     * 메인 화면.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/currentSeminar", method = RequestMethod.GET)
    public String currentSeminar(Model model) {

        Seminar currentSeminar = smService.findByIsCompleted(false);
        List<Worker> workers = wService.findAll();

        long diffDate = 0;

        if (currentSeminar == null)
            currentSeminar = new Seminar("현재 진행중인 세미나가 없습니다.");
        else
            diffDate = (currentSeminar.getDate().getTime() - new Date().getTime()) / (24 * 60 * 60 * 1000);


        model.addAttribute("currentSeminar", currentSeminar);
        model.addAttribute("workers", workers);
        model.addAttribute("diffDate", diffDate);
        model.addAttribute("homeMenu", ACTIVE_CLASS);
        model.addAttribute("currentSeminarClass", ACTIVE_CLASS);
        model.addAttribute("page", "currentSeminar");

        return LAYOUT;
    }

    /**
     * add form 화면
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addSeminar", method = RequestMethod.GET)
    public String addSeminarForm(Model model) {

        model.addAttribute("formMenu", ACTIVE_CLASS);
        model.addAttribute("addSeminarClass", ACTIVE_CLASS);
        model.addAttribute("page", "addSeminar");

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

        Page<Seminar> seminars = smService.findAll(pageable);
        model.addAttribute("seminars", seminars);

        model.addAttribute("listMenu", ACTIVE_CLASS);
        model.addAttribute("allSeminarClass", ACTIVE_CLASS);
        model.addAttribute("page", "allSeminar");

        return LAYOUT;
    }

    /**
     * edit form 화면
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/editSeminar", method = RequestMethod.GET)
    public String editSeminarForm(Model model, @RequestParam String id) {

        Seminar editSeminar = smService.findOne(id);

        model.addAttribute("editSeminar", editSeminar);
        model.addAttribute("page", "editSeminar");

        return LAYOUT;
    }

    @RequestMapping(value = "/deleteSeminar", method = RequestMethod.POST)
    public String deleteSeminar(@PageableDefault Pageable pageable, Model model, @RequestParam String id) {

        smService.deleteSeminar(id);

        return allSeminar(pageable, model);
    }
}
