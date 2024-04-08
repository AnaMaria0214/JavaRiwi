package controller;

import entity.Specialty;
import model.SpecialtyModel;

import javax.swing.*;
import java.util.List;

public class SpecialtyController {

    public SpecialtyModel instanceModel() {
        return new SpecialtyModel();
    }

    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder list = new StringBuilder("SPECIALTIES REGISTERED:\n");
        if (objectsList.isEmpty()) {
            list.append("There is no Specialties yet");
        } else {
            for (Object obj : objectsList) {
                Specialty objSpecialty = (Specialty) obj;
                list.append(objSpecialty.toString()).append("\n");
            }
        }
        return list;
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll((instanceModel().findAll())));
    }

    public void create() {
        String name = JOptionPane.showInputDialog(null, "Enter the name of the new specialty");
        String description = JOptionPane.showInputDialog(null, "Enter the description of the new specialty");
        instanceModel().create(new Specialty(name, description));
    }

    public void delete() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Specialty selectedOption = (Specialty) JOptionPane.showInputDialog(
                    null,
                    "Select the specialty that you want to delete:\n",
                    "Deleting a specialty",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this specialty?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Specialty successfully removed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't delete the specialty");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No specialty was deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no specialty yet");
        }

    }

    public void update() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Specialty selectedOption = (Specialty) JOptionPane.showInputDialog(
                    null,
                    "Select the specialty that you want to update:\n",
                    "Updating a specialty",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to update this specialty?");
                if (confirm == 0) {
                    String name = JOptionPane.showInputDialog(null, "Enter the new name of the specialty", selectedOption.getName());
                    String description = JOptionPane.showInputDialog(null, "Enter the new description of the specialty");
                    selectedOption.setName(name);
                    selectedOption.setDescription(description);
                    if (instanceModel().update(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Specialty information successfully updated");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't update the Specialty:");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"data update cancelled");
                }
            }
        }
    }


}


