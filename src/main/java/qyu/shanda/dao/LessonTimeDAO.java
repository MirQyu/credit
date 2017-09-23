package qyu.shanda.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import qyu.shanda.model.Lesson_Time;
import qyu.shanda.model.Room;

/**
 * Created by MirQ on 17/9/23.
 */
@Mapper
public interface LessonTimeDAO {
    String TABLE_NAME = " lesson_time ";
    String INSERT_FIELDS = " time ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{time})"})
    int addLessonTime(Lesson_Time lessonTime);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Lesson_Time selectById(int id);
}
