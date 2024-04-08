package Controller;

import database.CRUD;
import entity.Airplane;
import model.AirplaneModel;

import javax.swing.*;
import java.util.List;

public class AirplaneController {

    public AirplaneModel instanceModel() {
        return new AirplaneModel();
    }
    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder list = new StringBuilder("AIRPLANES REGISTERED:\n");
        if (objectsList.isEmpty()) {
            list.append("There is no airplanes yet");
        } else {
            for (Object obj : objectsList) {
                Airplane objAirplane = (Airplane) obj;
                list.append(objAirplane.toString()).append("\n");
            }
        }
        return list;
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll((instanceModel().findAll())));
    }
    public void create() {
        String model = JOptionPane.showInputDialog(null, "Enter model of the new airplane");
        int capacity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the capacity of the new airplane"));
        instanceModel().create(new Airplane(model,capacity));
    }

    public void delete() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Airplane selectedOption = (Airplane) JOptionPane.showInputDialog(
                    null,
                    "Select the airplane that you want to delete:\n",
                    "Deleting a airplane",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this airplane?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "airplane successfully removed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't delete the airplane");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No airplane was deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no airplane yet");
        }

    }

    public void update() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Airplane selectedOption = (Airplane) JOptionPane.showInputDialog(
                    null,
                    "Select the airplane that you want to update:\n",
                    "Updating a airplane",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to update this airplane?");
                if (confirm == 0) {
                    String model = JOptionPane.showInputDialog(null, "Enter the new model of the airplane", selectedOption.getModel());
                    int capacity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the new capacity of the airplane"));
                    selectedOption.setModel(model);
                    selectedOption.setCapacity(capacity);
                    if (instanceModel().update(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Airplane information successfully updated");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't update the airplane:");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"data update cancelled");
                }
            }
        }
    }



}
