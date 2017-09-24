package qyu.shanda.dao;

import org.apache.ibatis.annotations.*;
import qyu.shanda.model.Course;
import qyu.shanda.model.Publish_Course;

import java.util.List;

/**
 * Created by MirQ on 17/9/22.
 */
@Mapper
public interface CourseDAO {
    String TABLE_NAME = " course ";
    String INSERT_FIELDS = " name,college_id,necessary,syllabus,credit ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{name},#{college_id},#{necessary},#{syllabus},#{credit})"})
    int addCourse(Course course);

//    @Update({"update ", TABLE_NAME, " set name=#{name}, college_id=#{college_id}," +
//             "necessary=#{necessary},syllabus=#{syllabus},credit=#{credit} where id=#{id}"})
//    void updateCourse(Course course);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, "where id=#{id}"})
    Course selectCourseById(@Param("id") int id);

    //查看所有 课程
    @Select({"select", SELECT_FIELDS, " from ", TABLE_NAME, " limit #{offset}, #{limit}"})
    List<Course> selectAllPublishCourse(@Param("offset") int offset, @Param("limit") int limit);

}
