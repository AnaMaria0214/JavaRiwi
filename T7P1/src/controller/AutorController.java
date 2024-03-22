

public class AutorController {
    AutorModel objAutorModel;

    public AutorController() {
        //crear una instancia del moder
        this.objAutorModel = new AutorModel();
    }

    public void create() {
        Autor objAutor = new Autor();
        String name = JOptionPane.showInputDialog(null, "Ingrese nombre: ");
        String nationality = JOptionPane.showInputDialog(null, "Ingrese nacionalidad: ");

        objAutor.setName(name);
        objAutor.setNacionality(nationality);

        objAutor = (Autor) this.objAutorModel.crear(objAutor);

        JOptionPane.showMessageDialog(null, objAutor.toString());
    }


    public String getAll(){

        String list= this.getAll(this.objAutorModel.BuscarTodos());

        JOptionPane.showMessageDialog(null,list);

        return list;

    }


    public String getAll(List<Object> listObject) {
        String list = "List Autors\n";
        //iteramos sobre la lista que devuelve el método findAll
        for (Object obj : listObject) {
            //convertimos o casteamos el objeto tipo Object a un coder
            Autor objAutor = (Autor) obj;
            //concatenamos la información
            list += objAutor.toString() + "\n";

        }
        return list;

    }


    public void query(){
        String searchName = JOptionPane.showInputDialog(null,"name search");

        List<Autor> resultCoders = this.objAutorModel.BuscarPorNombre(searchName);

        StringBuilder authorl = new StringBuilder();
        resultCoders.forEach((author)->{
            authorl.append(author.toString()).append("\n");
        });

        JOptionPane.showMessageDialog(null,authorl.toString());

    }


    public void update(){
        //1. Listamos
        String listAutors = this.getAll(this.objAutorModel.findAll());

        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAutors+"\nIngresa el ID del autor a editar"));

        //3. verificamos el id
        Autor objAutor = (Autor) this.objAutorModel.buscarPorId(idUpdate);

        if(objAutor==null){
            JOptionPane.showMessageDialog(null, "Autor no encontrado");

        }else {
            String name= JOptionPane.showInputDialog(null,"Ingresa el nuevo nombre", objAutor.getName());
            String nationality = JOptionPane.showInputDialog(null,"Ingresa la nueva nacionalidad", objAutor.getNacionality());
            objAutor.setName(name);
            objAutor.setNacionality(nationality);

            this.objAutorModel.Actualizar(objAutor);

        }

    }


    public void delete(){
        String listAutorString = this.getAll(this.objAutorModel.buscarTodos());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAutorString+"Ingresa el ID del autor a eliminar"));

        Autor objAutor =(Autor) this.objAutorModel.buscarPorId(idDelete);

        if(objAutor== null){
            JOptionPane.showMessageDialog(null,"Autor no encontrado");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"estas seguro de eliminar este autor: \n"+objAutor.toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objAutorModel.eliminar(objAutor);
            }
        }



    }


}
s

