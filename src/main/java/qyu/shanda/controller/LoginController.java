package qyu.shanda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import qyu.shanda.service.StudentService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by MirQ on 17/9/24.
 */
@Controller
public class LoginController {
    private static final Logger logger =  LoggerFactory.getLogger(LoginController.class);


    @Autowired
    StudentService studentService;


    @RequestMapping(path = "/reglogin", method = {RequestMethod.GET})
    public String reglogin(Model model,
                           @RequestParam(value = "next", required = false) String next) {
        model.addAttribute("next", next);
        return "login";
    }

    //处理注册
    @RequestMapping(path = {"/reg"}, method = {RequestMethod.POST})
    public String reg(Model model,
                      HttpServletResponse response,
                      @RequestParam("name") String name,
                      @RequestParam("password") String password) {
        try {
            Map<String, String> map = studentService.register(name, password);
            return processMap(model, response, map, null);
        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
            return "login";
        }
    }

    //处理登陆
    @RequestMapping(path = {"/login"}, method = {RequestMethod.POST})
    public String login(Model model,
                        HttpServletResponse response,
                        @RequestParam("name") String name,
                        @RequestParam("password") String password,
                        @RequestParam(value = "next", required = false) String next) {
        try {
            Map<String, String> map = studentService.login(name, password);
            logger.debug("next=" + next);
            return processMap(model, response, map, next);

        } catch (Exception e) {
            logger.error("登陆异常" + e.getMessage());
            return "login";
        }
    }

    @RequestMapping(path = {"/logout"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(@CookieValue("ticket") String ticket) {
        studentService.logout(ticket);
        return "redirect:/reglogin";
    }

    private String processMap(Model model,
                              HttpServletResponse response, Map<String, String> map,
                              String next) throws IOException {
        // 已经登陆
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket"));
//            cookie.setMaxAge();
            cookie.setPath("/");
            response.addCookie(cookie);

            if (!StringUtils.isEmpty(next)) {
                return "redirect:" + next;
            }

            return "redirect:/";
        }
        else {
            model.addAttribute("msg", map.get("msg"));
            return "login";
        }
    }


}
