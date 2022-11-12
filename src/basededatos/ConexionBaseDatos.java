package basededatos;

import java.sql.*;

public class ConexionBaseDatos {
    private String cadenaConexion;

    public ConexionBaseDatos(String cadenaConexion) {
        this.cadenaConexion = cadenaConexion;
    }

    public SQLTable realizarConsulta(String consulta){
        try {
            Connection connection = DriverManager.getConnection(this.cadenaConexion);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);
            SQLTable results = new SQLTable(resultSet);
            connection.close();
            return results;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean ejecutarSentencia(String sentencia) {
        try {
            Connection connection = DriverManager.getConnection(this.cadenaConexion);
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sentencia);
            boolean executed = preparedStatement.execute();
            connection.close();
            return executed;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
