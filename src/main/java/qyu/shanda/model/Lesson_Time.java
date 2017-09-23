package qyu.shanda.model;

/**
 * Created by MirQ on 17/9/23.
 */
public class Lesson_Time {
    private int id;
    private String time;   //可取组范围[1,2..9,0,A,B,C]

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
