package Controller;

import entity.Airplane;
import entity.Flight;
import model.AirplaneModel;
import model.FlightModel;

import javax.swing.*;
import java.sql.Time;
import java.sql.Date;
import java.util.List;

public class FlightController {
    public FlightModel instanceModel() {
        return new FlightModel();
    }

    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder list = new StringBuilder("FLIGHTS REGISTERED:\n");
        if (objectsList.isEmpty()) {
            list.append("There is no flight yet");
        } else {
            for (Object obj : objectsList) {
                Flight objFlight = (Flight) obj;
                list.append(objFlight.toString()).append("\n");
            }
        }
        return list;
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll((instanceModel().findAll())));
    }

    public void create() {
        String destination = JOptionPane.showInputDialog(null, "Enter destination of the new flight");
        Date departure_Date = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the departure date of the new flight"));
        Time departure_Time = Time.valueOf(JOptionPane.showInputDialog(null, "Enter the departure time of the new flight"));
        Object[] options = new AirplaneModel().findAll().toArray();
        if (options.length > 0) {
            Flight selectedOption = (Flight) JOptionPane.showInputDialog(
                    null,
                    "Select the airplane of the new flight:\n",
                    "Select a airplane",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                instanceModel().create(new Flight(destination, departure_Date, departure_Time,selectedOption.getId_Airplane()));
            }
        } else {
            JOptionPane.showMessageDialog(null, "No airplanes registered yet");
        }

    }

    public void delete() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Flight selectedOption = (Flight) JOptionPane.showInputDialog(
                    null,
                    "Select the flight that you want to delete:\n",
                    "Deleting a flight",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this flight?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Flight successfully removed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't delete the flight");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No flight was deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no flight yet");
        }
    }

    public void update() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Flight selectedOption = (Flight) JOptionPane.showInputDialog(
                    null,
                    "Select the flight that you want to update:\n",
                    "Updating a flight",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to update this flight?");
                if (confirm == 0) {
                    String destination = JOptionPane.showInputDialog(null, "Enter the new destination of the flight",selectedOption.getDestination());
                    Date departure_Date = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the departure date of the new flight",selectedOption.getDeparture_Date()));
                    Time departure_Time = Time.valueOf(JOptionPane.showInputDialog(null, "Enter the departure time of the new flight",selectedOption.getDeparture_Time()));
                    Object[] optionsID = new AirplaneModel().findAll().toArray();
                    if (options.length > 0) {
                        Flight selectedOptionID = (Flight) JOptionPane.showInputDialog(
                                null,
                                "Select the new airplane of the  flight:\n",
                                "Select a new airplane",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                optionsID,
                                optionsID[0]);
                        if (selectedOptionID == null) {
                            JOptionPane.showMessageDialog(null, "No option selected");
                        } else {
                            selectedOption.setDestination(destination);
                            selectedOption.setDeparture_Date(departure_Date);
                            selectedOption.setDeparture_Time(departure_Time);
                            selectedOption.setId_Airplane(selectedOptionID.getId_Airplane());

                            if (instanceModel().update(selectedOption)) {
                                JOptionPane.showMessageDialog(null, "Flight Updated successfully");
                            } else {
                                JOptionPane.showMessageDialog(null, "Couldn't update the Flight");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "data update cancelled");
                    }
                }
            }
        }
    }
}