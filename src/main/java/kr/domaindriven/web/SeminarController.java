package kr.domaindriven.web;

import kr.domaindriven.model.Seminar;
import kr.domaindriven.service.SeminarService;
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

/**
 * Created by donghoon on 2016-06-24.
 */
@Controller
public class SeminarController {
    private final Logger logger = LoggerFactory.getLogger(SeminarController.class);
    private final String LAYOUT = "layout";
    private final String REDIRECT = "redirect:/";

    @Autowired
    private SeminarService smService;

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

    @RequestMapping(value = "/seminar/editTitle", method = RequestMethod.POST)
    public String updateSerminarTitle(Model model, @RequestParam String id, @RequestParam String title) {
        logger.info("세미나 주제 업데이트.");
        logger.info("id: {}, title: {}", id, title);

        Seminar currentSeminar = smService.findOne(id);
        currentSeminar.setTitle(title);
        smService.save(currentSeminar);

        model.addAttribute("page", "currentSeminar");

        return REDIRECT;
    }

    @RequestMapping(value = "/seminar/editDate", method = RequestMethod.POST)
    public String updateSerminarDate(Model model, @RequestParam String id, @RequestParam Date date) {
        logger.info("세미나 날짜 업데이트.");
        logger.info("id: {}, date: {}", id, date);

        Seminar currentSeminar = smService.findOne(id);
        currentSeminar.setDate(date);
        smService.save(currentSeminar);

        model.addAttribute("page", "currentSeminar");

        return REDIRECT;
    }

}
