package qyu.shanda.model;

/**
 * Created by MirQ on 17/9/22.
 */
public class Course {
    private int id;                 //课程号
    private String name;            //课程名
    private int college_id;      // 学院
    private boolean necessary;       // 必修or选修
    private int credit;             //学分
    private String syllabus;        //大纲

    @Override
    public String toString() {
        return "id:" + id + " name:" + name + " college_id:" + college_id +
                " necessary:" + necessary + " credit: " + credit + " syllabus: " + syllabus;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public int getId() {
        return id;
    }

    public boolean isNecessary() {
        return necessary;
    }

    public void setNecessary(boolean necessary) {
        this.necessary = necessary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCollege_id() {
        return college_id;
    }

    public void setCollege_id(int college_id) {
        this.college_id = college_id;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
