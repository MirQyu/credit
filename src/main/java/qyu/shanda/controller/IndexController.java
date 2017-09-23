package qyu.shanda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by MirQ on 17/9/22.
 */
@Controller
public class IndexController {
    @RequestMapping(path = {"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(path = {"/myCourse"})
    public String myCourse() {
        return "myCourse";
    }

    @RequestMapping(path = {"/courseList"})
    public String courseList() {
        return "courseList";
    }

    @RequestMapping(path = {"/userInfo"})
    public String userInfo() {
        return "userInfo";
    }

    @RequestMapping(path = {"/login"})
    public String login() {
        return "login";
    }
}
