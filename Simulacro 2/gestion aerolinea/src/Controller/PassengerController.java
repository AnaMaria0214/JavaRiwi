package Controller;

import entity.Passenger;
import model.PassengerModel;

import javax.swing.*;
import java.util.List;

public class PassengerController {

    private Passenger selectedOption;

    public PassengerModel instanceModel() {
        return new PassengerModel();
    }

    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder list = new StringBuilder("PASSENGER REGISTERED:\n");
        if (objectsList.isEmpty()) {
            list.append("There is no passenger yet");
        } else {
            for (Object obj : objectsList) {
                Passenger objPatient = (Passenger) obj;
                list.append(objPatient.toString()).append("\n");
            }
        }
        return list;
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll((instanceModel().findAll())));
    }

    public void create() {
        String name = JOptionPane.showInputDialog(null, "Enter the name of the new passenger");
        String last_name = JOptionPane.showInputDialog(null, "Enter the last names of the new passenger");
        String identification  = JOptionPane.showInputDialog(null, "Enter the identification of the new passenger (no spaces, dashes or commas)");
        instanceModel().create(new Passenger(name, last_name, identification));
    }

    public void delete() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Passenger selectedOption = (Passenger) JOptionPane.showInputDialog(
                    null,
                    "Select the passenger that you want to delete:\n",
                    "Deleting a passenger",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this passenger?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Passenger successfully removed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't delete the passenger");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No passenger was deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no passenger yet");
        }

    }
    public void update() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Passenger selectedOption = (Passenger) JOptionPane.showInputDialog(
                    null,
                    "Select the passenger that you want to update:\n",
                    "Updating a passenger",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to update this passenger?");
                if (confirm == 0) {
                    String name = JOptionPane.showInputDialog(null, "Enter the new name of the passenger", selectedOption.getName());
                    String last_Name = JOptionPane.showInputDialog(null, "Enter the new last names of the passenger", selectedOption.getLast_Name());
                    String identification  =JOptionPane.showInputDialog(null, "Enter the new identification of the passenger");

                    selectedOption.setName(name);
                    selectedOption.setLast_Name(last_Name);
                    selectedOption.setIdentification(identification);
                    if (instanceModel().update(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Passenger information successfully updated");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't update the passenger:");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"data update cancelled");
                }
            }
        }
    }

}
