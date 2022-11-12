package clientes;

import basededatos.ConexionBaseDatos;
import basededatos.SQLTable;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
       Date fecha = cliente.getFecha();
       char sexo = cliente.getSexo();
       String telefono=cliente.getTelefono();
       String nombreContacto = cliente.getNombreContacto();
       String telefonoContacto = cliente.getTelefonoContacto();
       String correoElectronico = cliente.getCorreoElectronico();
       String direccion = cliente.getDireccion();



       String sentencia="INSERT INTO clientes("+cedula+","+nombres+","+apellidos+","+fecha+","+telefono+","+ sexo+","+ nombreContacto+","+ telefonoContacto+","+ correoElectronico+","+direccion+")";

       try{
           conexion.ejecutarSentencia(sentencia);
       }catch (SQLException ex){
           System.out.println(ex.getMessage());
       }


    }

    public Cliente consultarCliente(String cedula){

        try{
            SQLTable resultado=conexion.realizarConsulta("");
            String nombres = resultado.getValueAt(0,0);
            String apellidos = resultado.getValueAt(0,1);
            Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(resultado.getValueAt(0,2));
            char sexo = resultado.getValueAt(0,3).charAt(0);
            String telefono = resultado.getValueAt(0,4);
            String nombreContacto = resultado.getValueAt(0,5);
            String telefonoContacto = resultado.getValueAt(0,6);
            String correoElectronico = resultado.getValueAt(0,7);
            String direccion = resultado.getValueAt(0,8);

            Cliente cliente=new Cliente(cedula,nombres,apellidos,fecha,sexo,telefono,nombreContacto,telefonoContacto,correoElectronico,direccion);

            return cliente;

        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }


    }
}
