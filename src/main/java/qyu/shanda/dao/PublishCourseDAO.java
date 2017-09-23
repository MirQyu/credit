package qyu.shanda.dao;

import org.apache.ibatis.annotations.*;
import qyu.shanda.model.Course;
import qyu.shanda.model.Publish_Course;

import java.util.List;

/**
 * Created by MirQ on 17/9/22.
 */
@Mapper
public interface PublishCourseDAO {
    String TABLE_NAME = " publish_course ";
    String INSERT_FIELDS = " course_id, tea_id, exp_num, real_num ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{course_id},#{tea_id},#{exp_num},#{real_num})"})
    int addPublishCourse(Publish_Course publishCourse);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Publish_Course selectById(int id);

    @Update({"update ", TABLE_NAME, " set tea_id=#{tea_id},exp_num=#{exp_num},real_num=#{real_num} where id=#{id}"})
    void updatePublishCourse(Publish_Course publishCourse);

    // 查看所有开课 班级
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " limit #{offset}, #{limit}"})
    List<Publish_Course> selectAllPublishCourse(@Param("offset") int offset, @Param("limit") int limit);

    // 查看某个学院开设的所有课程
    List<Publish_Course> selectAllPublishCourseByCollegeId(
            @Param("college_id") int id,
            @Param("offset") int offset,
            @Param("limit") int limit);
}
