package qyu.shanda.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qyu.shanda.dao.ChooseCourseDAO;
import qyu.shanda.dao.PublishCourseDAO;
import qyu.shanda.dao.StudentDAO;
import qyu.shanda.model.Choose_Course;
import qyu.shanda.model.Publish_Course;
import qyu.shanda.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MirQ on 17/9/23.
 */
@Service
public class StudentService {

    private static Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentDAO studentDAO;

    public Student getStudent(int id) {
        return studentDAO.selectById(id);
    }
}
