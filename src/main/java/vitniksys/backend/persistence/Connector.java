package vitniksys.backend.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Connector {
    
    private static final String DRIVER  = "com.mysql.cj.jdbc.Driver"; 
    private static final String URL = "jdbc:mysql://localhost:3306/vitniksanluis";
    private static final String USER = "root";
    private static final String PASS = "";
    
    private static Connector connectorSingleton = null;
    private static Connection connection = null;
    
    private Connector(){
        try 
        {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USER,PASS);
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            //Alert alert =  new Alert(AlertType.ERROR, "Ocurrio un Error en la Conexion");
            //alert.setTitle("Error");
            JOptionPane.showMessageDialog(null, "Ocurrio un Error en la Conexion", "Error en la Conexion", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*
    private Connector(String driver, String url, String user ,String pass){
        try 
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,pass);
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio un Error en la Conexion", "Error en la Conexion", JOptionPane.ERROR_MESSAGE);
        }
    }
    */
    
    public static Connector getConnector()
    {
        if (Connector.connectorSingleton==null)
        {
            Connector.connectorSingleton = new Connector();
        }
        return Connector.connectorSingleton;
    }

    /*
    private static Connector createConnector(String driver, String url, String user, String pass)
    {
        if (connectorSingleton==null)
        {
            connectorSingleton = new Connector(driver, url, user, pass);
        }
        return connectorSingleton;
    }
    */

    public Connection getConnection()
    {
        return Connector.connection;
    }
}