package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.User;

import java.util.List;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();



        //User user = userDAO.findByEmail("email@email.com");
        //log.info(user.toString());
        //System.out.println(user.toString());

//        List<User> users = userDAO.findByFirstName("Mark");
        List<User> users = userDAO.findAll();
        System.out.println("ahhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        for (User user : users){
            log.info(user.toString());
        }

        response.setViewName("index");

        return response;
    }

}
