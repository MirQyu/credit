package qyu.shanda.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import qyu.shanda.model.College;
import qyu.shanda.model.Course_Arrange;

import java.util.List;

/**
 * Created by MirQ on 17/9/24.
 */
@Mapper
public interface CourseArrangeDAO {
    String TABLE_NAME = " course_arrange ";
    String INSERT_FIELDS = " room_id, pub_course_id, day, lesson_time_id ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{room_id},#{pub_course_id},${day},#{lesson_time_id})"})
    int addCourseArrange(Course_Arrange courseArrange);

    @Select({"select " + SELECT_FIELDS + " from " + TABLE_NAME + " where pub_course_id=#{pub_course_id}"})
    List<Course_Arrange> selectByPublishCourseId(int pub_course_id);

    @Select({"select " + SELECT_FIELDS + " from " + TABLE_NAME + " where room_id=#{room_id}"})
    List<Course_Arrange> selectByRoomId(int room_id);
}
