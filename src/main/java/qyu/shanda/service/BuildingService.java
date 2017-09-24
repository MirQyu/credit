package qyu.shanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qyu.shanda.dao.BuildingDAO;
import qyu.shanda.model.Building;

/**
 * Created by MirQ on 17/9/25.
 */
@Service
public class BuildingService {

    @Autowired
    BuildingDAO buildingDAO;

    public Building getBuildingById(int id) {
        return buildingDAO.selectById(id);
    }
}
