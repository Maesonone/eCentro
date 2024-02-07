
import java.util.List;

import ecentro.Alumno;
import ecentro.Grupo;
import ecentro.Identifier;
import ecentro.Model;
import ecentro.Usuario;
import ecentro.UsuariosModel;


public class App {

    public static int menuA(){
        // menu de acceso
        System.out.println("---------------------------------------");
        System.out.println("|         BIENVENIDO AL GESTOR        |");
        System.out.println("---------------------------------------");
        System.out.println("1. Acceder.");
        System.out.println("2. Registrar.");
        System.out.println("3. Salir.");
        int opcion = Integer.parseInt(System.console().readLine());
        return opcion;
    }

    public static int menuP(){
        // menu principal
        System.out.println("1. Añadir grupo.");
        System.out.println("2. Listar grupo.");
        System.out.println("3. Consultar grupo.");
        System.out.println("4. Borrar grupo.");
        System.out.println("5. Añadir alumno.");
        System.out.println("6. Listar alumnos.");
        System.out.println("7. Borrar alumno.");
        System.out.println("8. Salir.");
        int opcion = Integer.parseInt(System.console().readLine());
        return opcion;
    }

    public static int menuG(){
        // menu de grupo
        System.out.println("1. Añadir alumno.");
        System.out.println("2. Eliminar alumno.");
        System.out.println("3. Listar alumnos.");
        System.out.println("4. Atrás.");
        int opcion = Integer.parseInt(System.console().readLine());
        return opcion;
    }

    public static Usuario SingIn(UsuariosModel usuarios){
        // proceso de registro
        System.out.println("---------------------------------------");
        System.out.println("|               REGISTER              |");
        System.out.println("---------------------------------------");
        System.out.println("Introduzca su nombre: ");
        String nombre = System.console().readLine();
        System.out.println("Introduzca sus apellidos: ");
        String apellidos = System.console().readLine();
        System.out.println("Introduzca su correo electrónico: ");
        String email = System.console().readLine();
        System.out.println("Introduzca su contraseña: ");
        String password = System.console().readLine();
        System.out.println("Repita su contraseña: ");
        String password2 = System.console().readLine();

        if (password.compareTo(password2)==0) {
            Usuario usu = (Usuario)usuarios.add(new Usuario("", nombre, apellidos, email, password2));
            return usu;
        }else{
            System.out.println("Las contraseñas no son iguales, vuelva a intentarlo.");
            SingIn(usuarios);
            return null;
        }
    }

    public static void LogIn(UsuariosModel usuarios){
        // proceso de acceso
        boolean salir = false;
        while(!salir){
        System.out.println("---------------------------------------");
        System.out.println("|                LOG IN               |");
        System.out.println("---------------------------------------");
        System.out.println("Introduzca su email: ");
        String email = System.console().readLine();
        System.out.println("Introduzca su contraseña: ");
        String password = System.console().readLine();
        
            if(usuarios.matchPassword(email, password)){
                System.out.println("Perfecto, puede pasar.");
                salir = true;
            }else{
                System.out.println("Lo siento, algo ha ido mal.");
                salir = false;
            }
        }
    }

    public static Grupo nuevoG(Model grupos){
        // crea un nuevo grupo
        System.out.println("Introduzca el nombre del grupo: ");
        String nombre = System.console().readLine();
        Grupo grupo = (Grupo) grupos.add(new Grupo("", nombre));
        return grupo;
    }

    public static Grupo deleteG(Model grupos){
        // borra un grupo
        System.out.println("Introduzca el id del grupo: ");
        String id = System.console().readLine();
        Grupo grupo = (Grupo) grupos.delete(id);
        return grupo;
    }

    public static Alumno nuevoA(Model alumnos){
        // crea un nuevo alumno
        System.out.println("Introduzca el nombre: ");
        String nombre = System.console().readLine();
        System.out.println("Introduzca los apellidos: ");
        String apellidos = System.console().readLine();
        Alumno alumno = (Alumno)alumnos.add(new Alumno("", nombre, apellidos, ""));
        return alumno;
    }

    public static Alumno deleteA(Model alumnos){
        // borra un alumno
        System.out.println("Introduzca el id del alumno: ");
        String id = System.console().readLine();
        Alumno alumno = (Alumno) alumnos.delete(id);
        return alumno;
    }

    public static Model listarG(Model grupos){
        // lista los grupos creados
        for(Identifier a : grupos.query()){
            System.out.println(a);
        }
        return grupos;
    }

    public static Model listarA(Model alumnos){
        // lista los alumnos creados
        for(Identifier a : alumnos.query()){
            System.out.println(a);
        }
        return alumnos;
    }

    public static void anadeAlumno(Model grupos, Model alumnos){
        // añade un alumno a un grupo
        listarG(grupos);
        System.out.println("Introduzca el id del grupo al que quiere añadir un alumno: ");
        String idG = System.console().readLine();
        listarA(alumnos);
        System.out.println("Introduzca el id del alumno que quiere añadir al grupo "+idG+": ");
        String idA = System.console().readLine();
        Grupo grupo = (Grupo)grupos.get(idG);
        grupo.anadeAlumno((Alumno)alumnos.get(idA));
    }

    public static void eliminAlumno(Model grupos, Model alumnos){
        // elimina un alumno de un grupo
        listarG(grupos);
            System.out.println("Introduce el id del grupo a listar: ");
            String idG = System.console().readLine();

            Grupo grupo = (Grupo) grupos.get(idG);
            if (grupo != null) {
                System.out.println("Alumnos del grupo " + grupo.getNombre() + ":");
                List<Alumno> alumnosDelGrupo = grupo.getAlumnos();
                for (Alumno alumno : alumnosDelGrupo) {
                    System.out.println(alumno);
                }
                System.out.println("Introduzca el id del alumno a eliminar: ");
                String idA = System.console().readLine();
                grupo.borraAlumno((Alumno)alumnos.get(idA));
            } else {
                System.out.println("No se encontró ningún grupo con el id proporcionado.");
            }

    }


    public static void listarAG(Model grupos, Model alumnos) {
        // lista los alumnos de un grupo
            listarG(grupos);
            System.out.println("Introduce el id del grupo a listar: ");
            String idG = System.console().readLine();

            Grupo grupo = (Grupo) grupos.get(idG);
            if (grupo != null) {
                System.out.println("Alumnos del grupo " + grupo.getNombre() + ":");
                List<Alumno> alumnosDelGrupo = grupo.getAlumnos();
                for (Alumno alumno : alumnosDelGrupo) {
                    System.out.println(alumno);
                }
            } else {
                System.out.println("No se encontró ningún grupo con el id proporcionado.");
            }
        }


    public static void accedido(Model grupos, Model alumnos){
        switch (menuP()) {
            case 1:
                System.out.println("-----------------------------------------");
                nuevoG(grupos);
                System.out.println("-----------------------------------------");
                accedido(grupos, alumnos);
                break;
            
            case 2:
                System.out.println("-----------------------------------------");
                listarG(grupos);
                System.out.println("-----------------------------------------");
                accedido(grupos, alumnos);
                break;

            case 3:
                System.out.println("-----------------------------------------");
                switch (menuG()) {
                    case 1:
                        System.out.println("-----------------------------------------");
                        anadeAlumno(grupos, alumnos);
                        System.out.println("-----------------------------------------");
                        accedido(grupos, alumnos);
                        break;
                    
                    case 2:
                        System.out.println("-----------------------------------------");
                        eliminAlumno(grupos, alumnos);
                        System.out.println("-----------------------------------------");
                        accedido(grupos, alumnos);
                        break;
                    
                    case 3:
                        System.out.println("-----------------------------------------");
                        listarAG(grupos, alumnos);
                        System.out.println("-----------------------------------------");
                        accedido(grupos, alumnos);
                        break;
                    
                    case 4:
                        System.out.println("-----------------------------------------");
                        accedido(grupos, alumnos);
                        break;
                }
                break;

            case 4:
                System.out.println("-----------------------------------------");
                deleteG(grupos);
                System.out.println("-----------------------------------------");
                accedido(grupos, alumnos);
                break;

            case 5:
                System.out.println("-----------------------------------------");
                nuevoA(alumnos);
                System.out.println("-----------------------------------------");
                accedido(grupos, alumnos);
                break;

            case 6:
                System.out.println("-----------------------------------------");
                listarA(alumnos);
                System.out.println("-----------------------------------------");
                accedido(grupos, alumnos);
                break;

            case 7:
                System.out.println("-----------------------------------------");
                deleteA(alumnos);
                System.out.println("-----------------------------------------");
                accedido(grupos, alumnos);
                break;

            case 8:
                
                break;
            
            default:
                System.out.println("-----------------------------------------");
                System.out.println("Opción incorrecta.");
                System.out.println("-----------------------------------------");
                accedido(grupos, alumnos);
                break;
        }
    }


    public static void main(String[] args) throws Exception {
        UsuariosModel usuarios = new UsuariosModel();
        Model alumnos = new Model();
        Model grupos = new Model();
        boolean salir = false;
        while (!salir) {
            switch (menuA()) {
                case 1:
                    try {
                        LogIn(usuarios);
                        accedido(grupos, alumnos);
                    } catch (Exception e) {
                        System.out.println("Lo siento, algo ha ido mal.");
                        salir = false;
                    }

                    break;
                case 2:
                    SingIn(usuarios);
                    salir = false;
                    break;
    
                case 3:
                    System.out.println("Gracias por utilizar este gestor.");
                    salir = true;
                    break;
                
                default:
                    System.out.println("Opción no válida.");
                    salir = false;
                    break;
            }
        }
    }
}
