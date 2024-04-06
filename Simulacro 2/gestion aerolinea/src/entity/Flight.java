package entity;

import java.sql.Time;
import java.sql.Date;

public class Flight {
    private int id;
    private String destination;
    private Date departure_Date;
    private Time departure_Time;
    private int id_Airplane;

    public Flight() {
    }

    public Flight(int id, String destination, Date departure_Date, Time departure_Time, int id_Airplane) {
        this.id = id;
        this.destination = destination;
        this.departure_Date = departure_Date;
        this.departure_Time = departure_Time;
        this.id_Airplane = id_Airplane;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDeparture_Date() {
        return departure_Date;
    }

    public void setDeparture_Date(Date departure_Date) {
        this.departure_Date = departure_Date;
    }

    public Time getDeparture_Time() {
        return departure_Time;
    }

    public void setDeparture_Time(Time departure_Time) {
        this.departure_Time = departure_Time;
    }

    public int getId_Airplane() {
        return id_Airplane;
    }

    public void setId_Airplane(int id_Airplane) {
        this.id_Airplane = id_Airplane;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", departure_Data=" + departure_Date +
                ", departure_Time=" + departure_Time +
                ", id_Airplane=" + id_Airplane +
                '}';
    }
}


