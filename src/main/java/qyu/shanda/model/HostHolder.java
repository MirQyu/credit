package qyu.shanda.model;

import org.springframework.stereotype.Component;

/**
 * Created by MirQ on 17/9/24.
 */
@Component
public class HostHolder {
    private ThreadLocal<Student> students = new ThreadLocal<>();

    public void setStudent(Student student) {
        students.set(student);
    }

    public Student getStudent() {
        return students.get();
    }

    public void clear() {
        students.remove();
    }
}
