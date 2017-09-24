package qyu.shanda.service;

import freemarker.ext.beans.HashAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import qyu.shanda.dao.ChooseCourseDAO;
import qyu.shanda.dao.LoginTicketDAO;
import qyu.shanda.dao.PublishCourseDAO;
import qyu.shanda.dao.StudentDAO;
import qyu.shanda.model.Choose_Course;
import qyu.shanda.model.LoginTicket;
import qyu.shanda.model.Publish_Course;
import qyu.shanda.model.Student;

import java.util.*;

/**
 * Created by MirQ on 17/9/23.
 */
@Service
public class StudentService {

    private static Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    LoginTicketDAO loginTicketDAO;

    public Map<String, String> register(String name,
                                        String password) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isEmpty(name)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isEmpty(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        Student student = studentDAO.selectByName(name);

        if (student != null) {
            map.put("msg", "用户名已经被注册");
            return map;
        }
        Random random = new Random();
        student = new Student();
        student.setName(name);
        student.setPassword(password);
        student.setSex(random.nextBoolean());
        student.setAge(18);
        student.setYear(2017);
        student.setType("0");
        student.setHead_url(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
        studentDAO.addStudent(student);

        String ticket = addLoginTicket(student.getId(), 10);
        map.put("ticket", ticket);

        return map;
    }


    public Map<String, String> login(String name,
                                     String password) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isEmpty(name)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isEmpty(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        Student student = studentDAO.selectByName(name);

        if (student == null) {
            map.put("msg", "用户名不存在");
            return map;
        }

        if (!password.equals(student.getPassword())) {
            map.put("msg", "密码不正确");
            return map;
        }

        // 下发一个ticket

        String ticket = addLoginTicket(student.getId(), 10);
        map.put("ticket", ticket);

        return map;
    }

    public String addLoginTicket(int stu_id, int expiredDays) {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setStu_id(stu_id);
        Date date = new Date();
        date.setTime(date.getTime() + expiredDays*3600*24);
        logger.debug("ticket expired time: ", date.getTime());
        loginTicket.setExpired(date);
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDAO.addTicket(loginTicket);
        return loginTicket.getTicket();
    }


    public void logout(String ticket) {
        loginTicketDAO.updateStatus(ticket, 1);
    }

    public Student getStudent(int id) {
        return studentDAO.selectById(id);
    }
}
