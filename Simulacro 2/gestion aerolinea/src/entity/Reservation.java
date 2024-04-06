package entity;

import java.sql.Date;

public class Reservation {
    private int id;
    private Date departure_Date;
    private String seat;
    private int id_Passenger;
    private int id_Flight;

    public Reservation() {
    }

    public Reservation(int id, Date departure_Date, String seat, int id_Passenger, int id_Flight) {
        this.id = id;
        this.departure_Date = departure_Date;
        this.seat = seat;
        this.id_Passenger = id_Passenger;
        this.id_Flight = id_Flight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDeparture_Date() {
        return departure_Date;
    }

    public void setDeparture_Date(Date departure_Date) {
        this.departure_Date = departure_Date;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getId_Passenger() {
        return id_Passenger;
    }

    public void setId_Passenger(int id_Passenger) {
        this.id_Passenger = id_Passenger;
    }

    public int getId_Flight() {
        return id_Flight;
    }

    public void setId_Flight(int id_Flight) {
        this.id_Flight = id_Flight;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", departure_Date=" + departure_Date +
                ", seat='" + seat + '\'' +
                ", id_Passenger=" + id_Passenger +
                ", id_Flight=" + id_Flight +
                '}';
    }
}
