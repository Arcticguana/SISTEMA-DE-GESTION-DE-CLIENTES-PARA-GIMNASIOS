package validacion;

import clientes.excepciones.ErrorCedula;

public class ValidadorCedula {
    /**
     * Target es la cédula que ingresa para su validación en el siguiente orden:
     * <li>La cédula no está vacía</li>
     * <li>La cédula contiene solo números</li>
     * <li>La cédula contiene exactamente 10 números</li>
     * <li>Los primeros dos dígitos de la cédula están entre 1 y 24</li>
     * <li>El tercer dígito es menor a 6</li>
     * <li>El último dígito de la cédula es igual al número calculado con el algoritmo de suma</li>
     * @param cedula
     * @throws ErrorCedula
     */
    public void validar(String cedula) throws ErrorCedula {
        validarCadenaVacia(cedula);
        validarSoloNumeros(cedula);
        validarLongitud(cedula);
        validarPrimerosDigitos(cedula);
        validarTercerDigito(cedula);
        validarDigitoVerificador(cedula);
    }

    /**
     * Revisa que la cadena contenga únicamente números
     * @param cedula
     */
    private void validarSoloNumeros(String cedula) throws ErrorCedula {
        if(!cedula.matches("[0-9]+")){
            throw new ErrorCedula("La cédula solo puede contener números");
        }
    }
    /**
     * Se comprueba que la cadena que ingresa no está vacía
     * @param cedula
     * @throws ErrorCedula
     */
    private void validarCadenaVacia(String cedula) throws ErrorCedula {
        if (cedula.length() == 0) {
            throw new ErrorCedula("La cédula no puede estar vacía");
        }
    }
    /**
     * Comprueba si la longitud de la cadena es 10
     * @param cedula
     * @throws ErrorCedula
     */
    private void validarLongitud(String cedula) throws ErrorCedula {
        if(cedula.length() != 10){
            throw new ErrorCedula("La cédula debe tener una longitud de 10 números, se encontraron " + cedula.length());
        }
    }

    /**
     * Si es que los primeros dígitos no están entre 1 y 24 entonces la cédula no es válida.
     * @param cedula
     */
    private void validarPrimerosDigitos(String cedula) throws ErrorCedula {
        //Se revisa que los primeros números estén entre 1 y 24
        int primeros = Integer.parseInt(cedula.substring(0,2));

        if (primeros > 24 || primeros < 1){
            throw new ErrorCedula("Error en los primeros dígitos de la cédula: " + primeros);
        }
    }

    /**
     * Si el tercer dígito es mayor o igual que 6 entonces la cédula no es válida
     * @param cedula
     */
    private void validarTercerDigito(String cedula) throws ErrorCedula {
        int tercerDigito = Integer.parseInt(String.valueOf(cedula.charAt(2)));
        if (tercerDigito >= 6){
            throw new ErrorCedula("Error en el tercer dígito: " + tercerDigito);
        }
    }

    /**
     * Si el último dígito de la cédula no es igual al valor calculado con el algoritmo de
     * suma, entonces no es una cédula válida.
     * @param cedula
     * @throws ErrorCedula
     */
    private void validarDigitoVerificador(String cedula) throws ErrorCedula {
        //Se realiza el algoritmo de validación con la suma y el último dígito
        int numValidacion = 0, sumaPar = 0, sumaImpar = 0, numero;
        for(int i = 0; i < cedula.length() - 1; i++){
            numero = Integer.parseInt(String.valueOf(cedula.charAt(i)));
            if((i+1) % 2 != 0){//Si la posición es impar
                numero *= 2;
                if(numero > 9){ numero -= 9;}
                sumaImpar += numero;
            }else{
                sumaPar += numero;
            }
        }
        int modulo = (sumaPar + sumaImpar) % 10;
        if(modulo != 0) {
            numValidacion = 10 - modulo;
        }
        int ultimoNumero = Integer.parseInt(String.valueOf(cedula.charAt(9)));
        if (numValidacion != ultimoNumero){
            throw new ErrorCedula("Error en el dígito validador: " + ultimoNumero);
        }
    }

}
