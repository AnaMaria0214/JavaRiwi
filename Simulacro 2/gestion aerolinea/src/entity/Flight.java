package entity;

import java.sql.Time;
import java.sql.Date;

public class Flight {
    private int id_Flights;
    private String destination;
    private Date departure_Date;
    private Time departure_Time;
    private int id_Airplane;

    public Flight() {
    }

    public Flight( String destination, Date departure_Date, Time departure_Time, int id_Airplane) {
        this.destination = destination;
        this.departure_Date = departure_Date;
        this.departure_Time = departure_Time;
        this.id_Airplane = id_Airplane;
    }

    public int getId_Flights() {
        return id_Flights;
    }

    public void setId_Flights(int id_Flights) {
        this.id_Flights = id_Flights;
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
        return
                "  - FlightID:" + id_Flights +
                " Destination: " + destination +
                " Departure_Data: " + departure_Date +
                " Departure_Time: " + departure_Time +
                " AirplaneID: " + id_Airplane ;
    }
}


