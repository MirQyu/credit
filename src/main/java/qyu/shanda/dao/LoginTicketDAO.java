package qyu.shanda.dao;

import org.apache.ibatis.annotations.*;
import qyu.shanda.model.LoginTicket;

/**
 * Created by MirQ on 17/9/24.
 */
@Mapper
public interface LoginTicketDAO {
    String TABLE_NAME = " login_ticket ";
    String INSERT_FIELDS = " stu_id, expired, status, ticket ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDS, ")",
            "values (#{stu_id}, #{expired}, #{status}, #{ticket})"})
    int addTicket(LoginTicket ticket);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME, "where ticket=#{ticket}"})
    LoginTicket selectByTicket(String ticket);

    @Update({"update", TABLE_NAME, "set status=#{status} where ticket=#{ticket}"})
    boolean updateStatus(@Param("ticket") String ticket, @Param("status") int staus);
}
