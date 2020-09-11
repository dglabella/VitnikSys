package vitniksys.backend.model.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.util.CustomAlert;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Connector {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3308/vitniksanluis?serverTimezone=UTC";
    // private static final String URL =
    // "jdbc:mysql://localhost:3306/vitniksanluis";//SET GLOBAL time_zone = '-3:00'
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
        if (Connector.connector == null)
        {
            try
            {
                Connector.connector = new Connector();
            }
            catch (ClassNotFoundException | SQLException exception)
            {
                new CustomAlert(AlertType.ERROR, CustomAlert.DEFAULT_ERROR_TITLE , CustomAlert.DEFAULT_ERROR_HEADER,
                            CustomAlert.DEFAULT_DESCRIPTION, exception).customShow();
            }
        }
        return Connector.connector;
    }

    public void closeConnection()
    {
        try
        {
            Connector.connection.close();
        }
        catch (SQLException exception)
        {
            new CustomAlert(AlertType.ERROR, CustomAlert.DEFAULT_ERROR_TITLE , CustomAlert.DEFAULT_ERROR_HEADER,
                            CustomAlert.DEFAULT_DESCRIPTION, exception).customShow();
        }
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

    public void rollBack()
    {
        try
        {
            Connector.connection.rollback();
        }
        catch (Exception exception)
        {
            new CustomAlert(AlertType.ERROR, CustomAlert.DEFAULT_ERROR_TITLE , CustomAlert.DEFAULT_ERROR_HEADER,
                            CustomAlert.DEFAULT_DESCRIPTION, exception).customShow();
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
            new CustomAlert(AlertType.ERROR, CustomAlert.DEFAULT_ERROR_TITLE , CustomAlert.DEFAULT_ERROR_HEADER,
                            CustomAlert.DEFAULT_DESCRIPTION, exception).customShow();
        }
    }

    public PreparedStatement getStatement(String SQLstatement) throws SQLException
    {
        return Connector.connection.prepareStatement(SQLstatement);
    }
}