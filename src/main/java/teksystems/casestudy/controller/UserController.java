package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.User;
import teksystems.casestudy.formbean.RegisterFormBean;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserDAO userDao;

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("user/register");

        // blank form to seed form so it does not error out with trying to set values to the form
        RegisterFormBean form = new RegisterFormBean();
        response.addObject("form", form);

        return response;

    }

    @RequestMapping(value = "/user/registerSubmit", method = RequestMethod.POST)
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {
            HashMap errors = new HashMap();

            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.put(((FieldError) error).getField(), error.getDefaultMessage());
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            response.addObject("formErrors", errors);

            response.setViewName("redirect:/user/register");
            return response;
        }

        User user = new User();

        if (form.getId() != null) {
            user = userDao.findById(form.getId());
        }

        user.setEmail(form.getEmail());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setPassword(form.getPassword());

        userDao.save(user);

        log.info(form.toString());

        response.setViewName("redirect:/user/edit/" + user.getId());

        return response;
    }

    @RequestMapping(value = "/user/edit/{userId}")
    public ModelAndView editUser(@PathVariable("userId") Integer userId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        User user = userDao.findById(userId);

        RegisterFormBean form = new RegisterFormBean();
        form.setId(user.getId());
        form.setEmail(user.getEmail());
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setPassword(user.getPassword());
        form.setConfirmPassword(user.getPassword());

        response.addObject("form", form);

        return response;
    }

    // ---create a form on the user search page that action submits to this route using a get method
    // ---make an input box for the user to enter a search term for first name
    // ---add a @RequestParam to take in a search value from the input box - use required = false in the annotation
    // ---use the search value in the query
    // ---add the search value to the model and make it display in the input box when the page reloads
    // ---add error checking to make sure that the incoming search value is not null and is not empty.
    // ---find apache string utils on maven central and add it to your pom file - very high recommendation
    // ---research the StringUtils.isEmpty function and use for error checking
    @GetMapping("/user/search")
    public ModelAndView search(@RequestParam(value = "searchFirstName", required = false) String searchFirstName) {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/search");

        log.error("Searched for: " + searchFirstName);
        List<User> users = new ArrayList<>();

        // we dont want to show an error if the request is null, which indicates the first load of the page
        if(searchFirstName != null){
            if(!StringUtils.isEmpty(searchFirstName)){
                users = userDao.findByFirstNameIgnoreCaseContaining(searchFirstName);
            }else{
                response.addObject("searchError", "You cannot have an empty search");
            }
        }

        response.addObject("users", users);

        return response;
    }
}
