package qyu.shanda.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qyu.shanda.model.Course;
import qyu.shanda.model.Room;

import java.util.List;

/**
 * Created by MirQ on 17/9/22.
 */
@Mapper
public interface RoomDAO {
    String TABLE_NAME = " room ";
    String INSERT_FIELDS = " num, building_id, capacity ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
             ") values (#{num},#{building_id},#{capacity})"})
    int addRoom(Room room);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Room selectById(int id);

    @Select({"select", INSERT_FIELDS, " from ", TABLE_NAME, " limit #{offset}, #{limit}"})
    List<Room> selectAllPublishCourse(@Param("offset") int offset, @Param("limit") int limit);

}
