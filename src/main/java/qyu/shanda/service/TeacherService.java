package qyu.shanda.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qyu.shanda.dao.StudentDAO;
import qyu.shanda.dao.TeacherDAO;
import qyu.shanda.model.Student;
import qyu.shanda.model.Teacher;

/**
 * Created by MirQ on 17/9/23.
 */
@Service
public class TeacherService {

    private static Logger logger = LoggerFactory.getLogger(TeacherService.class);

    @Autowired
    TeacherDAO teacherDAO;

    public Teacher getStudent(int id) {
        return teacherDAO.selectById(id);
    }
}
