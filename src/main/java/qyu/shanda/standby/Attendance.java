package qyu.shanda.standby;

import java.util.Date;

/**
 * Created by MirQ on 17/9/22.
 */
public class Attendance {
    private int id;
    private int pub_course_id;
    private Date date;
    private int stu_id;
    private String reason;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
