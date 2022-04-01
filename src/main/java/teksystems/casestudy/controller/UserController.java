package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class UserController {

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception{
        ModelAndView response = new ModelAndView();

        response.setViewName("user/register");

        return response;

    }

    @RequestMapping(value = "/user/registerSubmit", method = RequestMethod.POST)
    public ModelAndView registerSubmit(@RequestParam("email") String email) throws Exception{
        ModelAndView response = new ModelAndView();

        response.setViewName("user/register");

        log.info("email from form submission: " + email);

        return response;

    }
}
