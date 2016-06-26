package kr.domaindriven.web;

import kr.domaindriven.model.Worker;
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

/**
 * Created by donghoon on 2016-06-26.
 */
@Controller
public class WorkerController {
    private final Logger logger = LoggerFactory.getLogger(WorkerController.class);
    private final String LAYOUT = "layout";

    @Autowired
    private WorkerService service;

    @RequestMapping(value = "/allWorker", method = RequestMethod.GET)
    public String allWorker(@PageableDefault Pageable pageable, Model model) {
        logger.info("모든 운영진 리스트 화면..");

        Page<Worker> workers = service.findAll(pageable);
        model.addAttribute("workers", workers);

        model.addAttribute("page", "allWorker");

        return LAYOUT;
    }

    @RequestMapping(value = "/addWorker", method = RequestMethod.GET)
    public String addInstructorForm(Model model) {
        logger.info("운영진 추가 폼 화면..");

        model.addAttribute("page", "addWorker");

        return LAYOUT;
    }

    //TODO: worker edit 기능 구현.

    @RequestMapping(value = "/editWorker", method = RequestMethod.GET)
    public String editInstructorForm(Model model, @RequestParam String id) {
        logger.info("운영진 편집 폼 화면..");
        logger.info("운영진 id: {}", id);

        model.addAttribute("page", "editWorker");

        return LAYOUT;
    }


    @RequestMapping(value = "/deleteWorker", method = RequestMethod.POST)
    public String deleteSeminar(@PageableDefault Pageable pageable, Model model, @RequestParam String id) {
        logger.info("운영진 삭제.");
        logger.info("운영진 id: {}", id);

        service.deleteWorker(id);

        return allWorker(pageable, model);
    }
}
