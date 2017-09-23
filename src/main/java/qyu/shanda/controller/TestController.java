package qyu.shanda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by MirQ on 17/9/24.
 */

@Controller
public class TestController {

    @RequestMapping(value = {"/profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("groupId") String groupId,
                          @PathVariable("userId") int userId,
                          @RequestParam(value = "type", defaultValue = "1") int type,
                          @RequestParam(value = "key", defaultValue = "nowcoder") String key) {
        return String.format("{%s}, {%d}, {%d}, {%s}", groupId, userId, type, key);
    }

    @RequestMapping(value = {"/response"})
    @ResponseBody
    public String response(@CookieValue(value = "nowcoderID", defaultValue = "a") String nowcoderID,
                           @RequestParam(value = "key", defaultValue = "key") String key,
                           @RequestParam(value = "value", defaultValue = "value") String value,
                           HttpServletResponse response,
                           HttpSession session) {
        response.addCookie(new Cookie(key, value));
        response.addHeader(key, value);

        return "NowCoderID from cookie:" + nowcoderID;
    }
}
