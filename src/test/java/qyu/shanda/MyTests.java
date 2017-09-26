package qyu.shanda;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import qyu.shanda.dao.*;
import qyu.shanda.model.*;

import java.util.*;

/**
 * Created by MirQ on 17/9/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/init-schema.sql")
public class MyTests {
    private static final Logger logger =  LoggerFactory.getLogger(MyTests.class);


    @Autowired
    StudentDAO studentDAO;

    @Autowired
    TeacherDAO teacherDAO;

    @Autowired
    LessonTimeDAO lessonTimeDAO;

    @Autowired
    BuildingDAO buildingDAO;

    @Autowired
    CollegeDAO collegeDAO;

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    PublishCourseDAO publishCourseDAO;

    @Autowired
    RoomDAO roomDAO;

    @Autowired
    CourseArrangeDAO courseArrangeDAO;

    @Test
    public void test() {

        //List<Course_Arrange> list = courseArrangeDAO.selectByPublishCourseId(1);

        Set<Integer> set  = new HashSet<>();
        set.add(1);
        set.add(2);
        if (set.contains(2)) {
            logger.info("hahahaha");
        }

        //Assert.assertNull(list);

//        Publish_Course publishCourse = publishCourseDAO.selectById(2);
//        Course course = courseDAO.selectCourseById(publishCourse.getCourse_id());
//        logger.debug("name-----------", course.getName());
    }

}
