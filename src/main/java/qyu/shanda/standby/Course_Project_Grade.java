package qyu.shanda.standby;

/**
 * Created by MirQ on 17/9/22.
 */
public class Course_Project_Grade {
    private int id;
    private int course_project_id;
    private int stu_id;
    private int grade;
    private int rate; //     ???不太明白含义


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_project_id() {
        return course_project_id;
    }

    public void setCourse_project_id(int course_project_id) {
        this.course_project_id = course_project_id;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
