package qyu.shanda.standby;

/**
 * Created by MirQ on 17/9/22.
 */
public class Grade {
    private int id;
    private int choose_course_id;      //
    private int homework_grade;
    private double homework_rate;
    private int perform;
    private double perform_rate;
    private int exam_grade; // 考试成绩
    private int project_grade;
    private int exp_grade;  // 实验成绩
    private int total;      // 总成绩

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChoose_course_id() {
        return choose_course_id;
    }

    public void setChoose_course_id(int choose_course_id) {
        this.choose_course_id = choose_course_id;
    }

    public int getHomework_grade() {
        return homework_grade;
    }

    public void setHomework_grade(int homework_grade) {
        this.homework_grade = homework_grade;
    }

    public double getHomework_rate() {
        return homework_rate;
    }

    public void setHomework_rate(double homework_rate) {
        this.homework_rate = homework_rate;
    }

    public int getPerform() {
        return perform;
    }

    public void setPerform(int perform) {
        this.perform = perform;
    }

    public double getPerform_rate() {
        return perform_rate;
    }

    public void setPerform_rate(double perform_rate) {
        this.perform_rate = perform_rate;
    }

    public int getExam_grade() {
        return exam_grade;
    }

    public void setExam_grade(int exam_grade) {
        this.exam_grade = exam_grade;
    }

    public int getProject_grade() {
        return project_grade;
    }

    public void setProject_grade(int project_grade) {
        this.project_grade = project_grade;
    }

    public int getExp_grade() {
        return exp_grade;
    }

    public void setExp_grade(int exp_grade) {
        this.exp_grade = exp_grade;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
