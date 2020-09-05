package vitniksys.backend.model.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

public class Connector
{
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/vitniksanluis?serverTimezone=UTC"; 
    //private static final String URL = "jdbc:mysql://localhost:3306/vitniksanluis";//SET GLOBAL time_zone = '-3:00'
    private static final String USER = "root";
    private static final String PASS = "";

    private static Connector connector = null;
    private static Connection connection = null;

    private Connector()
    {
        try
        {
            Class.forName(DRIVER);
            Connector.connection = DriverManager.getConnection(URL, USER, PASS);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio un Error en la Conexion", "Error en la Conexion",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Connector getConnector()
    {
        if (Connector.connector == null)
        {
            Connector.connector = new Connector();
        }
        return Connector.connector;
    }

    public void closeConnection() throws SQLException
    {
        Connector.connection.close();
        Connector.connector = null;
    }

    public void startTransaction() throws SQLException
    {
        Connector.connection.setAutoCommit(false);
    }

    public void commit() throws SQLException
    {
        Connector.connection.commit();
    }

    public void rollBack() throws SQLException
    {
        Connector.connection.rollback();
    }

    public void endTransaction() throws SQLException
    {
        Connector.connection.setAutoCommit(true);
    }

    public PreparedStatement getStatement(String SQLstatement) throws SQLException
    {
        return Connector.connection.prepareStatement(SQLstatement);
    }
}