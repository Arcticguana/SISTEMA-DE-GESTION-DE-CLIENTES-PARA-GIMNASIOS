package clientes;

import basededatos.ConexionBaseDatos;
import basededatos.SQLTable;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class ControladorCliente {
    //a
    private ConexionBaseDatos conexion;

    public ControladorCliente(ConexionBaseDatos conexion) {
        this.conexion = conexion;
    }

    public void registrarCliente(Cliente cliente){

       String cedula = cliente.getCedula();
       String nombres = cliente.getNombres();
       String apellidos = cliente.getApellidos();
       LocalDate fecha = cliente.getFecha();
       String fechaFormateada = fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
       char sexo = cliente.getSexo();
       String telefono=cliente.getTelefono();
       String nombreContacto = cliente.getNombreContacto();
       String telefonoContacto = cliente.getTelefonoContacto();
       String correoElectronico = cliente.getCorreoElectronico();
       String direccion = cliente.getDireccion();

       String formato = "INSERT INTO clientes VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
       String sentencia = String.format(formato, cedula, nombres, apellidos, sexo, fechaFormateada, correoElectronico, telefono, nombreContacto, telefonoContacto, direccion);
       try{
           conexion.ejecutarSentencia(sentencia);
       }catch (SQLException ex){
           System.out.println(ex.getMessage());
       }

    }

    public Cliente consultarCliente(String cedula){
        try{
            SQLTable resultado = conexion.realizarConsulta("select * from clientes where cedula="+cedula);
            String nombres = resultado.getValueAt(0,"nombres");
            String apellidos = resultado.getValueAt(0,"apellidos");
            LocalDate fecha = LocalDate.parse(resultado.getValueAt(0,"fecha_nacimiento"), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            char sexo = resultado.getValueAt(0,"sexo").charAt(0);
            String telefono = resultado.getValueAt(0,"telefono");
            String nombreContacto = resultado.getValueAt(0,"nombre_contacto");
            String telefonoContacto = resultado.getValueAt(0,"telefono_contacto");
            String correoElectronico = resultado.getValueAt(0,"correo_electronico");
            String direccion = resultado.getValueAt(0,"direccion");

            Cliente cliente = new Cliente(cedula,nombres,apellidos,fecha,sexo,telefono,nombreContacto,telefonoContacto,correoElectronico,direccion);

            return cliente;

        }catch (Exception ex){
            return null;
        }
    }
}
