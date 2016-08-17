package kr.domaindriven.web;

import kr.domaindriven.model.form.IdAndPassWordForm;
import kr.domaindriven.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by donghoon on 2016. 8. 14..
 */
@Controller
public class LoginController {

    @Autowired
    private SeminarController seminarController;

    @Autowired
    private LoginService loginService;

    private static final String PAGE = "login";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String landingPage() {
        return PAGE;
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public String authenticate(@ModelAttribute IdAndPassWordForm idAndPassWord, Model model) {
        if (loginService.isOurUser(idAndPassWord)) {
            return seminarController.index(model);
        } else {
            return PAGE;
        }

    }
    @RequestMapping(value = "/facebookSSO")
    public String facebookSSO(){
        System.out.println("11");
        return "facebookSSO";
    }

}
