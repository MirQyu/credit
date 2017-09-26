package qyu.shanda.controller;

import com.sun.javafx.sg.prism.NGShape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qyu.shanda.model.*;
import qyu.shanda.service.*;

import java.util.*;

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
    RoomService roomService;

    @Autowired
    BuildingService buildingService;

    @RequestMapping(path = {"/", "/index"})
    public String index() {

        return "index";
    }


    @RequestMapping(path = {"/addCourse"})
    public String addCourse(
            Model model,
            @RequestParam("publishCourseId") int publishCourseId) {
        Student student = hostHolder.getStudent();
        String msg = "选课成功";
        if (student != null) {
            //得到该学生已选的课程
            List<Choose_Course> chooseCourseList =  courseService.getChooseCourseByStuId(student.getId());
            // key 是星期几，day， value是某天 已选的时间lesson_time_Id号
            Map<Integer, Set<Integer>> map = new HashMap<>();
            for (Choose_Course chooseCourse : chooseCourseList) {
                List<Course_Arrange> courseArrangeList = courseService.getCourseArrangeByPublishCourseId(chooseCourse.getPub_course_id());
                for (Course_Arrange courseArrange : courseArrangeList) {
                    Set<Integer> set = null;
                    logger.info("已选的课程日期有Day: " + courseArrange.getDay() + "lesson_id: " + courseArrange.getLesson_time_id());
                    if (map.containsKey(courseArrange.getDay())) {
                        set = map.get(courseArrange.getDay());
                    }
                    else {
                        set = new HashSet<>();
                    }
                    set.add(courseArrange.getLesson_time_id());
                    map.put(courseArrange.getDay(), set);
                }
            }
            boolean isSucceed = true;
            // 想选的课程时间安排
            List<Course_Arrange> curCourseArrangeList = courseService.getCourseArrangeByPublishCourseId(publishCourseId);
            for (Course_Arrange courseArrange : curCourseArrangeList) {
                Set<Integer> set = map.get(courseArrange.getDay());
                if (set != null){
                    if (set.contains(courseArrange.getLesson_time_id())) {
                        msg = "该课程时间与已选课冲突";
                        isSucceed = false;
                        break;
                    }
                }
            }

            if (isSucceed) {
                Choose_Course chooseCourse = new Choose_Course();
                chooseCourse.setPub_course_id(publishCourseId);
                chooseCourse.setStu_id(student.getId());
                courseService.addChooseCourse(chooseCourse);
            }
        }
        else {
            msg = "未登录";
        }

        model.addAttribute("msg", msg);
        return "feedback";
    }

    @RequestMapping(path = {"/myCourse"})
    public String myCourse(Model model) {
        Student student = hostHolder.getStudent();
        if (student != null) {
            List<ViewObject> vos = new ArrayList<>();
            List<Publish_Course> publishCourseList = courseService.getAllChoosePublishCourseByStudentId(student.getId());
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
            publishCourseList = publishCourseService.getAllPublishCourse(offset, 20);
        }
        else {
            publishCourseList = publishCourseService.getAllPublishCourseByCollegeId(college_id, offset, 20);
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

                List<Course_Arrange> courseArrangeList = courseService.getCourseArrangeByPublishCourseId(publishCourse.getId());
                Room room = null;
                String[] timeList = new String[6];
                for (Course_Arrange courseArrange : courseArrangeList) {
                    if (room == null) {
                        room = roomService.getRoomById(courseArrange.getRoom_id());
                    }

                    logger.info(courseArrange.getLesson_time_id() + "------");
                    Lesson_Time lessonTime = lessonTimeService.getLessonTimeById(courseArrange.getLesson_time_id());
                    int day = courseArrange.getDay();
                    if (timeList[day] == null) {
                        timeList[day] = "";
                    }
                    timeList[day] += lessonTime.getTime();
                }


                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < timeList.length; i++) {
                    if (timeList[i] == null) {
                        continue;
                    }
                    sb.append("星期");
                    sb.append(i);
                    sb.append(": ");
                    sb.append(timeList[i]);
                    sb.append("\t");
                }

                Building building = buildingService.getBuildingById(room.getBuilding_id());

                vo.set("room", room);
                vo.set("time", sb.toString());
                vo.set("mark", building.getMark());
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
