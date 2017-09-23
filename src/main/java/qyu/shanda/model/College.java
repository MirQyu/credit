package qyu.shanda.model;

/**
 * Created by MirQ on 17/9/24.
 */
public class College {
    private int id;
    private String name;
    private int dean_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDean_id() {
        return dean_id;
    }

    public void setDean_id(int dean_id) {
        this.dean_id = dean_id;
    }
}
