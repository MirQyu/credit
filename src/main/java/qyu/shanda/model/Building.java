package qyu.shanda.model;

/**
 * Created by MirQ on 17/9/23.
 */
public class Building {
    private int id;
    private String label;       //教学楼名
    private String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
