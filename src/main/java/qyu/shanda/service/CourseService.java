package qyu.shanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qyu.shanda.dao.ChooseCourseDAO;
import qyu.shanda.dao.CourseArrangeDAO;
import qyu.shanda.dao.CourseDAO;
import qyu.shanda.dao.PublishCourseDAO;
import qyu.shanda.model.Choose_Course;
import qyu.shanda.model.Course;
import qyu.shanda.model.Course_Arrange;
import qyu.shanda.model.Publish_Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MirQ on 17/9/24.
 */
@Service
public class CourseService {

    @Autowired
    ChooseCourseDAO chooseCourseDAO;

    @Autowired
    PublishCourseDAO publishCourseDAO;

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    CourseArrangeDAO courseArrangeDAO;

    public List<Publish_Course> getAllCourseByStudentId(int stu_id) {
        List<Choose_Course> choose_courses =  chooseCourseDAO.getAllChooseCourseByStudentId(stu_id);
        List<Publish_Course> res = new ArrayList<>();
        for (Choose_Course cc : choose_courses) {
            Publish_Course publish_course = publishCourseDAO.selectById(cc.getPub_course_id());
            res.add(publish_course);
        }

        return res;
    }

    public List<Course_Arrange> getByPublishCourseId(int id) {
        return courseArrangeDAO.selectByPublishCourseId(id);
    }

    public Course getCourseById(int course_id) {
        return courseDAO.selectCourseById(course_id);
    }
}
