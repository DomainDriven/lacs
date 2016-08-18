package kr.domaindriven.web;

import kr.domaindriven.util.FacebookAuth;
import kr.domaindriven.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/authenticationFacebook/{id}", method = RequestMethod.GET)
    public String authenticate(@PathVariable String id) {
        System.out.println(id);
        return id;
    }
}
