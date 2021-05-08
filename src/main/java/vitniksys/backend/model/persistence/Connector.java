package vitniksys.backend.model.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Connector
{
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //private static final String URL = "jdbc:mysql://localhost:3306/vitniksanluis?serverTimezone=UTC";
    //private static final String URL = "jdbc:mysql://localhost:3306/vitniksanluis?serverTimezone=America/Argentina/Buenos_Aires";
    private static final String URL = "jdbc:mysql://localhost:3308/vitniksanluis?serverTimezone=America/Argentina/Buenos_Aires";
    //private static final String URL = "jdbc:mysql://localhost:3308/vitniksanluis_test?serverTimezone=America/Argentina/Buenos_Aires";
    private static final String USER = "root";
    private static final String PASS = "";
    
    private static Connector connector = null;
    private static Connection connection = null;

    private Connector() throws ClassNotFoundException, SQLException
    {
        Class.forName(DRIVER);
        Connector.connection = DriverManager.getConnection(URL, USER, PASS);
    }

    public static Connector getConnector()
    {
        try 
        {
            if (Connector.connector == null)
            {
                Connector.connector = new Connector();
            }    
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        
        return Connector.connector;
    }

    public void closeConnection()
    {
        try
        {
            Connector.connection.close();
            Connector.connector = null;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void startTransaction()
    {
        try
        {
            Connector.connection.setAutoCommit(false);   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void commit()
    {
        try
        {
            Connector.connection.commit();   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void rollBack()
    {
        try
        {
            Connector.connection.rollback();   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void endTransaction()
    {
        try
        {
            Connector.connection.setAutoCommit(true);    
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public PreparedStatement getStatement(String SQLstatement)
    {
        PreparedStatement statement = null;

        try
        {
            statement = Connector.connection.prepareStatement(SQLstatement);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return statement;
    }

    public PreparedStatement getStatement(String SQLstatement, int returnGenKeys) throws SQLException
    {
        PreparedStatement statement = null;

        try
        {
            statement = Connector.connection.prepareStatement(SQLstatement, returnGenKeys);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return statement;
    }
}