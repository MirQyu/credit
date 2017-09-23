package qyu.shanda.dao;

import org.apache.ibatis.annotations.*;
import qyu.shanda.model.Choose_Course;

import java.util.List;

/**
 * Created by MirQ on 17/9/22.
 */
@Mapper
public interface ChooseCourseDAO {
    String TABLE_NAME = " choose_course ";
    String INSERT_FIELDS = " stu_id, pub_course_id ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
             ") values (#{stu_id}, #{pub_course_id})"})
    int addChooseCourse(Choose_Course chooseCourse); // 选课

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);    //退课

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Choose_Course selectById(int id);

    // 选出某位同学已经选的所有课程
    @Select({"select", INSERT_FIELDS, " from ", TABLE_NAME, " where stu_id=#{stu_id}"})
    List<Choose_Course> getAllChooseCourseByStudentId(@Param("stu_id") int stu_id);
}
