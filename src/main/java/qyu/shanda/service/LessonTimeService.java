package qyu.shanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qyu.shanda.dao.LessonTimeDAO;
import qyu.shanda.model.Lesson_Time;

/**
 * Created by MirQ on 17/9/24.
 */
@Service
public class LessonTimeService {

    @Autowired
    LessonTimeDAO lessonTimeDAO;

    public Lesson_Time getLessonTimeById(int id) {
        return lessonTimeDAO.selectById(id);
    }

}
