package web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping(value = "/")
     public ModelAndView userList(){
        List<User> userList = userService.userList();
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    @GetMapping (value = "/add")
    public String addPage(){
        return "add";
    }

   @PostMapping(value = "/add")
   public String addUser(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") long id){
    User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

   @PostMapping(value = "/edit")
   public String updateUser(@ModelAttribute ("user") User user){
        userService.save(user);
        return "redirect:/";
    }

   @GetMapping(value = "/delete/{id}")
   public String deleteUser(@PathVariable("id") long id){
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/";
    }


}
