package qyu.shanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qyu.shanda.dao.RoomDAO;
import qyu.shanda.model.Room;

/**
 * Created by MirQ on 17/9/24.
 */
@Service
public class RoomService {

    @Autowired
    RoomDAO roomDAO;

    public Room getRoomById(int id) {
        return roomDAO.selectById(id);
    }
}
