import database.ConfigDB;
import entity.Physician;
import model.PhysicianModel;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PhysicianModel physicianModel = (PhysicianModel) new PhysicianModel().create(new Physician("prueba", "prueba", 1));
        System.out.println(physicianModel.findAll().toString());
    }
}