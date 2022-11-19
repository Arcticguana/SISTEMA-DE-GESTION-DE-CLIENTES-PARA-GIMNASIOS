package clientes;

import clientes.excepciones.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public Cliente(String cedula, String nombres, String apellidos, LocalDate fecha, char sexo, String telefono,
                   String nombreContacto, String telefonoContacto, String correoElectronico, String direccion) {
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
    public static void validarCedula(String cedula) throws ErrorCedula{
        //Si la cédula contiene otra cosa que no sean números
        if (cedula.length() == 0) {
            throw new ErrorCedula("La cédula no puede estar vacía");
        }
        if(!cedula.matches("[0-9]+")){
            throw new ErrorCedula("La cédula solo puede contener números");
        }
        //Si la longitud de la cédula es distinta a números
        if(cedula.length() != 10){
            throw new ErrorCedula(
                    "La cédula debe tener una longitud de 10 números, se encontraron "
                            + cedula.length());
        }
        //Se revisa que los primeros números estén entre 1 y 24
        int primeros = Integer.parseInt(cedula.substring(0,2));
        if (primeros > 24 || primeros < 1){
            throw new ErrorCedula(
                    "Error en los primeros dígitos de la cédula: " + primeros);
        }
        //Se revisa que el tercer dígito no sea mayor o igual a 6
        int tercerDigito = Integer.parseInt(String.valueOf(cedula.charAt(2)));
        if (tercerDigito >= 6){
            throw new ErrorCedula("Error en el tercer dígito: " + tercerDigito);
        }
        //Se realiza el algoritmo de validación con la suma y el último dígito
        int numValidacion = 0;
        int sumaPar = 0;
        int sumaImpar = 0;
        int numero;
        for(int i = 0; i < cedula.length() - 1; i++){
            numero = Integer.parseInt(String.valueOf(cedula.charAt(i)));
            if((i+1) % 2 != 0){//Si la posición es impar
                numero *= 2;
                if(numero > 9){
                    numero -= 9;
                }
                sumaImpar += numero;
            }else{
                sumaPar += numero;
            }
        }
        int modulo = (sumaPar + sumaImpar)%10;
        if(modulo == 0) {
            numValidacion = 0;
        }else{
            numValidacion = 10 - modulo;
        }
        int ultimoNumero = Integer.parseInt(String.valueOf(cedula.charAt(9)));
        if (numValidacion != ultimoNumero){
            throw new ErrorCedula("Error en el dígito validador: " + ultimoNumero);
        }
    }

    public Cliente(String cedula, String nombres, String apellidos, String fecha, char sexo, String telefono, String nombreContacto,
                   String telefonoContacto, String correoElectronico, String direccion) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha = stringALocalDate(fecha);
        this.sexo = sexo;
        this.telefono = telefono;
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
    }

    private static LocalDate stringALocalDate(String fecha) {
        return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
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
