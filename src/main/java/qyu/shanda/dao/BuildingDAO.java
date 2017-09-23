package qyu.shanda.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import qyu.shanda.model.Building;
import qyu.shanda.model.Room;
import qyu.shanda.model.Student;

/**
 * Created by MirQ on 17/9/23.
 */

@Mapper
public interface BuildingDAO {
    String TABLE_NAME = " building ";
    String INSERT_FIELDS = " label, mark ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;


    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{label},#{mark})"})
    int addBuilding(Building building);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Building selectById(int id);

}
