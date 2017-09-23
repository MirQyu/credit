package qyu.shanda.standby;

import java.util.Date;

/**
 * Created by MirQ on 17/9/22.
 */
public class Course_Exchange {
    private int id;
    private int pub_course_id;
    private Date from_time;
    private Date to_time;
    private String old_place;
    private String new_place;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPub_course_id() {
        return pub_course_id;
    }

    public void setPub_course_id(int pub_course_id) {
        this.pub_course_id = pub_course_id;
    }

    public Date getFrom_time() {
        return from_time;
    }

    public void setFrom_time(Date from_time) {
        this.from_time = from_time;
    }

    public Date getTo_time() {
        return to_time;
    }

    public void setTo_time(Date to_time) {
        this.to_time = to_time;
    }

    public String getOld_place() {
        return old_place;
    }

    public void setOld_place(String old_place) {
        this.old_place = old_place;
    }

    public String getNew_place() {
        return new_place;
    }

    public void setNew_place(String new_place) {
        this.new_place = new_place;
    }
}
