package entity;

public class Airplane {
    private int id_Airplane;
    private String model;
    private int capacity;

    public Airplane() {
    }

    public Airplane(String model, int capacity) {
        this.model = model;
        this.capacity = capacity;
    }

    public int getId_Airplane() {
        return id_Airplane;
    }

    public void setId_Airplane(int id_Airplane) {
        this.id_Airplane = id_Airplane;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return
                "  - AirplaneID:" + id_Airplane +
                " Model: " + model +
                " Capacity: " + capacity ;
    }
}
