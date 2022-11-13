package basededatos;

import java.sql.*;

public class ConexionBaseDatos {
    private String cadenaConexion;

    public ConexionBaseDatos(String cadenaConexion) {
        this.cadenaConexion = cadenaConexion;
    }

    /**
     * Realiza una consulta SQL únicamente, (no acepta insert, update, ni delete).
     * Método para la obtención de datos únicamente.
     * @param consulta String con la consulta SQL
     * @return SQLTable con los resultados de la consulta
     * @throws SQLException
     */
    public SQLTable realizarConsulta(String consulta) throws SQLException{
        Connection connection = DriverManager.getConnection(this.cadenaConexion);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(consulta);
        SQLTable resultados = new SQLTable(resultSet);
        connection.close();
        return resultados;
    }

    /**
     * Realiza la ejecución de sentencias (insert, update, delete, etc...)
     * @param sentencia
     * @return SQLTable con los resultados de la consulta o null si la sentencia no arroja resultados
     * @throws SQLException
     */
    public SQLTable ejecutarSentencia(String sentencia) throws SQLException{

        Connection connection = DriverManager.getConnection(this.cadenaConexion);
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(sentencia);
        if(preparedStatement.execute()){ //Si al ejecutar la sentencia existe un resultado
            SQLTable table = new SQLTable(preparedStatement.getResultSet());
            connection.close();
            return table;
        }
        connection.close();
        return null;
    }

}
