package kr.domaindriven.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by donghoon on 2016. 8. 14..
 */
@Controller
public class LoginController {

    private static final String PAGE = "login";

    @RequestMapping(value = "/login")
    public String landingPage() {
        return PAGE;
    }

}
