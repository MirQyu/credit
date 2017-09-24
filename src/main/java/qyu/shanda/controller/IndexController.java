package qyu.shanda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qyu.shanda.dao.CourseArrangeDAO;
import qyu.shanda.dao.LessonTimeDAO;
import qyu.shanda.dao.PublishCourseDAO;
import qyu.shanda.model.*;
import qyu.shanda.service.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

    @Autowired
    HostHolder hostHolder;

    @Autowired
    LessonTimeService lessonTimeService;

    @Autowired
    RommService rommService;

    @RequestMapping(path = {"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(path = {"/myCourse"})
    public String myCourse(Model model) {
        if (hostHolder.getStudent() != null) {
            List<ViewObject> vos = new ArrayList<>();
            List<Publish_Course> publishCourseList = courseService.getAllCourseByStudentId(hostHolder.getStudent().getId());
            if (publishCourseList != null) {
                generateViewList(publishCourseList, vos);
                model.addAttribute("vos", vos);
            }

        }


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
        generateViewList(publishCourseList, vos);
        model.addAttribute("vos", vos);
        model.addAttribute("college_id", college_id);

        return "courseList";
    }

    private void generateViewList(List<Publish_Course> publishCourseList, List<ViewObject> vos) {
        if (publishCourseList != null) {
            for (Publish_Course publishCourse : publishCourseList) {
                // 传给模板的视图对象， 聚集相关内容
                ViewObject vo = new ViewObject();
                vo.set("publishCourse", publishCourse);


                Course course = courseService.getCourseById(publishCourse.getCourse_id());
                //logger.debug("courseName", course.getName());
                logger.info(course.toString());

                vo.set("course", course);

                Teacher teacher = teacherService.getTeacherById(publishCourse.getTea_id());
                vo.set("teacher", teacher);

                List<Course_Arrange> courseArrangeList = courseService.getByPublishCourseId(publishCourse.getId());
                Room room = null;
                List<String> timeList = new ArrayList<>();
                for (Course_Arrange courseArrange : courseArrangeList) {
                    if (room == null) {
                        room = rommService.getRoomById(courseArrange.getRoom_id());
                    }
                    Lesson_Time lessonTime = lessonTimeService.getLessonTimeById(courseArrange.getLesson_time_id());
                    timeList.add(lessonTime.getTime());
                }

                vo.set("room", room);
                vo.set("timeList", timeList);
                vos.add(vo);
            }
        }
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
}
