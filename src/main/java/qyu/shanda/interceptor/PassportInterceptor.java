package qyu.shanda.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import qyu.shanda.dao.LoginTicketDAO;
import qyu.shanda.dao.StudentDAO;
import qyu.shanda.model.HostHolder;
import qyu.shanda.model.LoginTicket;
import qyu.shanda.model.Student;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by MirQ on 17/9/24.
 */

@Component
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    LoginTicketDAO loginTicketDAO;

    @Autowired
    HostHolder hostHolder;


    //进入Controller之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }

        if (ticket != null) {
            LoginTicket loginTicket = loginTicketDAO.selectByTicket(ticket);
            if (loginTicket == null ||
                loginTicket.getExpired().before(new Date()) ||
                loginTicket.getStatus() != 0) {
                return true;
            }

            Student student = studentDAO.selectById(loginTicket.getStu_id());
            hostHolder.setStudent(student);
        }

        return true;
    }

    //渲染之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && hostHolder.getStudent() != null) {
            modelAndView.addObject("student", hostHolder.getStudent());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
