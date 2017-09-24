package qyu.shanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qyu.shanda.dao.CollegeDAO;
import qyu.shanda.model.College;

import java.util.List;

/**
 * Created by MirQ on 17/9/24.
 */
@Service
public class CollegeService {
    @Autowired
    CollegeDAO collegeDAO;

    public College getCollegeById(int id) {
        return collegeDAO.selectById(id);
    }

    public List<College> getAllCollege() {
        return collegeDAO.selectAllCollege();
    }
}
