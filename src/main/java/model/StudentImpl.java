package model;

/**
 * Created by Макс on 09.04.2016.
 */
public class StudentImpl implements Student {

    private int id;
    private String name;
    private String group;

    public StudentImpl(int id, String name, String group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }
}
