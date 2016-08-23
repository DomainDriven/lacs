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
    private final String ACTIVE_CLASS = "active";

    @Autowired
    private WorkerService service;

    @RequestMapping(value = "/addWorker", method = RequestMethod.GET)
    public String addWorkerForm(Model model) {

        model.addAttribute("formMenu", ACTIVE_CLASS);
        model.addAttribute("addWorkerClass", ACTIVE_CLASS);
        model.addAttribute("page", "addWorker");

        return LAYOUT;
    }

    @RequestMapping(value = "/allWorker", method = RequestMethod.GET)
    public String allWorker(@PageableDefault Pageable pageable, Model model) {

        Page<Worker> workers = service.findAll(pageable);
        model.addAttribute("workers", workers);

        model.addAttribute("listMenu", ACTIVE_CLASS);
        model.addAttribute("allWorkerClass", ACTIVE_CLASS);
        model.addAttribute("page", "allWorker");

        return LAYOUT;
    }

    @RequestMapping(value = "/editWorker", method = RequestMethod.GET)
    public String editWorkerForm(Model model, @RequestParam String id) {

        Worker editWorker = service.findOne(id);

        model.addAttribute("editWorker", editWorker);
        model.addAttribute("page", "editWorker");

        return LAYOUT;
    }

    @RequestMapping(value = "/deleteWorker", method = RequestMethod.POST)
    public String deleteWorker(@PageableDefault Pageable pageable, Model model, @RequestParam String id) {

        service.deleteWorker(id);

        return allWorker(pageable, model);
    }
}
