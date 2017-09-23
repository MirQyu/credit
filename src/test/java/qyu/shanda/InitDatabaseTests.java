package qyu.shanda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import qyu.shanda.dao.*;
import qyu.shanda.model.*;

import java.util.Random;

/**
 * Created by MirQ on 17/9/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/init-schema.sql")
public class InitDatabaseTests {
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

    @Test
    public void initDatabase() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setName(String.format("STU%d", i));
            student.setPassword("123456");
            student.setSex((i & 1) == 0);
            student.setAge(17 + i);
            student.setYear(2017);
            student.setType("0");
            student.setHead_url(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            studentDAO.addStudent(student);
        }

        for (int i = 0; i < 10; i++) {
            Teacher teacher = new Teacher();
            teacher.setName(String.format("STU%d", i));
            teacher.setPassword("123456");
            teacher.setSex((i & 1) == 0);
            teacher.setAge(17 + i);
            teacher.setYear(2017);
            teacher.setType("0");
            teacher.setHead_url(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            teacherDAO.addTeacher(teacher);
        }


        for (int i = 1; i < 13; i++) {
            Lesson_Time lessonTime = new Lesson_Time();

            if (i < 10) {
                lessonTime.setTime(String.valueOf(i));
            }
            else if (i == 10) {
                lessonTime.setTime(String.valueOf("A"));
            }
            else if (i == 11) {
                lessonTime.setTime(String.valueOf("B"));
            }
            else {
                lessonTime.setTime(String.valueOf("C"));
            }
            lessonTimeDAO.addLessonTime(lessonTime);
        }

        String[] build = {"E座教学楼", "D座教学楼", "G座教学楼", "C座教学楼"};

        for (int i = 0; i < build.length; i++) {
            Building building = new Building();
            building.setLabel(build[i]);
            building.setMark(String.valueOf(build[i].charAt(0)));
            buildingDAO.addBuilding(building);
        }

        String[] colleges = {"工学院", "商学院", "法学院", "医学院", "新闻学院", "艺术学院", "文学院"};
        for (String name : colleges) {
            College college = new College();
            college.setName(name);
            collegeDAO.addCollege(college);
        }


        String[] courses = {"高等数学", "英语Level-1", "英语Level-2", "英语Level-3", "英语Level-4", "英语Level-5",
                            "面向对象设计", "资本市场", "法语", "数据库原理", "操作系统", "计算机网络"};
        for (String name : courses) {
            Course course = new Course();
            course.setName(name);
            course.setCollege_id(random.nextInt(colleges.length) + 1);
            course.setCredit(random.nextInt(4) + 1);
            course.setNecessary(random.nextBoolean());
            course.setSyllabus("本课程教学大纲为：~");
            courseDAO.addCourse(course);
        }

        for (int i = 0; i < courses.length; i++) {
            Publish_Course publish_course = new Publish_Course();
            publish_course.setCourse_id(i+1);
            publish_course.setTea_id(random.nextInt(10) + 1);
            publish_course.setExp_num(50 + random.nextInt(30));

            publish_course.setReal_num(random.nextInt(80));
            publishCourseDAO.addPublishCourse(publish_course);
        }
    }
}
