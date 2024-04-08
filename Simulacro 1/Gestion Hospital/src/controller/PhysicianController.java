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
        StringBuilder List = getAll((instanceModel().findAll()));
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
}
