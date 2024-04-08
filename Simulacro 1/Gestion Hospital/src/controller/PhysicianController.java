package controller;

import entity.Physician;
import entity.Specialty;
import model.PhysicianModel;
import model.SpecialtyModel;

import javax.swing.*;
import java.util.List;

public class PhysicianController {

    public PhysicianModel instanceModel() {
        return new PhysicianModel();
    }

    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder list = new StringBuilder("PHYSICIAN REGISTERED:\n");
        if (objectsList.isEmpty()) {
            list.append("There is no physician yet");
        } else {
            for (Object obj : objectsList) {
                Physician objPhysician = (Physician) obj;
                list.append(objPhysician.toString()).append("\n");
            }
        }
        return list;
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll((instanceModel().findAll())));
    }

    public void create() {
        String name = JOptionPane.showInputDialog(null, "Enter the name of the new physician");
        String last_name = JOptionPane.showInputDialog(null, "Enter the last names of the new physician");
        Object[] options = new SpecialtyModel().findAll().toArray();
        if (options.length > 0) {
            Specialty selectedOption = (Specialty) JOptionPane.showInputDialog(
                    null,
                    "Select the specialty of the new physician:\n",
                    "Select a specialty",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                instanceModel().create(new Physician(name, last_name, selectedOption.getId_Specialty()));
            }
        } else {
            JOptionPane.showMessageDialog(null, "No specialties registered yet");
        }

    }

    public void delete() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Physician selectedOption = (Physician) JOptionPane.showInputDialog(
                    null,
                    "Select the physician that you want to delete:\n",
                    "Deleting a Physician",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this physician?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Physician successfully removed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't delete the physician");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No physician was deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no physician yet");
        }
    }

    public void update() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Physician selectedOption = (Physician) JOptionPane.showInputDialog(
                    null,
                    "Select the physician that you want to update:\n",
                    "Updating a physician",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to update this physician?");
                if (confirm == 0) {
                    String name = JOptionPane.showInputDialog(null, "Enter the new name of the physician", selectedOption.getName());
                    String last_name = JOptionPane.showInputDialog(null, "Enter the new last names of the physician", selectedOption.getLast_name());
                    Object[] optionsID =  new SpecialtyModel().findAll().toArray();
                    if (optionsID.length > 0) {
                        Specialty selectedOptionID = (Specialty) JOptionPane.showInputDialog(
                                null,
                                "Select the new specialty for the physician:\n",
                                "Updating the specialty",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                optionsID,
                                optionsID[0]);
                        if (selectedOptionID == null) {
                            JOptionPane.showMessageDialog(null, "No option selected");
                        } else {
                            selectedOption.setName(name);
                            selectedOption.setLast_name(last_name);
                            selectedOption.setId_Specialty(selectedOptionID.getId_Specialty());

                            if (instanceModel().update(selectedOption)) {
                                JOptionPane.showMessageDialog(null, "Specialty Updated successfully");
                            } else {
                                JOptionPane.showMessageDialog(null, "Couldn't update the Specialty:");
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
