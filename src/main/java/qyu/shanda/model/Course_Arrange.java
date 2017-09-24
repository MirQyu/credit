package qyu.shanda.model;

import java.util.Date;

/**
 * Created by MirQ on 17/9/22.
 */
public class Course_Arrange {
    private int id;
    private int room_id;
    private int pub_course_id;
    private int day;        // 周几 被选了 [1...5]
    private int lesson_time_id; //一天中的那个时间段 被选了

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getPub_course_id() {
        return pub_course_id;
    }

    public void setPub_course_id(int pub_course_id) {
        this.pub_course_id = pub_course_id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getLesson_time_id() {
        return lesson_time_id;
    }

    public void setLesson_time_id(int lesson_time_id) {
        this.lesson_time_id = lesson_time_id;
    }
}
