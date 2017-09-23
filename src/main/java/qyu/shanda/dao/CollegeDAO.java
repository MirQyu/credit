package qyu.shanda.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import qyu.shanda.model.College;

/**
 * Created by MirQ on 17/9/24.
 */
@Mapper
public interface CollegeDAO {
    String TABLE_NAME = " college ";
    String INSERT_FIELDS = " name, dean_id ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{name},#{dean_id})"})
    int addCollege(College college);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    College selectById(int id);


}
