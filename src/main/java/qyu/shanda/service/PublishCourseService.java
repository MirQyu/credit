package qyu.shanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qyu.shanda.dao.PublishCourseDAO;
import qyu.shanda.model.Publish_Course;

import java.util.List;

/**
 * Created by MirQ on 17/9/24.
 */
@Service
public class PublishCourseService {

    @Autowired
    PublishCourseDAO publishCourseDAO;

    public List<Publish_Course> getAllPublishCourse(int offset, int limit) {
        return publishCourseDAO.selectAllPublishCourse(offset, limit);
    }

    public List<Publish_Course> getAllPublishCourseByCollegeId(int college_id, int offset, int limit) {
        return publishCourseDAO.selectAllPublishCourseByCollegeId(college_id, offset, limit);
    }
}
