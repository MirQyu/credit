package qyu.shanda.dao;

import org.apache.ibatis.annotations.*;
import qyu.shanda.model.Student;


/**
 * Created by MirQ on 17/9/22.
 */
@Mapper
public interface StudentDAO {
    String TABLE_NAME = " student ";
    String INSERT_FIELDS = " name, password, sex, age, year, type, head_url";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
             ") values (#{name},#{password},#{sex},#{age},#{year},#{type},#{head_url})"})
    int addStudent(Student student);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Student selectById(int id);

//    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
//    Student selectByName(String name);

    @Update({"update ", TABLE_NAME, " set password=#{password} where id=#{id}"})
    void updatePassword(Student student);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);
}
