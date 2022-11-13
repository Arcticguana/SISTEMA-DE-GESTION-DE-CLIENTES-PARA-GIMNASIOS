package clientes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Cliente {
    private String cedula;
    private String nombres;
    private String apellidos;
    private LocalDate fecha;
    private char sexo;
    private String telefono;
    private String nombreContacto;
    private String telefonoContacto;
    private String correoElectronico;
    private String direccion;

    public Cliente(String cedula, String nombres, String apellidos, LocalDate fecha, char sexo, String telefono, String nombreContacto, String telefonoContacto, String correoElectronico, String direccion) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha = fecha;
        this.sexo = sexo;
        this.telefono = telefono;
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
    }

    public Cliente(String cedula, String nombres, String apellidos, String fecha, char sexo, String telefono, String nombreContacto, String telefonoContacto, String correoElectronico, String direccion) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.sexo = sexo;
        this.telefono = telefono;
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public char getSexo() {
        return sexo;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente{\n" +
                "   cedula='" + cedula + "'\n" +
                "   nombres='" + nombres + "'\n" +
                "   apellidos='" + apellidos + "'\n" +
                "   fecha=" + fecha + "\n" +
                "   sexo=" + sexo + "'\n" +
                "   telefono='" + telefono + "'\n" +
                "   nombreContacto='" + nombreContacto + "'\n" +
                "   telefonoContacto='" + telefonoContacto + "'\n" +
                "   correoElectronico='" + correoElectronico + "'\n" +
                "   direccion='" + direccion + "'\n" +
                '}';
    }
}
