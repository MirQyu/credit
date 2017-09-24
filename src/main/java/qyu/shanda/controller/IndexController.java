package qyu.shanda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qyu.shanda.dao.PublishCourseDAO;
import qyu.shanda.model.*;
import qyu.shanda.service.CollegeService;
import qyu.shanda.service.CourseService;
import qyu.shanda.service.PublishCourseService;
import qyu.shanda.service.TeacherService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MirQ on 17/9/22.
 */
@Controller
public class IndexController {
    private static final Logger logger =  LoggerFactory.getLogger(IndexController.class);

    @Autowired
    PublishCourseService publishCourseService;

    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    CollegeService collegeService;

    @RequestMapping(path = {"/index"})
    public String index() {

        return "index";
    }

    @RequestMapping(path = {"/myCourse"})
    public String myCourse() {
        return "myCourse";
    }

    @RequestMapping(path = {"/courseList"})
    public String courseList(Model model,
                        @RequestParam(value = "offset", defaultValue = "0") int offset,
                        @RequestParam(value = "college_id", defaultValue = "0") int college_id) {

        List<Publish_Course> publishCourseList = null;

        if (college_id == 0) {
            publishCourseList = publishCourseService.getAllPublishCourse(offset, 15);
        }
        else {
            publishCourseList = publishCourseService.getAllPublishCourseByCollegeId(college_id, offset, 15);
            College college = collegeService.getCollegeById(college_id);
            model.addAttribute("college", college);
        }

        List<ViewObject> vos = new ArrayList<>();
        if (publishCourseList != null) {
            for (Publish_Course publishCourse : publishCourseList) {
                ViewObject vo = new ViewObject();
                vo.set("publishCourse", publishCourse);


                Course course = courseService.getCourseById(publishCourse.getCourse_id());
                //logger.debug("courseName", course.getName());
                logger.info(course.toString());

                vo.set("course", course);

                Teacher teacher = teacherService.getTeacherById(publishCourse.getTea_id());
                vo.set("teacher", teacher);
                vos.add(vo);
            }
        }
        model.addAttribute("vos", vos);
        model.addAttribute("college_id", college_id);

        return "courseList";
    }


    @RequestMapping(path = {"/collegeList"})
    public String collegeList(Model model) {
        List<College> collegeList = collegeService.getAllCollege();
        model.addAttribute("collegeList", collegeList);
        return "collegeList";
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
