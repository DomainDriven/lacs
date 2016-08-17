package kr.domaindriven.web;

import kr.domaindriven.util.FacebookAuth;
import kr.domaindriven.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jerry on 2016-08-17.
 */
@RestController
public class LoginRestCntrl {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/authenticationFacebook", method = RequestMethod.POST)
    public Boolean authenticate(@RequestParam("id") String id, @RequestParam("email") String email) {
        return loginService.isOurUser(new FacebookAuth(id,email));
    }
}
