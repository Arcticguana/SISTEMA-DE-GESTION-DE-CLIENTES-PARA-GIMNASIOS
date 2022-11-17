package basededatos;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLTable {
    private String[] columnNames;
    private HashMap<String, Integer> columnIndex;
    private String tableName;
    private int columnCount;
    private int rowCount = 0;
    private ArrayList<String[]> rows;

    public SQLTable(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        columnCount = metaData.getColumnCount();
        columnNames = new String[columnCount];
        columnIndex = new HashMap<>();
        rows = new ArrayList<>();
        //Fill the column names

        for(int i = 0 ; i < columnCount ; i++){
            columnNames[i] = metaData.getColumnName(i+1);
            columnIndex.put(columnNames[i], i);
        }

        //Fill the rows with the data
        while(resultSet.next()){
            String[] row = new String[columnCount];
            rowCount += 1;
            for(int i = 0 ; i < columnCount ; i++){
                row[i] = resultSet.getString(i+1);
            }
            rows.add(row);
        }
    }

    public HashMap<String, Integer> getColumnIndexMap() {
        return columnIndex;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public String getTableName() {
        return tableName;
    }

    public int getColumIndex(String column){
        if (columnIndex.containsKey(column)) return columnIndex.get(column);
        return -1;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public ArrayList<String[]> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        if(rows.size() == 0){
            return "No rows selected";
        }

        String format = "| %-30s";
        String output = "";
        String separador = "+";

        for(int i = 0 ; i < columnCount ; i++){
            separador += "-------------------------------+";
        }
        output += separador +"\n";
        for(String name : columnNames){
            output += String.format(format,name);
        }
        output += "|\n"+separador+"\n";

        for(String[] row : rows){
            for(String data : row){
                output += String.format(format,data);
            }
            output += "|\n";
        }
        output += separador;

        return output;
    }

    public String getValueAt(int row, int column){
        if(row >= rowCount || column >= columnCount){
            return null;
        }
        return getRows().get(row)[column];
    }

    public String getValueAt(int row, String column){
        if(row >= rowCount || getColumIndex(column) < 0){
            return null;
        }
        return getRows().get(row)[getColumIndex(column)];
    }
}
