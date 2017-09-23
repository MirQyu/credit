package qyu.shanda.model;

/**
 * Created by MirQ on 17/9/22.
 */
public class Room {
    private int id;
    private int num; //房间编号
    private int building_id;    //外键
    private int capacity;       //容量

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
