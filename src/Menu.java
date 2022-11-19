import basededatos.ConexionBaseDatos;
import clientes.Cliente;
import clientes.ControladorCliente;
import clientes.excepciones.ErrorCedula;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private ControladorCliente controladorCliente;
    private boolean continueRunning;
    public Menu() {
        this.scanner = new Scanner(System.in);
        ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos("jdbc:sqlite:gimnasio.db");
        this.controladorCliente = new ControladorCliente(conexionBaseDatos);
        this.continueRunning = true;
    }

    public void mostrarMenuPrincipal(){
        String menu = "--------------------------------------------\n" +
                "SISTEMA DE GESTIÓN DE CLIENTES PARA GIMNASIO\n"+
                " 1.- Consultar cliente\n" +
                " 2.- Registrar cliente\n" +
                " 0.- Salir\n" +
                "Ingresa la opción deseada >>> ";

        while(continueRunning){
            System.out.print(menu);
            int op = scanner.nextInt();
            System.out.println("---------------------------------");
            switch (op){
                case 0:
                    salir();
                    break;
                case 1:
                    mostrarConsultaCliente();
                    break;
                case 2:
                    mostrarRegistroCliente();
                    break;
            }
        }



    }

    private void mostrarRegistroCliente() {
        scanner = new Scanner(System.in);
        String cedula = leerEntrada("Cédula >>> ");
        while (true){
            try{
                Cliente.validarCedula(cedula);
                break;
            }catch (ErrorCedula errorCedula){
                System.out.println(errorCedula.getMessage());
            }
        }
        String nombres = leerEntrada("Nombres >>> ");
        String apellidos = leerEntrada("Apellidos >>> ");
        String fecha = leerEntrada("Fecha de nacimiento (dd-mm-aaaa)>>> ");
        String sexo = leerEntrada("Sexo (M o F) >>> ");
        String telefono = leerEntrada("Teléfono >>> ");
        String correoElectronico = leerEntrada("Correo electrónico >>> ");
        String direccion = leerEntrada("Dirección >>> ");
        String nombreContacto = leerEntrada("Nombre de contacto >>> ");
        String telefonoContacto = leerEntrada("Teléfono de contacto >>> ");
        Cliente cliente = new Cliente(
                cedula,
                nombres,
                apellidos,
                fecha,
                sexo.charAt(0),
                telefono,
                nombreContacto,
                telefonoContacto,
                correoElectronico,
                direccion
        );
        try {
            controladorCliente.registrarCliente(cliente);
            System.out.println("Cliente registrado exitosamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }

    private void mostrarConsultaCliente() {
        scanner = new Scanner(System.in);
        String mensaje = "Ingresa la cédula del cliente que deseas consultar\nCédula >>> ";
        String cedula = null;

        try{
            cedula = leerEntrada(mensaje);
            Cliente.validarCedula(cedula);
        }catch (ErrorCedula errorCedula){
                System.out.println(errorCedula.getMessage());
                return;
        }

        Cliente cliente = null;
        try {
            cliente = controladorCliente.consultarCliente(cedula);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (cliente == null){
            System.out.println("Cliente no encontrado");
            return;
        }
        System.out.println(cliente);
    }

    private void salir(){
        System.out.println("Adios, vuelva pronto...");
        this.continueRunning = false;
    }

    private String leerEntrada(String mensaje){
        System.out.print(mensaje);
        return scanner.nextLine();
    }
}
