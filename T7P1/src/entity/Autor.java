package entity;

public class Autor {
    private int id;
    private String name;
    private String nacionality;

    public Autor() {
    }

    public Autor(int id, String name, String nacionality) {
        this.id = id;
        this.name = name;
        this.nacionality = nacionality;
    }

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

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nacionality='" + nacionality + '\'' +
                '}';
    }
}
